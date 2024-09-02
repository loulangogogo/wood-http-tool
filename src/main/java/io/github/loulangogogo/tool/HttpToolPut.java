package io.github.loulangogogo.tool;

import io.github.loulangogogo.enums.HttpMethod;
import io.github.loulangogogo.ask.HttpRequestTool;
import io.github.loulangogogo.ask.HttpResponseTool;
import okhttp3.Response;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/*********************************************************
 ** http请求的put工具类
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
class HttpToolPut {

    /**
     * 向指定的URL发送PUT请求，并接收字符串响应
     * 此方法重载为调用者提供方便
     *
     * @param url 目标服务器的URL，不能为空
     * @return 服务器的响应，作为字符串
     */
    public static String toStr(String url) {
        return toStr(url, null);
    }

    /**
     * 向指定的URL发送PUT请求，并接收字节数组响应
     * 此方法重载为调用者提供方便
     *
     * @param url 目标服务器的URL，不能为空
     * @return 服务器的响应，作为字节数组
     */
    public static byte[] toByteArray(String url) {
        return toByteArray(url, null);
    }

    /**
     * 向指定的URL发送PUT请求，并接收输入流响应
     * 此方法重载为调用者提供方便
     *
     * @param url 目标服务器的URL，不能为空
     * @return 服务器的响应，作为输入流
     */
    public static InputStream toInputStream(String url) {
        return toInputStream(url, null);
    }

    /**
     * 向指定的URL发送PUT请求，并接收响应对象
     * 此方法重载为调用者提供方便
     *
     * @param url 目标服务器的URL，不能为空
     * @return 响应对象，包含服务器的响应信息
     */
    public static Response request(String url) {
        return request(url, null);
    }


    /**
     * 使用默认消息转换器执行PUT请求，并返回响应字符串
     * 此方法重载了带转换器的toStr方法
     *
     * @param url 请求的URL,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @return 响应字符串
     */
    public static String toStr(String url, Map<String, String> headers) {
        return toStr(url, headers, null);
    }

    /**
     * 使用默认消息转换器执行PUT请求，并返回响应的字节数组
     * 此方法重载了带转换器的toByteArray方法
     *
     * @param url 请求的URL,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @return 响应的字节数组
     */
    public static byte[] toByteArray(String url, Map<String, String> headers) {
        return toByteArray(url, headers, null);
    }

    /**
     * 使用默认消息转换器执行PUT请求，并返回响应的输入流
     * 此方法重载了带转换器的toInputStream方法
     *
     * @param url 请求的URL,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @return 响应的输入流
     */
    public static InputStream toInputStream(String url, Map<String, String> headers) {
        return toInputStream(url, headers, null);
    }

    /**
     * 使用默认消息转换器执行PUT请求，并返回响应对象
     * 此方法重载了带转换器的request方法
     *
     * @param url 请求的URL,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @return 响应对象
     */
    public static Response request(String url, Map<String, String> headers) {
        return request(url, headers, null);
    }


    /**
     * 使用默认配置执行PUT请求，并返回响应的字符串表示
     * 此方法重载了具有更多自定义选项的toStr方法
     *
     * @param url     请求的url,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @param body    请求的正文内容,可以为null
     * @return 返回响应的字符串表示
     */
    public static String toStr(String url, Map<String, String> headers, String body) {
        return toStr(url, null, headers, body);
    }

    /**
     * 使用默认配置执行PUT请求，并返回响应的字节数组
     * 此方法重载了具有更多自定义选项的toByteArray方法
     *
     * @param url     请求的url,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @param body    请求的正文内容,可以为null
     * @return 返回响应的字节数组表示
     */
    public static byte[] toByteArray(String url, Map<String, String> headers, String body) {
        return toByteArray(url, null, headers, body);
    }

    /**
     * 使用默认配置执行PUT请求，并返回响应的输入流
     * 此方法重载了具有更多自定义选项的toInputStream方法
     *
     * @param url     请求的url,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @param body    请求的正文内容,可以为null
     * @return 返回响应的输入流表示
     */
    public static InputStream toInputStream(String url, Map<String, String> headers, String body) {
        return toInputStream(url, null, headers, body);
    }

    /**
     * 使用默认配置执行PUT请求，并返回完整的响应信息
     * 此方法重载了具有更多自定义选项的request方法
     *
     * @param url     请求的url,不能为空
     * @param headers 请求头信息，如果为null，则不添加任何请求头
     * @param body    请求的正文内容,可以为null
     * @return 返回完整的响应信息对象
     */
    public static Response request(String url, Map<String, String> headers, String body) {
        return request(url, null, headers, body);
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
    public static String toStr(String url, Map<String, String> params, Map<String, String> headers, String body) {
        Response responese = request(url, params, headers, body);
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
    public static byte[] toByteArray(String url, Map<String, String> params, Map<String, String> headers, String body) {
        Response responese = request(url, params, headers, body);
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
    public static InputStream toInputStream(String url, Map<String, String> params, Map<String, String> headers, String body) {
        Response responese = request(url, params, headers, body);
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
    public static Response request(String url, Map<String, String> params, Map<String, String> headers, String body) {
        return HttpRequestTool.request(url, HttpMethod.PUT, headers, params, body);
    }

    /**
     * 上传文件的put的请求
     *
     * @param url      请求的url,不能为空
     * @param headers  请求头
     * @param bodyFile 上传的文件字节数组
     * @param fileName 文件名
     * @param bodyName 请求体名称
     * @return 响应对象
     * @author :loulan
     */
    public static Response uploadFile(String url, Map<String, String> headers, byte[] bodyFile, String fileName, String bodyName) {
        return uploadFile(url, null, headers, bodyFile, fileName, bodyName);
    }

    /**
     * 上传文件的put的请求
     *
     * @param url      请求的url,不能为空
     * @param params   请求的参数
     * @param headers  请求头
     * @param bodyFile 上传的文件字节数组
     * @param fileName 文件名
     * @param bodyName 请求体名称
     * @return 响应对象
     * @author :loulan
     */
    public static Response uploadFile(String url, Map<String, String> params, Map<String, String> headers, byte[] bodyFile, String fileName, String bodyName) {
        return HttpRequestTool.uploadFile(url, HttpMethod.PUT, headers, params, bodyFile, fileName, bodyName);
    }

    /**
     * 上传文件的put的请求
     *
     * @param url      请求的url,不能为空
     * @param headers  请求头
     * @param bodyFile 上传的文件
     * @param bodyName 请求体名称
     * @return 响应对象
     * @author :loulan
     */
    public static Response uploadFile(String url, Map<String, String> headers, File bodyFile, String bodyName) {
        return uploadFile(url, null, headers, bodyFile, bodyName);
    }

    /**
     * 上传文件的put的请求
     *
     * @param url      请求的url,不能为空
     * @param params   请求的参数
     * @param headers  请求头
     * @param bodyFile 上传的文件
     * @param bodyName 请求体名称
     * @return 响应对象
     * @author :loulan
     */
    public static Response uploadFile(String url, Map<String, String> params, Map<String, String> headers, File bodyFile, String bodyName) {
        return HttpRequestTool.uploadFile(url, HttpMethod.PUT, headers, params, bodyFile, bodyName);
    }
}
