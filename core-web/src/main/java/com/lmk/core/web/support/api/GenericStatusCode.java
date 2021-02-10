package com.lmk.core.web.support.api;

/**
 * 通用请求状态码
 * 系统级：11
 * 表单类：12
 * 用户类：13
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class GenericStatusCode {

    public static final StatusCode Success = new StatusCode(0, "请求成功");
    public static final StatusCode Busy = new StatusCode(-1, "系统繁忙");

    ////////////  系统级错误以11开头的6位数字 ////////////
    public static final StatusCode ParameterError = new StatusCode(110001, "参数错误");
    public static final StatusCode VerifyCodeError = new StatusCode(110002, "验证码错误");
    public static final StatusCode ServerError = new StatusCode(110003, "服务器端异常");

    //////////// 通用表单类错误以12开头的6位数字 ////////////
    public static final StatusCode FormIdError = new StatusCode(120001, "ID不可用");
    public static final StatusCode FormCodeError = new StatusCode(120002, "代码不可用");
    public static final StatusCode FormTypeError = new StatusCode(120003, "类型不可用");
    public static final StatusCode FormParentError = new StatusCode(120004, "上级不可用");
    public static final StatusCode FormCategoryError = new StatusCode(120005, "分类不可用");
    public static final StatusCode FormTitleError = new StatusCode(120006, "名称不可用");
    public static final StatusCode FormStatusError = new StatusCode(120007, "状态不可用");

    ////////////  用户类错误以13开头的6位数字 ////////////
    public static final StatusCode NoLogin = new StatusCode(130001, "未登录");
    public static final StatusCode NoRole = new StatusCode(130002, "没有角色");
    public static final StatusCode NoPermission = new StatusCode(110003, "没有权限");
    public static final StatusCode AccountDisabled = new StatusCode(130004, "账户不可用");
    public static final StatusCode UsernameError = new StatusCode(130005, "用户名错误");
    public static final StatusCode PasswordError = new StatusCode(130006, "密码错误");
    public static final StatusCode UsernameDisabled = new StatusCode(130007, "用户名不可用");
}
