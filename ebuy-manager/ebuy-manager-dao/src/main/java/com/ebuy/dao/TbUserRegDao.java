package com.ebuy.dao;

import com.ebuy.pojo.TbUserReg;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by zhouxinghang on 2018/5/2.
 */
public interface TbUserRegDao {

    String TABLE = "tb_user_reg";

    String COLL_ALL = "username, code";

    @Select(" select "
        + COLL_ALL
        + " from "
        + TABLE
        + " where "
        + " username = #{username}")
    List<TbUserReg> selectByUserName(@Param("username") String username);

    @Insert("insert into "
        + " tb_user_reg(username,code) "
        + " values "
        + " (#{username},#{code})")
    int insert(@Param("username") String username, @Param("code") String code);

    @Select(" select "
            + COLL_ALL
            + " from "
            + TABLE
    )
    List<TbUserReg> listUser();

    @Update(" update "
        + " tb_user "
        + " set "
        + " status = #{status}"
        + " where "
        + " username = #{username} "
    )
    int updateStatus(@Param("status") int status, @Param("username") String username);
}
