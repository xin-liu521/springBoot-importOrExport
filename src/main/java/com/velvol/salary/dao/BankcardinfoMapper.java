package com.velvol.salary.dao;


import com.velvol.salary.domain.Bankcardinfo;

import java.util.List;

public interface BankcardinfoMapper {

    List<Bankcardinfo> queryBankInfo(String workerId);

    int addBankInfo(Bankcardinfo bankcardinfo);

    int updateBankInfo(Bankcardinfo bankcardinfo);
}