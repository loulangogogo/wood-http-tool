package io.github.loulangogogo;

import okhttp3.OkHttpClient;

/*********************************************************
 ** http的请求客户端配置
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
public class WoodHttpClient {
    private static final OkHttpClient httpClient = new OkHttpClient();

    /**
     * 获取http请求客户端对象
     *
     * @return http请求客户端对象
     * @author :loulan
     */
    public static OkHttpClient getHttpClient() {
        return httpClient;
    }
}
