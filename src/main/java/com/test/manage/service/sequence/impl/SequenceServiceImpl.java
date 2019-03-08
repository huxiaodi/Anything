package com.test.manage.service.sequence.impl;

import java.util.Date;

import com.test.manage.dao.custom.SequenceCuMapper;
import com.test.manage.dao.generator.SequenceMapper;
import com.test.manage.model.generator.Sequence;
import com.test.manage.service.sequence.SequenceService;
import org.apache.commons.lang3.StringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by hugo on 2016/10/18.
 */
@Service
public class SequenceServiceImpl implements SequenceService {
	public static final Logger logger = LogManager.getLogger(SequenceServiceImpl.class);
    @Autowired
    private SequenceCuMapper sequenceCuMapper;
    @Autowired
    private SequenceMapper sequenceMapper;

    @Override
    public Integer next(String sequenceName) {
        Sequence sequence = new Sequence();
        sequence.setSequenceName(sequenceName);
        sequenceCuMapper.next(sequence);
        return sequence.getSequenceId();
    }

    @Override
    public Integer now(String sequenceName) {
        return sequenceCuMapper.now(sequenceName);
    }

    @Override
    public void init(String sequenceName, String sequenceDesc) {
        Sequence sequence = new Sequence();
        sequence.setSequenceName(sequenceName);
        sequence.setSequenceDesc(sequenceDesc);
        sequence.setSequenceId(0);
        sequence.setSequenceCreateTime(new Date());
        sequence.setSequenceUpdateTime(new Date());
        sequenceMapper.insert(sequence);
    }

    @Override
    public void  init(String sequenceName) {
        init(sequenceName, "");
    }

    @Override
    public String generateNo(String sequenceName, Integer length, String sequenceDesc) {
    	 String generateNo =  makeGenerateNo(sequenceName,length,sequenceDesc,0);
    	 if(StringUtils.isBlank(generateNo)){
    		 throw new RuntimeException("---create sequence error！序列号生成失败---");
    	 }
    	 return generateNo;
    }
    /****
     * 
    * @author zybiao
    * @Description: 生成序列号
    * @param sequenceName 
    * @param length
    * @param sequenceDesc
    * @param count 如出现异常最多执行的次数
    * @return
    * @throws
     */
    private String makeGenerateNo(String sequenceName, Integer length, String sequenceDesc,int count){
    	String generateNo = null;
    	try {
		    if(count < 10){
		    	Integer sequence = now(sequenceName);
		    	if (sequence == null || sequence == 0) {
		    		init(sequenceName, sequenceDesc);
		    	}
		    	sequence = next(sequenceName);
		    	String sequenceStr = String.valueOf(sequence);
		    	StringBuilder sb = new StringBuilder();
		    	sb.append(sequenceName);
		    	for (int i = 0, l = length - sequenceStr.length(); i < l; i++) {
		    		sb.append("0");
		    	}
		    	sb.append(sequenceStr);
		    	generateNo = sb.toString();
		    	return generateNo;
		    }
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			count ++;
			return makeGenerateNo(sequenceName,length,sequenceDesc,count) ;
		}
    	return generateNo;
    }
    
    @Override
    public String generateNo(String sequenceName, Integer length) {
        return generateNo(sequenceName, length, "");
    }
}
