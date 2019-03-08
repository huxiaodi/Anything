package com.test.manage.dao.custom;


import com.test.manage.model.generator.Sequence;

public interface SequenceCuMapper {
    void next(Sequence sequence);

    Integer now(String sequenceName);
}