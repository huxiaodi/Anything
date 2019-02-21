package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.model.response.WithdrawalBankCardResponse;

import java.util.List;

public interface BankCardCuMapper {

    List<WithdrawalBankCardResponse> getBindCardBySubNo(String accountNo);
}
