package com.lmk.core.web.auth.service;

import com.lmk.core.web.support.api.RequestResult;
import com.lmk.core.web.support.api.StatusCode;
import com.lmk.core.commons.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 鉴权服务接口
 */
public interface AuthService {

    /**
     * 获取访问令牌
     * @param request
     * @return
     */

    default String getToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }

    /**
     * 返回错误信息
     * @param response
     * @param httpStatus
     * @param statusCode
     */
    default void writeError(HttpServletResponse response, int httpStatus, StatusCode statusCode){
        response.setStatus(httpStatus);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        RequestResult result = new RequestResult(statusCode);
        try {
            response.getWriter().write(JsonUtils.toJSON(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
