package io.github.loulangogogo.ask;

import io.github.loulangogogo.enums.HttpMethod;
import io.github.loulangogogo.water.tool.AssertTool;
import okhttp3.Request;
import okhttp3.RequestBody;

/*********************************************************
 ** 请求方法
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
class WoodHttpRequestMethod {

    /**
     * 对请求构建器设置请求方法
     *
     * @param requestBuilder 请求构建器
     * @param method         请求方法
     * @param requestBody    请求体
     * @author :loulan
     */
    public static void setMethod(Request.Builder requestBuilder, HttpMethod method, RequestBody requestBody) {
        AssertTool.notNull(method, "请求方法不能为空");
        AssertTool.notNull(requestBuilder, "请求构建器不能为空");
        // 设置请求方法
        switch (method) {
            case GET:
                requestBuilder.get();
                break;
            case POST:
                requestBuilder.post(requestBody);
                break;
            case PUT:
                requestBuilder.put(requestBody);
                break;
            case DELETE:
                requestBuilder.delete();
                break;
            case HEAD:
                requestBuilder.head();
                break;
            case PATCH:
                requestBuilder.patch(requestBody);
                break;
            default:
                throw new IllegalArgumentException("不支持的请求方法");
        }
    }
}
