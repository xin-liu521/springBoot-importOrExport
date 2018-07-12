package com.velvol.salary.dao;


import com.velvol.salary.domain.EquipWorker;

public interface EquipWorkerMapper {

    EquipWorker findById(String riderId);

    int updateByPrimaryKeySelective(EquipWorker equipWorker);
}