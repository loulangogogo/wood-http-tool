package io.github.loulangogogo.ask;

import io.github.loulangogogo.water.map.MapTool;
import io.github.loulangogogo.water.tool.AssertTool;
import okhttp3.Headers;
import okhttp3.Request;

import java.util.Map;

/*********************************************************
 ** 请求头配置
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
class WoodHttpRequestHeader {

    /**
     * 设置请求头
     *
     * @param requestBuilder 请求构建器
     * @param headers        请求头
     * @author :loulan
     */
    public static void setHeader(Request.Builder requestBuilder, Map<String, String> headers) {
        AssertTool.notNull(requestBuilder, "请求构建器不能为空");
        if (MapTool.isNotEmpty(headers)) {
            requestBuilder.headers(Headers.of(headers));
        }
    }
}
