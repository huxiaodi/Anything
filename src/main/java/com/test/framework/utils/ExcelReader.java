/**
 * Project Name:迅羽科技
 * File Name:ExcelReader.java
 * Package Name:com.qt.excel
 * Date:2016年9月24日上午10:57:36
 * Copyright (c) 2016, 无锡迅羽科技 All Rights Reserved.
 *
*/

package com.test.framework.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 操作Excel表格的功能类 ClassName:ExcelReader <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年10月22日
 * 
 * @author zhuxiao
 * @version
 * @since JDK 1.7
 * @see
 */
public class ExcelReader<T> {
	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;

	
	/**
	 * 读取Excel数据内容
	 * 
	 * @return Map 包含单元格数据内容的Map对象
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public List<T> readExcelContent(InputStream is,int sheetNum,Map<String,String>map,String clsName) {
		List<T> list = new ArrayList<T>();
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (OfficeXmlFileException e) {
			
			throw new RuntimeException("请将xlsx装换成xls再导入");
			//e.printStackTrace();
		}catch (IOException e) {
			//throw new RuntimeException("导入失败");
			e.printStackTrace();
		}
		
		sheet = wb.getSheetAt(sheetNum);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			title[i] = getCellFormatValue(row.getCell((short) i));
		}
		
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		//列的数量
		// 正文内容应该从第1行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			if(row==null){
				break;
			}
			
			try {
				T obj = (T) Class.forName(clsName).newInstance();
				
				for(int j = 0;j<colNum;++j){
					String titleName = title[j];
					String obj_field = map.get(titleName);
					if(obj_field == null ){
						throw new RuntimeException("标题["+titleName+"]不正确");
					}
					String value = getCellFormatValue(row.getCell((short) j)).trim();
					
					if("goodTypeId".equals(obj_field) || "goodTypeName".equals(obj_field)){
						if(StringUtils.isEmpty(value)){
							throw new RuntimeException("标题["+titleName+"]不能为空" );
						}
					}
					
					if(StringUtils.isEmpty(value) ){
						continue;
					}
					try {
						setObj(obj,value,obj_field);
					} catch (NoSuchMethodException e) {
						throw new RuntimeException("标题["+titleName+"]对应的字段不正确" , e);
					} catch (SecurityException e) {
						throw new RuntimeException("标题["+titleName+"]对应的字段不正确" , e);
					} catch (NoSuchFieldException e) {
						throw new RuntimeException("标题["+titleName+"]对应的字段不正确" , e);
					} catch (IllegalArgumentException e) {
						throw new RuntimeException("标题["+titleName+"]对应的字段不正确" , e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException("标题["+titleName+"]对应的字段不正确" , e);
					} catch (ParseException e) {
						throw new RuntimeException("标题["+titleName+"]必须是时间格式" , e);
					}
				}
				list.add(obj);
			} catch (InstantiationException e) {
				throw new RuntimeException("实例化异常" , e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("类包的访问权限异常" , e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("对象不存在" , e);
			}
		}
		return list;
	}
	

	
	
	public static void setObj(Object obj,String value,String field_name) throws NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException{
		Class<? extends Object> cla = obj.getClass();
		Field [] fi = cla.getDeclaredFields();
		// 将属性的首字符大写,方便构造get，set方法
		String method_name =  field_name.substring(0, 1).toUpperCase() + field_name.substring(1); 
			
		Field field = cla.getDeclaredField(field_name);
		
		// 获取属性的类型 
		String type = field.getGenericType().toString();



		if("class java.lang.String".equals(type)){
			Method method = cla.getDeclaredMethod("set" + method_name, String.class);
			method.invoke(obj, value);
		}
		
		if("int".equals(type) || "class java.lang.Integer".equals(type)){
			Method method = cla.getDeclaredMethod("set" + method_name, Integer.class);
			Integer val = Integer.parseInt(value);
			method.invoke(obj,val);
		}
		
		if("class java.math.BigDecimal".equals(type)){
			Method method = cla.getDeclaredMethod("set" + method_name, BigDecimal.class);
			BigDecimal val = new BigDecimal(value);
			method.invoke(obj,val);
		}
		
		if("long".equals(type) || "class java.lang.Long".equals(type)){
			Method method = cla.getDeclaredMethod("set" + method_name, Long.class);
			Long val = Long.parseLong(value);
			method.invoke(obj,val);
		}
		
		
		if("class java.util.Date".equals(type)){
			Method method = cla.getDeclaredMethod("set" + method_name, Date.class);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	        Date  date = format.parse(value);   
			method.invoke(obj,date);
		}
		
		if("class java.lang.Float".equals(type)){
			Method method = cla.getDeclaredMethod("set" + method_name, Float.class);
			Float val = Float.parseFloat(value);
			method.invoke(obj,val);
		}
			
	}
	
	
	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getStringCellValue(HSSFCell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 获取单元格数据内容为日期类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getDateCellValue(HSSFCell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate();
			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式

					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();

					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);

				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;

	}
}
