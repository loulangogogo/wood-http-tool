package io.github.loulangogogo;

import io.github.loulangogogo.exception.WoodRequestException;
import io.github.loulangogogo.water.tool.AssertTool;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/*********************************************************
 ** 请求
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
public class WoodHttpRequest {

    /**
     * 上传文件的请求
     *
     * @param url      请求地址，不能为空
     * @param method   请求方法，不能为空
     * @param headers  请求头
     * @param params   请求参数
     * @param bodyFile 要上传的文件字节数组，不能为空
     * @param bodyName 请求体参数名称，不能为空
     * @return {@link Response}响应对象
     * @author :loulan
     */
    public static Response uploadFile(
            String url,
            HttpMethod method,
            Map<String, String> headers,
            Map<String, String> params,
            byte[] bodyFile,
            String fileName,
            String bodyName
    ) {
        AssertTool.notEmpty(url, "url不能为空");
        AssertTool.notNull(method, "请求方法不能为空");
        AssertTool.notNull(bodyFile, "文件不能为空");
        AssertTool.notNull(bodyName, "请求体参数名称不能为空");
        // 创建请求构建对象，并设置请求参数
        Request.Builder requestBuilder = requestBuilder(url, params);
        // 设置请求头
        WoodHttpRequestHeader.setHeader(requestBuilder, headers);
        // 设置请求方法
        WoodHttpRequestMethod.setMethod(requestBuilder, method, WoodHttpRequestBody.createFileRequestBody(bodyFile, fileName, bodyName));

        try {
            return request(requestBuilder.build());
        } catch (Exception ex) {
            throw new WoodRequestException(ex);
        }
    }


    /**
     * 上传文件的请求
     *
     * @param url      请求地址，不能为空
     * @param method   请求方法，不能为空
     * @param headers  请求头
     * @param params   请求参数
     * @param bodyFile 要上传的文件，不能为空
     * @param bodyName 请求体参数名称，不能为空
     * @return {@link Response}响应对象
     * @author :loulan
     */
    public static Response uploadFile(
            String url,
            HttpMethod method,
            Map<String, String> headers,
            Map<String, String> params,
            File bodyFile,
            String bodyName
    ) {
        AssertTool.notEmpty(url, "url不能为空");
        AssertTool.notNull(method, "请求方法不能为空");
        AssertTool.notNull(bodyFile, "文件不能为空");
        AssertTool.notNull(bodyName, "请求体参数名称不能为空");
        // 创建请求构建对象，并设置请求参数
        Request.Builder requestBuilder = requestBuilder(url, params);
        // 设置请求头
        WoodHttpRequestHeader.setHeader(requestBuilder, headers);
        // 设置请求方法
        WoodHttpRequestMethod.setMethod(requestBuilder, method, WoodHttpRequestBody.createFileRequestBody(bodyFile, bodyName));

        try {
            return request(requestBuilder.build());
        } catch (Exception ex) {
            throw new WoodRequestException(ex);
        }
    }


    /**
     * 发起请求
     *
     * @param url     请求地址，不能为空
     * @param method  请求方法，不能为空
     * @param headers 请求头
     * @param params  请求参数
     * @param body    请求体（json数据请求体）
     * @return {@link Response}响应对象
     * @author :loulan
     */
    public static Response request(
            String url,
            HttpMethod method,
            Map<String, String> headers,
            Map<String, String> params,
            String body
    ) {
        AssertTool.notEmpty(url, "url不能为空");
        AssertTool.notNull(method, "请求方法不能为空");
        // 创建请求构建对象，并设置请求参数
        Request.Builder requestBuilder = requestBuilder(url, params);
        // 设置请求头
        WoodHttpRequestHeader.setHeader(requestBuilder, headers);
        // 设置请求方法
        WoodHttpRequestMethod.setMethod(requestBuilder, method, WoodHttpRequestBody.createRequestBody(body));

        try {
            return request(requestBuilder.build());
        } catch (Exception ex) {
            throw new WoodRequestException(ex);
        }
    }

    /*************************************************************************/

    /**
     * 创建请求构建对象
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return
     * @throws
     * @author :loulan
     */
    private static Request.Builder requestBuilder(String url, Map<String, String> params) {
        AssertTool.notEmpty(url, "url不能为空");
        // 创建请求构建对象
        return new Request.Builder()
                .url(WoodHttpRequestUrl.createUrl(url, params));
    }


    /**
     * 发起请求
     *
     * @param request 请求对象
     * @return 响应结果对象
     * @throws IOException 请求失败
     * @author :loulan
     */
    private static Response request(Request request) throws IOException {
        Call call = WoodHttpClient.getHttpClient().newCall(request);
        return call.execute();
    }
}
