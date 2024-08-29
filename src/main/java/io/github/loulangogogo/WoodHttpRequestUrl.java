package io.github.loulangogogo;

import io.github.loulangogogo.water.map.MapTool;
import io.github.loulangogogo.water.tool.AssertTool;
import okhttp3.HttpUrl;

import java.util.Map;

/*********************************************************
 ** 请求地址
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
class WoodHttpRequestUrl {

    // http协议
    private static final String HTTP_PROTOCOL = "http://";
    // https协议
    private static final String HTTPS_PROTOCOL = "https://";
    // 默认协议
    private static final String DEFAULT_PROTOCOL = HTTP_PROTOCOL;


    /**
     * 创建{@link HttpUrl}
     *
     * @param url    请求地址
     * @param params 请求参数,可以为空
     * @return {@link HttpUrl}对象
     * @author :loulan
     */
    public static HttpUrl createUrl(String url, Map<String, String> params) {
        AssertTool.notEmpty(url, "url不能为空");

        // 解析url中的各个部分到对象
        HttpUrl.Builder urlBuilder = null;
        if (url.trim().toLowerCase().startsWith(HTTP_PROTOCOL) || url.trim().toLowerCase().startsWith(HTTPS_PROTOCOL)) {
            // 如果url已经包含协议，则直接解析
            urlBuilder = HttpUrl.parse(url).newBuilder();
        } else {
            // 如果url没有默认的协议，则添加默认的协议http
            urlBuilder = HttpUrl.parse(DEFAULT_PROTOCOL + url).newBuilder();
        }

        // 循环添加参数
        if (MapTool.isNotEmpty(params)) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        return urlBuilder.build();
    }
}
