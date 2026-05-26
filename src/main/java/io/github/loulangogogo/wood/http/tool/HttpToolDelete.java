package io.github.loulangogogo.wood.http.tool;

import io.github.loulangogogo.wood.http.enums.HttpMethod;
import io.github.loulangogogo.wood.http.ask.HttpRequestTool;
import io.github.loulangogogo.wood.http.ask.HttpResponseTool;
import okhttp3.Response;

import java.time.Duration;
import java.util.Map;

/*********************************************************
 ** http请求的delete工具类
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
class HttpToolDelete {

    /**
     * delete请求
     *
     * @param url 请求的url
     * @return 请求体的字符串数据
     * @author :loulan
     */
    public static String toStr(String url) {
        return toStr(url, null, null);
    }

    /**
     * delete请求
     *
     * @param url 请求的url
     * @return 响应对象
     * @author :loulan
     */
    public static Response request(String url) {
        return request(url, null, null);
    }

    /**
     * delete请求
     *
     * @param url    请求的url
     * @param params 请求的参数
     * @return 请求体的字符串数据
     * @author :loulan
     */
    public static String toStr(String url, Map<String, String> params) {
        return toStr(url, params, null);
    }

    /**
     * delete请求
     *
     * @param url    请求的url
     * @param params 请求的参数
     * @return 响应对象
     * @author :loulan
     */
    public static Response request(String url, Map<String, String> params) {
        return request(url, params, null);
    }

    /**
     * delete请求
     *
     * @param url     请求的url
     * @param params  请求的参数
     * @param headers 请求头
     * @return 请求体的字符串数据
     * @author :loulan
     */
    public static String toStr(String url, Map<String, String> params, Map<String, String> headers) {
        Response responese = request(url, params, headers);
        return HttpResponseTool.toStr(responese);
    }

    /**
     * delete请求
     *
     * @param url     请求的url
     * @param params  请求的参数
     * @param headers 请求头
     * @return 响应对象
     * @author :loulan
     */
    public static Response request(String url, Map<String, String> params, Map<String, String> headers) {
        return HttpRequestTool.request(url, HttpMethod.DELETE, headers, params, null);
    }

    /**
     * delete请求
     *
     * @param url     请求的url
     * @param params  请求的参数
     * @param headers 请求头
     * @param timeout 请求超时时间
     * @param isLog   是否打印请求日志
     * @return 响应对象
     * @author :loulan
     */
    public static Response request(String url, Map<String, String> params, Map<String, String> headers, Duration timeout, Boolean isLog) {
        return HttpRequestTool.request(url, HttpMethod.DELETE, headers, params, null, timeout, isLog);
    }
}
