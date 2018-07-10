package com.velvol.salary.dao;


import com.velvol.salary.domain.Tipinfo;

import java.util.Map;

public interface TipinfoMapper {

    Tipinfo queryTipinfo(Map<String, Object> params);
}