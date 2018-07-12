package com.velvol.salary.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.velvol.salary.dao.IndexMapper;
import com.velvol.salary.domain.User;
import com.velvol.salary.service.IndexService;
import com.velvol.salary.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/12.
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
   private IndexMapper indexMapper;
   public PageInfo queryUser(Integer pageNum){
       if(pageNum==null||pageNum==0){
           pageNum= Constants.DEFAULT_PAGE;
       }
       Integer   pageSize= Constants.DEFAULT_SIZE;
       PageHelper.startPage(pageNum, pageSize);
       List<User> list=indexMapper.queryUser();
       PageInfo page = new PageInfo(list);
       return page;
   }
}
