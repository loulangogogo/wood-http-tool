package io.github.loulangogogo.wood.http.ask;

import io.github.loulangogogo.water.map.MapTool;
import io.github.loulangogogo.water.tool.AssertTool;
import io.github.loulangogogo.water.tool.ObjectTool;
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

        String trimmedUrl = url.trim();
        String lowerUrl = trimmedUrl.toLowerCase();
        HttpUrl httpUrl = null;

        if (lowerUrl.startsWith(HTTP_PROTOCOL) || lowerUrl.startsWith(HTTPS_PROTOCOL)) {
            httpUrl = HttpUrl.parse(trimmedUrl);
        } else {
            httpUrl = HttpUrl.parse(DEFAULT_PROTOCOL + trimmedUrl);
        }

        if (ObjectTool.isNull(httpUrl)) {
            throw new IllegalArgumentException("url格式无效: " + url);
        }

        HttpUrl.Builder urlBuilder = httpUrl.newBuilder();

        // 循环添加参数
        if (MapTool.isNotEmpty(params)) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        return urlBuilder.build();
    }
}
