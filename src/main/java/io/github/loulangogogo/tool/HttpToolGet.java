package io.github.loulangogogo.tool;

import io.github.loulangogogo.enums.HttpMethod;
import io.github.loulangogogo.ask.HttpRequestTool;
import io.github.loulangogogo.ask.HttpResponseTool;
import okhttp3.Response;

import java.io.InputStream;
import java.util.Map;

/*********************************************************
 ** http请求的get工具类
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
interface HttpToolGet {

    /**
     * get请求
     *
     * @param url 请求的url,不能为空
     * @return 响应体的字符串数据
     * @author :loulan
     */
    public static String getStr(String url) {
        return getStr(url, null, null);
    }

    /**
     * get请求
     *
     * @param url 请求的url,不能为空
     * @return 响应体的字节数组
     * @author :loulan
     */
    public static byte[] getByteArray(String url) {
        return getByteArray(url, null, null);
    }

    /**
     * get请求
     *
     * @param url 请求的url,不能为空
     * @return 响应对象的输入流
     * @author :loulan
     */
    public static InputStream getInputStream(String url) {
        return getInputStream(url, null, null);
    }

    /**
     * get请求
     *
     * @param url 请求的url,不能为空
     * @return 响应对象
     * @author :loulan
     */
    public static Response get(String url) {
        return get(url, null, null);
    }

    /**
     * get请求
     *
     * @param url     请求的url,不能为空
     * @param params  请求的参数
     * @param headers 请求头
     * @return 响应体的字符串数据
     * @author :loulan
     */
    public static String getStr(String url, Map<String, String> params, Map<String, String> headers) {
        Response responese = get(url, params, headers);
        return HttpResponseTool.toStr(responese);
    }

    /**
     * get请求
     *
     * @param url     请求的url,不能为空
     * @param params  请求的参数
     * @param headers 请求头
     * @return 响应体的字节数组
     * @author :loulan
     */
    public static byte[] getByteArray(String url, Map<String, String> params, Map<String, String> headers) {
        Response responese = get(url, params, headers);
        return HttpResponseTool.toByteArray(responese);
    }

    /**
     * get请求
     *
     * @param url     请求的url,不能为空
     * @param params  请求的参数
     * @param headers 请求头
     * @return 响应对象的输入流
     * @author :loulan
     */
    public static InputStream getInputStream(String url, Map<String, String> params, Map<String, String> headers) {
        Response responese = get(url, params, headers);
        return HttpResponseTool.toInputStream(responese);
    }

    /**
     * get请求
     *
     * @param url     请求的url,不能为空
     * @param params  请求的参数
     * @param headers 请求头
     * @return 响应对象
     * @author :loulan
     */
    public static Response get(String url, Map<String, String> params, Map<String, String> headers) {
        Response responese = HttpRequestTool.request(url, HttpMethod.GET, headers, params, null);
        return responese;
    }
}
