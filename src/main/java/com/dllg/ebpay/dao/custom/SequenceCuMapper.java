package com.dllg.ebpay.dao.custom;


import com.dllg.ebpay.model.generator.Sequence;

public interface SequenceCuMapper {
    void next(Sequence sequence);

    Integer now(String sequenceName);
}