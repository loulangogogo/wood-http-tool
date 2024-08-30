package io.github.loulangogogo.tool;

import io.github.loulangogogo.enums.HttpMethod;
import io.github.loulangogogo.ask.HttpRequestTool;
import io.github.loulangogogo.ask.HttpResponseTool;
import okhttp3.Response;

import java.io.InputStream;
import java.util.Map;

/*********************************************************
 ** http请求的put工具类
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
interface HttpToolPut {

    /**
     * 向指定的URL发送PUT请求，并接收字符串响应
     * 此方法重载为调用者提供方便
     *
     * @param url 目标服务器的URL，不能为空
     * @return 服务器的响应，作为字符串
     */
    public static String putStr(String url) {
        return putStr(url, null);
    }

    /**
     * 向指定的URL发送PUT请求，并接收字节数组响应
     * 此方法重载为调用者提供方便
     *
     * @param url 目标服务器的URL，不能为空
     * @return 服务器的响应，作为字节数组
     */
    public static byte[] putByteArray(String url) {
        return putByteArray(url, null);
    }

    /**
     * 向指定的URL发送PUT请求，并接收输入流响应
     * 此方法重载为调用者提供方便
     *
     * @param url 目标服务器的URL，不能为空
     * @return 服务器的响应，作为输入流
     */
    public static InputStream putInputStream(String url) {
        return putInputStream(url, null);
    }

    /**
     * 向指定的URL发送PUT请求，并接收响应对象
     * 此方法重载为调用者提供方便
     *
     * @param url 目标服务器的URL，不能为空
     * @return 响应对象，包含服务器的响应信息
     */
    public static Response put(String url) {
        return put(url, null);
    }


    /**
     * 使用默认消息转换器执行PUT请求，并返回响应字符串
     * 此方法重载了带转换器的putStr方法
     *
     * @param url 请求的URL,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @return 响应字符串
     */
    public static String putStr(String url, Map<String, String> headers) {
        return putStr(url, headers, null);
    }

    /**
     * 使用默认消息转换器执行PUT请求，并返回响应的字节数组
     * 此方法重载了带转换器的putByteArray方法
     *
     * @param url 请求的URL,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @return 响应的字节数组
     */
    public static byte[] putByteArray(String url, Map<String, String> headers) {
        return putByteArray(url, headers, null);
    }

    /**
     * 使用默认消息转换器执行PUT请求，并返回响应的输入流
     * 此方法重载了带转换器的putInputStream方法
     *
     * @param url 请求的URL,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @return 响应的输入流
     */
    public static InputStream putInputStream(String url, Map<String, String> headers) {
        return putInputStream(url, headers, null);
    }

    /**
     * 使用默认消息转换器执行PUT请求，并返回响应对象
     * 此方法重载了带转换器的put方法
     *
     * @param url 请求的URL,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @return 响应对象
     */
    public static Response put(String url, Map<String, String> headers) {
        return put(url, headers, null);
    }


    /**
     * 使用默认配置执行PUT请求，并返回响应的字符串表示
     * 此方法重载了具有更多自定义选项的putStr方法
     *
     * @param url     请求的url,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @param body    请求的正文内容,可以为null
     * @return 返回响应的字符串表示
     */
    public static String putStr(String url, Map<String, String> headers, String body) {
        return putStr(url, null, headers, body);
    }

    /**
     * 使用默认配置执行PUT请求，并返回响应的字节数组
     * 此方法重载了具有更多自定义选项的putByteArray方法
     *
     * @param url     请求的url,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @param body    请求的正文内容,可以为null
     * @return 返回响应的字节数组表示
     */
    public static byte[] putByteArray(String url, Map<String, String> headers, String body) {
        return putByteArray(url, null, headers, body);
    }

    /**
     * 使用默认配置执行PUT请求，并返回响应的输入流
     * 此方法重载了具有更多自定义选项的putInputStream方法
     *
     * @param url     请求的url,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @param body    请求的正文内容,可以为null
     * @return 返回响应的输入流表示
     */
    public static InputStream putInputStream(String url, Map<String, String> headers, String body) {
        return putInputStream(url, null, headers, body);
    }

    /**
     * 使用默认配置执行PUT请求，并返回完整的响应信息
     * 此方法重载了具有更多自定义选项的put方法
     *
     * @param url     请求的url,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @param body    请求的正文内容,可以为null
     * @return 返回完整的响应信息对象
     */
    public static Response put(String url, Map<String, String> headers, String body) {
        return put(url, null, headers, body);
    }

    /**
     * put请求
     *
     * @param url     请求的url,不能为空
     * @param params  请求的参数
     * @param headers 请求头
     * @param body    请求的正文内容,可以为null
     * @return 请求体的字符串数据
     * @author :loulan
     */
    public static String putStr(String url, Map<String, String> params, Map<String, String> headers, String body) {
        Response responese = put(url, params, headers, body);
        return HttpResponseTool.toStr(responese);
    }

    /**
     * put请求
     *
     * @param url     请求的url,不能为空
     * @param params  请求的参数
     * @param headers 请求头
     * @param body    请求的正文内容,可以为null
     * @return 响应体字节数组
     * @author :loulan
     */
    public static byte[] putByteArray(String url, Map<String, String> params, Map<String, String> headers, String body) {
        Response responese = put(url, params, headers, body);
        return HttpResponseTool.toByteArray(responese);
    }

    /**
     * put请求
     *
     * @param url     请求的url,不能为空
     * @param params  请求的参数
     * @param headers 请求头
     * @param body    请求的正文内容,可以为null
     * @return 响应输入流
     * @author :loulan
     */
    public static InputStream putInputStream(String url, Map<String, String> params, Map<String, String> headers, String body) {
        Response responese = put(url, params, headers, body);
        return HttpResponseTool.toInputStream(responese);
    }

    /**
     * put请求
     *
     * @param url     请求的url,不能为空
     * @param params  请求的参数
     * @param headers 请求头
     * @param body    请求的正文内容,可以为null
     * @return 响应对象
     * @author :loulan
     */
    public static Response put(String url, Map<String, String> params, Map<String, String> headers, String body) {
        return HttpRequestTool.request(url, HttpMethod.PUT, headers, params, body);
    }
}
