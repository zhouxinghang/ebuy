package com.ebuy.sso.service;

import com.ebuy.common.pojo.EbuyResult;
import com.ebuy.pojo.TbUser;
import com.ebuy.pojo.TbUserReg;

/**
 * Created by admin on 2017/12/30.
 */
public interface UserService {

    /**
     * 注册用户时，检查数据是否可用
     * @param data
     * @param type
     * @return
     */
    EbuyResult checkData(String data, int type);
    EbuyResult register(TbUser user);
    EbuyResult login(String username, String password);
    EbuyResult getUserByToken(String token);



    TbUserReg getByUserName(String username);

    int activeUser(String username);
}
