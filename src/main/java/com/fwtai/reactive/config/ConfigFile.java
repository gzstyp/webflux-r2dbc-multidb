package com.fwtai.reactive.config;

public final class ConfigFile{

    public final static String ACCESS_TOKEN = "accessToken";

    public final static String REFRESH_TOKEN = "refreshToken";

    public final static String api_v10 = "/api/v1.0/";

    public final static String push_v10 = "/push/v1.0/";

    public final static String title = "<title>系统提示</title>";

    /**自定义code及msg*/
    public final static int code198 = 198;
    /**操作失败*/
    public final static int code199 = 199;
    /**操作失败*/
    public final static String msg199 = "操作失败";
    /**操作成功*/
    public final static int code200 = 200;
    /**操作成功*/
    public final static String msg200 = "操作成功";
    /**暂无数据*/
    public final static int code201 = 201;
    /**暂无数据*/
    public final static String msg201 = "暂无数据";
    /**请求参数不完整*/
    public final static int code202 = 202;
    /**请求参数不完整*/
    public final static String msg202 = "请求参数不完整";
    /**token验证失败*/
    public final static int code203 = 203;
    /**token验证失败*/
    public final static String msg203 = "token验证失败";
    /**系统出现错误*/
    public final static int code204 = 204;
    /**系统出现错误*/
    public final static String msg204 = "系统出现错误";
    /**未登录或登录超时或token已失效*/
    public final static int code205 = 205;
    /**未登录或登录超时或token已失效*/
    public final static String msg205 = "未登录或登录超时!";
    /**账号或密码不正确*/
    public final static int code206 = 206;
    /**账号或密码不正确*/
    public final static String msg206 = "账号或密码错误";
    /**非法操作!或你的账号已被删除|你的账号已被禁用,一般用于被迫强制退出登录*/
    public final static int code207 = 207;
    /**没有操作权限*/
    public final static int code401 = 401;
    /**菜单的最顶级的pid值*/
    public final static String pid = "88888888888888888888888888888888";
    /**没有操作权限*/
    public final static String msg401 = "没有操作权限";
    /**token无效或已过期,请重新登录*/
    public final static String TOKEN_INVALID = "token无效或已过期,请重新登录";
    /**你的账号已被删除*/
    public final static String msg207 = "你的账号已被删除";
    /**分页的区间标识符*/
    public final static String section = "section";
    /**自定义全局变量登录者的权限信息*/
    public final static String LOGIN_PERMISSION = "login_permission";
    /**自定义全局变量最高权限及最最超级系统管理员admin*/
    public final static String KEY_SUPER = "admin";
    /**自定义全局变量登录者的账号*/
    public final static String LOGIN_PAGE = "/login.html";
    /**自定义全局变量切换登录的switcher_login_key主键标识*/
    public final static String switcher_login_key = "switcher_login_key";
    /**自定义全局变量切换登录的switcher_login_user登录账号标识*/
    public final static String switcher_login_user = "switcher_login_user";
    /**定义全局变量图形验证码标识,便于登录验证图形验证码*/
    public final static String imageCode = "imageCode";
    /**未登录或登录超时跳转到的jsp页面*/
    public final static String TIMEOUT = "timeout";
    /**统一全局的total总条数|总记录数json关键字key响应给客户端*/
    public final static String total = "total";
    /**统一全局的totalPage总页数json关键字key响应给客户端*/
    public final static String totalPage = "totalPage";
    /**统一全局的Easyui里的datagrid数据返回json关键字key响应给客户端*/
    public final static String rows = "rows";
    /**统一全局的code的状态码json关键字key关键字响应给客户端*/
    public final static String code = "code";
    /**统一全局的msg提示消息json关键字key响应给客户端*/
    public final static String msg = "msg";
    /**统一全局的pageSize每页大小json关键字key响应给客户端或作为Mybatis的分页参数*/
    public final static String pageSize = "pageSize";
    /**统一全局的result返回数据(含分页的数据)json关键字key响应给客户端*/
    public final static String data = "data";
    /**每次返回的条数*/
    public final static String record = "record";
    /**返回权限数据json关键字key响应给客户端*/
    public final static String permissions = "permissions";
    /**datatable的总记录数*/
    public final static String recordsFiltered = "recordsFiltered";
    /**datatable的总记录数*/
    public final static String recordsTotal = "recordsTotal";
    /**统一全局的current当前页json关键字key响应给客户端*/
    public final static String current = "current";
    /**分页的默认大小*/
    public final static int size_default = 50;
    /**api分页的默认限制大小*/
    public final static int size_api_default = 50;
    /**统一全局文件上传目录-根目录*/
    public final static String dir_root = "filedir";
    /**统一全局文件上传目录-图片目录*/
    public final static String dir_image = "image";
    /**统一全局文件上传目录-一般文件目录*/
    public final static String dir_file = "file";
    /**拦截器表达式,配置不拦截的url资源,以括号(xxx)什么开头的都不拦截;即不对匹配该值的访问路径拦截（正则）*/
    public final static String expression = ".*/((app)|(weChat)|(api)).*";
}