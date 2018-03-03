package com.taotao.dao;

import com.ebuy.pojo.TbOrder;
import com.ebuy.pojo.TbOrderQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbOrderDao {
    int countByExample(TbOrderQuery example);

    int deleteByExample(TbOrderQuery example);

    int deleteByPrimaryKey(String orderId);

    int insert(TbOrder record);

    int insertSelective(TbOrder record);

    List<TbOrder> selectByExample(TbOrderQuery example);

    TbOrder selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") TbOrder record, @Param("example") TbOrderQuery example);

    int updateByExample(@Param("record") TbOrder record, @Param("example") TbOrderQuery example);

    int updateByPrimaryKeySelective(TbOrder record);

    int updateByPrimaryKey(TbOrder record);
}