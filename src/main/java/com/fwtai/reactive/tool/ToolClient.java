package com.fwtai.reactive.tool;

import com.alibaba.fastjson.JSONObject;
import com.fwtai.reactive.config.ConfigFile;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ToolClient{


    /**
     * 查询时得到的数据为空返回的json字符串
     * @作者 田应平
     * @返回值类型 String
     * @创建时间 2017年1月11日 下午9:40:21
     * @QQ号码 444141300
     * @主页 http://www.fwtai.com
    */
    public static String queryEmpty(){
        return createJson(ConfigFile.code201,ConfigFile.msg201);
    }

    /**
     * 生成json字符串对象,直接采用JSONObject封装,执行效率会更高;适用于为增、删、改操作时,判断当前的rows是否大于0来确定是否操作成功,一般在service调用,偷懒的人可以使用本方法
     * @param rows 执行后受影响的行数
     * @用法 解析后判断data.code == AppKey.code.code200即可操作
     * @作者 田应平
     * @创建时间 2016年12月25日 下午5:44:23
     * @QQ号码 444141300
     * @官网 http://www.fwtai.com
    */
    public static String executeRows(final int rows){
        if(rows > 0){
            return jsonData(ConfigFile.msg200,rows);
        }else{
            return createJsonFail(ConfigFile.msg199);
        }
    }

    /**
     * 操作成功生成json字符串对象,失败信息是ConfigFile.msg199,直接采用JSONObject封装,执行效率会更高;适用于为增、删、改操作,一般在service调用
     * @param
     * @作者 田应平
     * @QQ 444141300
     * @创建时间 2020/1/19 11:31
    */
    public static String executeRows(final int rows,final String success){
        if(rows > 0){
            return jsonData(success,rows);
        }else{
            return createJsonFail(ConfigFile.msg199);
        }
    }

    /**
     * code=200的成功json数据格式
     * @param msg code=200时提示的信息
     * @param data key的data的数据对象
     * @作者 田应平
     * @QQ 444141300
     * @创建时间 2021/3/31 14:28
    */
    protected static String jsonData(final String msg,final Object data){
        final JSONObject json = new JSONObject(3);
        json.put(ConfigFile.code,ConfigFile.code200);
        json.put(ConfigFile.msg,msg);
        json.put(ConfigFile.data,data);
        return json.toJSONString();
    }

    public static String queryData(final Object data){
        return jsonData("操作成功",data);
    }

    /**
     * 生成自定义的json对象,直接采用JSONObject封装,执行效率会更高;适用于为增、删、改操作,一般在service调用
     * @param rows 执行后受影响的行数
     * @param success 执行成功的提示消息
     * @param failure 执行失败的提示消息
     * @作者 田应平
     * @创建时间 2016年12月25日 下午5:50:22
     * @QQ号码 444141300
     * @官网 http://www.fwtai.com
    */
    public static String executeRows(final int rows,final String success,final String failure){
        if(rows > 0){
            return jsonData(success,rows);
        }else{
            return createJsonFail(failure);
        }
    }

    /**
     * 生成json格式字符串,code和msg的key是固定的,直接采用JSONObject封装,执行效率会更高,用于增、删、改、查操作,一般在service层调用
     * @作者 田应平
     * @返回值类型 返回的是json字符串,内部采用JSONObject封装
     * @用法 解析后判断data.code == AppKey.code.code200即可处理操作
     * @创建时间 2016年12月25日 18:11:16
     * @QQ号码 444141300
     * @param code 相关参数协议
     * @主页 http://www.fwtai.com
    */
    public static String createJson(final int code,final String msg){
        final JSONObject json = new JSONObject(2);
        json.put(ConfigFile.code,code);
        json.put(ConfigFile.msg,msg);
        return json.toJSONString();
    }

    /**
     * 生成code为199的json格式数据且msg是提示信息
     * @param
     * @作者 田应平
     * @QQ 444141300
     * @创建时间 2019/7/29 15:00
    */
    public static String createJsonFail(final String msg){
        return createJson(ConfigFile.code199,msg);
    }

    /**
     * 生成code为200的json格式数据且msg是提示信息
     * @param
     * @作者 田应平
     * @QQ 444141300
     * @创建时间 2019/7/29 15:00
    */
    public static String createJsonSuccess(final String msg){
        return createJson(ConfigFile.code200,msg);
    }

    /**
     * 验证必要的参数字段是否为空的返回json格式专用,先调用方法validateField()返回值false后再直接调用本方法返回json字符串
     * @作者 田应平
     * @返回值类型 返回的是json字符串,内部采用JSONObject封装
     * @创建时间 2017年1月11日 下午7:38:48
     * @QQ号码 444141300
     * @主页 http://www.fwtai.com
    */
    public static String jsonValidateField(){
        return createJson(ConfigFile.code202,ConfigFile.msg202);
    }

    /**
     * 用于生成出现异常信息时的json固定code:204字符串提示,返回给controller层调用,一般在service层调用
     * @作者 田应平
     * @返回值类型 String,内部采用JSONObject封装,msg 为系统统一的‘系统出现错误’
     * @创建时间 2017年1月10日 21:40:23
     * @QQ号码 444141300
     * @主页 http://www.fwtai.com
    */
    public static String exceptionJson(){
        return exceptionJson(ConfigFile.msg204);
    }

    /**
     * 用于生成出现异常信息时的json固定code:204字符串提示,返回给controller层调用,一般在service层调用
     * @param msg 自定义提示的异常信息
     * @作者 田应平
     * @返回值类型 String,内部采用JSONObject封装
     * @创建时间 2017年1月10日 21:40:23
     * @QQ号码 444141300
     * @主页 http://www.fwtai.com
    */
    public static String exceptionJson(final String msg){
        return createJson(ConfigFile.code204,msg);
    }

    /**
     * 未登录提示信息,json格式
     * @作者 田应平
     * @创建时间 2017年1月14日 上午12:46:08
     * @QQ号码 444141300
     * @官网 http://www.fwtai.com
    */
    public static String jsonNotLogin(){
        return createJson(ConfigFile.code205,ConfigFile.msg205);
    }

    /**
     * 不推荐,获取表单的请求参数,不含文件域,返回的是HashMap<String,String>,若要返回 PageFormData 直接使用 new PageFormData(request);
     * @param request
     * @作者:田应平
     * @创建时间 2019年11月13日 19:14:15
     * @主页 www.fwtai.com
    */
    public static HashMap<String,String> getFormParams(final ServerHttpRequest request){
        final HashMap<String,String> params = new HashMap<String,String>();
        abstractMap(request,params);
        return params;
    }

    /**
     * 获取表单的请求参数,不含文件域,返回的是线程安全的ConcurrentHashMapString,String>
     * @param request
     * @作者 田应平
     * @QQ 444141300
     * @创建时间 2019/11/13 19:29
    */
    public static ConcurrentHashMap<String,String> getFormParam(final ServerHttpRequest request){
        final ConcurrentHashMap<String,String> params = new ConcurrentHashMap<String,String>();
        abstractMap(request,params);
        return params;
    }

    protected static void abstractMap(final ServerHttpRequest request,final AbstractMap<String,String> map){
        final MultiValueMap<String,String> queryParams = request.getQueryParams();
        for(final String key : queryParams.keySet()){
            if(key.equals("_"))continue;
            String value = queryParams.getFirst(key);
            if(value != null && value.length() >0){
                value = value.trim();
                if(checkNull(value))
                    continue;
                map.put(key,value);
            }
        }
    }

    private static boolean checkNull(final String value){
        if(value.length() <= 0)return true;
        if(value.equals("_"))return true;
        if(value.equalsIgnoreCase("undefined"))return true;
        if(value.equalsIgnoreCase("null"))return true;
        return false;
    }

    // todo 解决IE8请求时出现下载的bug,推荐使用
    public static Mono<Void> responseJson(final String json,final ServerHttpResponse response){
        response.getHeaders().add("Content-Type","text/html;charset=utf-8");
        final DataBuffer db = response.bufferFactory().wrap(json.getBytes());
        return response.writeWith(Mono.just(db));
    }

    /**
     * 基于注解且仅在在controller层调用,结合 MediaType 是ok
     * @param json 是字符串,本方法会处理成json格式字符串
     * @作者 田应平
     * @QQ 444141300
     * @创建时间 2021/2/7 17:29
    */
    public static Mono<String> responseJson(final String json){
        if(json == null || json.isEmpty()){
            return Mono.justOrEmpty(queryEmpty());
        }
        return Mono.justOrEmpty(createJsonSuccess(json));
    }

    public static String validateField(final Map<String,?> params,final String... fields){
        if(params == null || params.size() <= 0) return jsonValidateField();
        for (final String value : fields){
            final Object object = params.get(value);
            if(object == null){
                return jsonValidateField();
            }else{
                final boolean bl = checkNull(String.valueOf(object));
                if(bl){
                    return jsonValidateField();
                }
            }
        }
        return null;
    }
}