package com.lmk.core.web.monitor.service;

import com.lmk.core.web.auth.bean.LoginUser;
import com.lmk.core.web.monitor.dto.LogParams;
import com.lmk.core.web.support.bean.http.ClientInfo;

/**
 * 基础日志服务
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public interface BaseLogService {

    /**
     * 记录日志
     * @param logInfo
     * @param user
     * @param client
     */
    void log(LogParams logInfo, LoginUser user, ClientInfo client);

}
