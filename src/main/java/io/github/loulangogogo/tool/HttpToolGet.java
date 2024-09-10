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
public class HttpToolGet {

    /**
     * get请求
     *
     * @param url 请求的url,不能为空
     * @return 响应体的字符串数据
     * @author :loulan
     */
    public static String toStr(String url) {
        return toStr(url, null, null);
    }

    /**
     * get请求
     *
     * @param url 请求的url,不能为空
     * @return 响应体的字节数组
     * @author :loulan
     */
    public static byte[] toByteArray(String url) {
        return toByteArray(url, null, null);
    }

    /**
     * get请求
     *
     * @param url 请求的url,不能为空
     * @return 响应对象的输入流
     * @author :loulan
     */
    public static InputStream toInputStream(String url) {
        return toInputStream(url, null, null);
    }

    /**
     * get请求
     *
     * @param url 请求的url,不能为空
     * @return 响应对象
     * @author :loulan
     */
    public static Response request(String url) {
        return request(url, null, null);
    }

    /**
     * get请求
     *
     * @param url    请求的url,不能为空
     * @param params 请求的参数
     * @return 响应体的字符串数据
     * @author :loulan
     */
    public static String toStr(String url, Map<String, String> params) {
        return toStr(url, params, null);
    }

    /**
     * 将给定的URL和参数转换为字节数组
     * 此方法重载了toByteArray方法，当params为null时调用自身并传入null的timeout参数
     *
     * @param url    请求的URL
     * @param params 请求参数的键值对映射
     * @return 返回的响应内容，以字节数组形式表示
     * @author loulan
     */
    public static byte[] toByteArray(String url, Map<String, String> params) {
        return toByteArray(url, params, null);
    }

    /**
     * 将给定的URL和参数转换为InputStream
     * 此方法重载了带有参数headers的toInputStream方法，为调用者提供了无需指定headers的简化方式
     *
     * @param url    请求的URL字符串
     * @param params 请求的参数，键值对形式
     * @return 转换后的InputStream对象
     * @author loulan
     */
    public static InputStream toInputStream(String url, Map<String, String> params) {
        return toInputStream(url, params, null);
    }

    /**
     * 发起网络请求的简化方法
     * 此方法作为请求封装，针对没有请求头参数的简单场景进行了简化
     *
     * @param url    请求的URL地址，决定了请求的目标资源
     * @param params 请求的参数，以键值对形式传递
     * @return 返回一个Response对象，包含服务器响应的所有信息
     * @author :loulan
     */
    public static Response request(String url, Map<String, String> params) {
        return request(url, params, null);
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
    public static String toStr(String url, Map<String, String> params, Map<String, String> headers) {
        Response responese = request(url, params, headers);
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
    public static byte[] toByteArray(String url, Map<String, String> params, Map<String, String> headers) {
        Response responese = request(url, params, headers);
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
    public static InputStream toInputStream(String url, Map<String, String> params, Map<String, String> headers) {
        Response responese = request(url, params, headers);
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
    public static Response request(String url, Map<String, String> params, Map<String, String> headers) {
        Response responese = HttpRequestTool.request(url, HttpMethod.GET, headers, params, null);
        return responese;
    }
}
