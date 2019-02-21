/**
 *
 */
package com.dllg.common;

import com.dllg.ebpay.model.dto.Menu;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * @Title: CommonUtil
 * @Description: 工具类
 * @Author:zjh
 * @Since:2016年5月3日
 * @Version:1.1.0
 */
public class CommonUtil {
//
//    public static FileSystemResource convertFile(MultipartFile file) throws IOException {
//        File temp = new File(file.getOriginalFilename());
//        FileUtils.copyInputStreamToFile(file.getInputStream(), temp);
//        return new FileSystemResource(temp);
//    }
//
//    public static BigDecimal round(BigDecimal bigDecimal) {
//        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
//    }
//
    public static void keys(List<Menu> menus, List<String> keys) {
        for (Menu menu : menus) {
            keys.add(menu.getResourceId());
            keys(menu.getChildren(), keys);
        }
    }
//
//    //去掉没有权限的菜单
//    public static void remainMenu(List<Menu> menus, List<String> keys) {
//        for (int l = menus.size() - 1; l >= 0; l--) {
//            Menu item = menus.get(l);
//            if (keys.contains(item.getResourceId())) {
//                if (item.getChildren() != null && !item.getChildren().isEmpty()) {
//                    remainMenu(item.getChildren(), keys);
//                }
//            } else {
//                menus.remove(item);
//            }
//        }
//    }
//
//    /**
//     * 时间
//     */
//    private static String date;
//    /**
//     * 订单号
//     */
//    private static long orderNum = 0l;
//
    /**
     * 获取UUID
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-","");
    }

//
//
//    /**
//     * 将字符串转换为集合
//     *
//     * @param value
//     * @param decollator
//     * @return
//     */
//    public static List<String> convert(String value, String decollator) {
//        String[] idstr = value.split(decollator);
//        List<String> list = new ArrayList<String>();
//        for (String id : idstr) {
//            if (StringUtils.isNotBlank(id)) {
//                list.add(id);
//            }
//        }
//        return list;
//    }
//
//    public static List<String> convert(String value) {
//        return convert(value, ",");
//    }
//
//    /**
//     * newList比oldList多的
//     *
//     * @param newList
//     * @param oldList
//     * @return
//     */
//    public static List<String> moreThan(final List<String> newList, final List<String> oldList) {
//        List<String> tempNew = new ArrayList<>();
//        tempNew.addAll(newList);
//        List<String> tempOld = new ArrayList<>();
//        tempOld.addAll(oldList);
//        tempNew.removeAll(tempOld);
//        return tempNew;
//    }
//
//    /**
//     * newList比oldList少的
//     *
//     * @param newList
//     * @param oldList
//     * @return
//     */
//    public static List<String> lessThan(final List<String> newList, final List<String> oldList) {
//        List<String> tempNew = new ArrayList<>();
//        tempNew.addAll(newList);
//        List<String> tempOld = new ArrayList<>();
//        tempOld.addAll(oldList);
//        tempOld.removeAll(tempNew);
//        return tempOld;
//    }
//
//    public static String compareObjects(Object oldObject, Object newObject) {
//        BeanMap map = new BeanMap(oldObject);
//        PropertyUtilsBean propUtils = new PropertyUtilsBean();
//        StringBuilder sb = new StringBuilder();
//        for (Object propNameObject : map.keySet()) {
//            try {
//                String propertyName = (String) propNameObject;
//                Object property1 = propUtils.getProperty(oldObject, propertyName);
//                Object property2 = propUtils.getProperty(newObject, propertyName);
//                if (!ObjectUtils.nullSafeEquals(property1, property2)) {
//                    sb.append(propertyName + "：值变化，修改前：" + property1 + ",修改后：" + property2 + ";");
//                }
//            } catch (Exception e) {
//
//            }
//        }
//        return sb.toString();
//    }
//
//    public static void main(String[] args) throws Exception {
////        List<String> list1 = new ArrayList<>();
////        list1.add("1");
////        list1.add("2");
////        list1.add("3");
////        List<String> list2 = new ArrayList<>();
////        list2.add("2");
////        list2.add("3");
////        list2.add("4");
////        String s = "[{\"resourceId\":\"125a8e7a-368b-4def-bd62-46de3f65ae90\",\"resourceEnName\":\"alliancePlatform\",\"children\":[{\"resourceId\":\"e0dd236f-7bb9-4a31-b619-8726e4696b65\",\"resourceEnName\":\"alliancePlatform company management\",\"children\":[],\"resourceName\":\"联盟平台公司管理\",\"parentResourceId\":\"125a8e7a-368b-4def-bd62-46de3f65ae90\",\"resourceValue\":\"\"},{\"resourceId\":\"d5d9c546-1d9b-4dd9-bc79-ba7db06e90af\",\"resourceEnName\":\"alliancePlatform user management\",\"children\":[],\"resourceName\":\"联盟平台用户管理\",\"parentResourceId\":\"125a8e7a-368b-4def-bd62-46de3f65ae90\",\"resourceValue\":\"\"}],\"resourceName\":\"联盟平台权限资源\",\"resourceIndex\":2,\"parentResourceId\":\"\",\"resourceValue\":\"\",\"resourceType\":1}]\n";
//////        System.out.println(JSON.toJSONString(moreThan(list1, list2)));
//////        System.out.println(JSON.toJSONString(lessThan(list1, list2)));
//////
////        User user = new User();
////        user.setUserId(uuid());
////        user.setUserName("jack");
////        User user1 = new User();
////        user1.setUserId(uuid());
////        user1.setUserName("hugo");
////        System.out.println(compareObjects(user, user1));
//
//        System.out.println(uuid());
//        //String[] sss = new String[]{"Pwd..123"};
//        //ConfigTools.main(sss);
//    }
//
//    public static File readClassPathFile(String fileName) {
//        try {
//            return ResourceUtils.getFile(MessageFormat.format("classpath:{0}", fileName));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static Map<String, String> readClassPathProps(String fileName) {
//        Map<String, String> map = new HashMap<>();
//        File file = readClassPathFile(fileName);
//        Properties properties = new Properties();
//        try {
//            properties.load(FileUtils.openInputStream(file));
//            properties.keySet().stream().forEach(p -> {
//                map.put(p.toString(), properties.getProperty(p.toString()));
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
//
//    //解析xml返回map
//    @SuppressWarnings("unchecked")
//
//
//    /**
//     * 新增文件
//     *
//     * @param fileUploadUtils
//     * @param file            文件
//     * @param filePath        文件类型所属,如driver,truck,animial
//     * @param parentFolder    文件属父文件夹名
//     * @param fileName        文件名不包含后缀
//     * @return 文件下载地址
//     */
//    public static String addFile(FileUploadUtils fileUploadUtils, MultipartFile file, String filePath, String parentFolder, String fileName, HttpServletRequest request) {
////        String uuid = StringUtils.isBlank(fileName) ? CommonUtil.uuid() : fileName;//如果未传文件名，则生成uuid
//        String uuid = CommonUtil.uuid();//如果未传文件名，则生成uuid
//        String filename = file.getOriginalFilename();
//        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
//        String finalName = filePath + "/" + parentFolder + "/" + uuid + "." + suffix;
//        return fileUploadUtils.upload(file, finalName);
//    }
//    /**
//     * 新增文件
//     *
//     * @param fileUploadUtils
//     * @param file            文件
//     * @param filePath        文件类型所属,如driver,truck,animial
//     * @param parentFolder    文件属父文件夹名
//     * @param fileName        文件名不包含后缀
//     * @return 文件下载地址
//     *//*
//    public static String addFile(FileUploadUtils fileUploadUtils, MultipartFile file, String filePath, String parentFolder, String fileName,HttpServletRequest request) {
//        String uuid = StringUtils.isBlank(fileName) ? CommonUtil.uuid() : fileName;//如果未传文件名，则生成uuid
//
//        String filename = file.getOriginalFilename();
//
//        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
//        String finalName = request.getServletContext().getContextPath() + fileUploadUtils.getStaticUrl() + "/" + uuid + "." + suffix;
//
////       String url = fileUploadUtils.getStaticUrl() + finalName;
//        StringBuffer httpurl = request.getRequestURL();
//        String tempContextUrl = httpurl.delete(httpurl.length() - request.getRequestURI().length(), httpurl.length()).toString();
//        String url = tempContextUrl + finalName;
//        System.out.println(request.getServletContext().getContextPath());
//        //url = url.replace("qiatu/static/", "");
//        try {
//            File dir = new File(fileUploadUtils.getStaticUrl());
//            //如果文件夹不存在则创建
//            if (!dir.exists() && !dir.isDirectory()) {
//                dir.mkdirs();
//            }
//            FileUtils.copyInputStreamToFile(file.getInputStream(), new java.io.File(finalName));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "";
//        }
//        return url;
//    }*/
//
//    /**
//     * 删除文件文件
//     *
//     * @param fileUploadUtils
//     * @param filePath
//     * @return 文件下载地址
//     */
//    public static boolean delFile(FileUploadUtils fileUploadUtils, String filePath) {
//        boolean returnFlag = true;
//        String prefix = fileUploadUtils.getStaticUrl();
//        if (!StringUtils.isBlank(filePath)) {
//            if (!filePath.contains(":")) {//非windows系统下，需要替换域名
//                filePath = filePath.replace(prefix, "");
//            }
//            File file = new File(filePath);
//            if (file.exists()) {
//                returnFlag = file.delete();
//            }
//        }
//        return returnFlag;
//    }
//
//    /**
//     * 生成订单编号
//     *
//     * @return
//     */
//    public static synchronized String getSerial(String prefix) {
//        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
//        if (date == null || !date.equals(str)) {
//            date = str;
//            orderNum = 0l;
//        }
//        orderNum++;
//        // 控制2位
//        if (orderNum > 99) {
//            orderNum = 01;
//        }
//        long orderNo = Long.parseLong((date)) * 10000;
//        orderNo += orderNum;
//        return prefix + orderNo;
//    }
//
//    /**
//     * 设置cookie
//     *
//     * @param response
//     * @param name     cookie名字
//     * @param value    cookie值
//     * @param maxAge   cookie生命周期  以秒为单位
//     */
//    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
//        Cookie cookie = new Cookie(name, value);
//        cookie.setPath("/");
//        if (maxAge > 0) cookie.setMaxAge(maxAge);
//        response.addCookie(cookie);
//    }
//
//    /**
//     * 根据名字获取cookie
//     *
//     * @param request
//     * @param name    cookie名字
//     * @return
//     */
//    public static Cookie getCookieByName(HttpServletRequest request, String name) {
//        Map<String, Cookie> cookieMap = ReadCookieMap(request);
//        if (cookieMap.containsKey(name)) {
//            Cookie cookie = (Cookie) cookieMap.get(name);
//            return cookie;
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * 将cookie封装到Map里面
//     *
//     * @param request
//     * @return
//     */
//    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
//        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
//        Cookie[] cookies = request.getCookies();
//        if (null != cookies) {
//            for (Cookie cookie : cookies) {
//                cookieMap.put(cookie.getName(), cookie);
//            }
//        }
//        return cookieMap;
//    }
//
//    /**
//     * 返回树形结构的list菜单
//     *
//     * @param resources
//     * @param parentId
//     * @return
//     */
//    public static List<Menu> treeMenuList(List<Resource> resources, String parentId) {
//        List<Menu> childMenu = new ArrayList<Menu>();
//        for (Resource resource : resources) {
//            if (StringUtils.equals(parentId, resource.getParentResourceId())) {
//                Menu menu = new Menu();
//                BeanUtils.copyProperties(resource, menu);
//                List<Menu> childs = CommonUtil.treeMenuList(resources, menu.getResourceId());
//                menu.setChildren(childs);
//                childMenu.add(menu);
//            }
//        }
//        return childMenu;
//    }
//
//
//    /**
//     * @param request
//     * @param @param  mv    设定文件
//     * @return void    返回类型
//     * @throws
//     * @Title: initMvObject
//     * @Description: 初始化mv
//     */
//
//
//    public static void initMvObject(HttpServletRequest request, ModelAndView mv) {
//        Cookie cookie1 = CommonUtil.getCookieByName(request, "Authentication");
//        Cookie cookie2 = CommonUtil.getCookieByName(request, "userId");
//        Cookie cookie3 = CommonUtil.getCookieByName(request, "userName");
//        if (cookie1 != null) {
//            mv.addObject("Authentication", cookie1.getValue());
//        }
//        if (cookie2 != null && !"".equals(cookie2.getValue())) {
//            mv.addObject("userId", "".equals(cookie2.getValue()) ? null : cookie2.getValue());
//        }
//        if (cookie3 != null) {
//            mv.addObject("userName", cookie3.getValue());
//        }
//    }
//
//    public static String  phoneFormat(String  phone){
//        if(phone.lastIndexOf('.') != -1) {
//            return phone.substring(0, phone.lastIndexOf('.'));
//        }else {
//            return phone;
//        }
//    }
//
public static Map<String, Object> xml(String str) {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
        Document doc = DocumentHelper.parseText(str);
        Element rootElt = doc.getRootElement(); // 获取根节点
        System.out.println("根节点：" + rootElt.elements().size()); // 拿到根节点的名称
        List<Element> list = rootElt.elements();
        for (Element element : list) {
            System.out.println(element.getName() + element.getData());
            map.put(element.getName(), element.getData());
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return map;
}
    /**
     * 数据校验
     * @param bean
     * @return
     */
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static Validator validator = factory.getValidator();
    public static Map<String, String> validate(Object bean) {
        Set<ConstraintViolation<Object>> validate = validator.validate(bean);
        Map<String, String> map = new HashMap<>();
        validate.forEach(p -> map.put(p.getPropertyPath().toString(), p.getMessage()));
        return map;
    }
}
