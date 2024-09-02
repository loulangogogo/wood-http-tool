package io.github.loulangogogo.tool;

import io.github.loulangogogo.enums.HttpMethod;
import io.github.loulangogogo.ask.HttpRequestTool;
import io.github.loulangogogo.ask.HttpResponseTool;
import okhttp3.Response;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/*********************************************************
 ** http请求的post工具类
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
class HttpToolPost {


    /**
     * 向指定URL发送POST请求，并接收字符串形式的响应
     * 此方法重载了自身，为调用者提供了简化的方式，无需关心请求头和参数的细节
     *
     * @param url 目标服务器的URL
     * @return 服务器返回的响应字符串
     */
    public static String toStr(String url) {
        return toStr(url, null, null, null);
    }


    /**
     * 使用默认参数向指定URL发送POST请求，并返回响应的字节数组
     * 此方法重载了具有更多参数的toByteArray方法，以便在不需要额外参数时简化调用
     *
     * @param url 目标服务器的URL
     * @return 从服务器接收到的响应的字节数组
     */
    public static byte[] toByteArray(String url) {
        return toByteArray(url, null, null, null);
    }

    /**
     * 使用默认参数向指定URL发送POST请求，并返回响应的输入流
     * 此方法重载了具有更多参数的toInputStream方法，以便在不需要额外参数时简化调用
     *
     * @param url 目标服务器的URL
     * @return 从服务器接收到的响应的输入流
     */
    public static InputStream toInputStream(String url) {
        return toInputStream(url, null, null, null);
    }

    /**
     * 使用默认参数向指定URL发送POST请求，并返回包含响应的Response对象
     * 此方法重载了具有更多参数的request方法，以便在不需要额外参数时简化调用
     *
     * @param url 目标服务器的URL
     * @return 包含服务器响应的Response对象
     */
    public static Response request(String url) {
        return request(url, null, null, null);
    }

    /**
     * 使用指定的URL和头部信息执行一个POST请求，返回响应的字符串表示
     * 此方法重载了toStr方法，简化调用时不需要提供body和额外参数
     *
     * @param url     请求的URL
     * @param headers 请求的头部信息，如果不需要可以传入null
     * @return 服务器响应的字符串表示
     */
    public static String toStr(String url, Map<String, String> headers) {
        return toStr(url, null, headers, null);
    }

    /**
     * 使用指定的URL和头部信息执行一个POST请求，返回响应的字节数组
     * 此方法重载了toByteArray方法，简化调用时不需要提供body和额外参数
     *
     * @param url     请求的URL
     * @param headers 请求的头部信息，如果不需要可以传入null
     * @return 服务器响应的字节数组
     */
    public static byte[] toByteArray(String url, Map<String, String> headers) {
        return toByteArray(url, null, headers, null);
    }

    /**
     * 使用指定的URL和头部信息执行一个POST请求，返回响应的输入流
     * 此方法重载了toInputStream方法，简化调用时不需要提供body和额外参数
     *
     * @param url     请求的URL
     * @param headers 请求的头部信息，如果不需要可以传入null
     * @return 服务器响应的输入流
     */
    public static InputStream toInputStream(String url, Map<String, String> headers) {
        return toInputStream(url, null, headers, null);
    }

    /**
     * 使用指定的URL和头部信息执行一个POST请求，返回完整的响应对象
     * 此方法重载了request方法，简化调用时不需要提供body和额外参数
     *
     * @param url     请求的URL
     * @param headers 请求的头部信息，如果不需要可以传入null
     * @return 完整的服务器响应对象
     */
    public static Response request(String url, Map<String, String> headers) {
        return request(url, null, headers, null);
    }

    /**
     * 使用指定的URL和请求头发送POST请求，并返回字符串响应
     * 此重载方法将body作为请求正文发送
     *
     * @param url     请求的URL
     * @param headers 请求头信息
     * @param body    请求的正文
     * @return 服务器响应的字符串
     */
    public static String toStr(String url, Map<String, String> headers, String body) {
        return toStr(url, null, headers, body);
    }

    /**
     * 使用指定的URL和请求头发送POST请求，并返回字节数组响应
     * 此重载方法将body作为请求正文发送
     *
     * @param url     请求的URL
     * @param headers 请求头信息
     * @param body    请求的正文
     * @return 服务器响应的字节数组
     */
    public static byte[] toByteArray(String url, Map<String, String> headers, String body) {
        return toByteArray(url, null, headers, body);
    }

    /**
     * 使用指定的URL和请求头发送POST请求，并返回输入流响应
     * 此重载方法将body作为请求正文发送
     *
     * @param url     请求的URL
     * @param headers 请求头信息
     * @param body    请求的正文
     * @return 服务器响应的输入流
     */
    public static InputStream toInputStream(String url, Map<String, String> headers, String body) {
        return toInputStream(url, null, headers, body);
    }

    /**
     * 使用指定的URL和请求头发送POST请求，并返回Response对象
     * 此重载方法将body作为请求正文发送
     *
     * @param url     请求的URL
     * @param headers 请求头信息
     * @param body    请求的正文
     * @return 包含服务器响应的Response对象
     */
    public static Response request(String url, Map<String, String> headers, String body) {
        return request(url, null, headers, body);
    }

    /**
     * post请求
     *
     * @param url     请求的url,不能为空
     * @param params  请求的参数
     * @param headers 请求头
     * @param body    请求体的json数据
     * @return 请求体的字符串数据
     * @author :loulan
     */
    public static String toStr(String url, Map<String, String> params, Map<String, String> headers, String body) {
        Response responese = request(url, params, headers, body);
        return HttpResponseTool.toStr(responese);
    }

    /**
     * post请求
     *
     * @param url     请求的url,不能为空
     * @param params  请求的参数
     * @param headers 请求头
     * @param body    请求体的json数据
     * @return 响应体字节数组
     * @author :loulan
     */
    public static byte[] toByteArray(String url, Map<String, String> params, Map<String, String> headers, String body) {
        Response responese = request(url, params, headers, body);
        return HttpResponseTool.toByteArray(responese);
    }

    /**
     * post请求
     *
     * @param url     请求的url,不能为空
     * @param params  请求的参数
     * @param headers 请求头
     * @param body    请求体的json数据
     * @return 响应输入流
     * @author :loulan
     */
    public static InputStream toInputStream(String url, Map<String, String> params, Map<String, String> headers, String body) {
        Response responese = request(url, params, headers, body);
        return HttpResponseTool.toInputStream(responese);
    }

    /**
     * post请求
     *
     * @param url     请求的url,不能为空
     * @param params  请求的参数
     * @param headers 请求头
     * @param body    请求体的json数据
     * @return 响应对象
     * @author :loulan
     */
    public static Response request(String url, Map<String, String> params, Map<String, String> headers, String body) {
        return HttpRequestTool.request(url, HttpMethod.POST, headers, params, body);
    }

    /**
     * 上传文件的post的请求
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
     * 上传文件的post的请求
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
        return HttpRequestTool.uploadFile(url, HttpMethod.POST, headers, params, bodyFile, fileName, bodyName);
    }

    /**
     * 上传文件的post的请求
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
     * 上传文件的post的请求
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
        return HttpRequestTool.uploadFile(url, HttpMethod.POST, headers, params, bodyFile, bodyName);
    }
}
