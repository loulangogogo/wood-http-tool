package io.github.loulangogogo.ask;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/*********************************************************
 ** http的请求客户端配置
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
class WoodHttpClient {
    private static final OkHttpClient httpClient = new OkHttpClient();
    public static final Duration defaultTimeout = Duration.ofSeconds(120);
    public static final Boolean defaultIsLog = Boolean.FALSE;

    /**
     * 获取http请求客户端对象
     *
     * @return http请求客户端对象
     * @author :loulan
     */
    public static OkHttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * 创建WoodHttpClientBuilder实例的静态工厂方法
     *
     * @return 新创建的WoodHttpClientBuilder构建器实例
     * @author loulan
     */
    public static WoodHttpClientBuilder builder() {
        return new WoodHttpClientBuilder();
    }


    /*********************************************************
     ** http的请求客户端配置构建器
     **
     ** @author loulan
     ** @since
     *********************************************************/
    static class WoodHttpClientBuilder {
        private OkHttpClient.Builder clientBuilder = httpClient.newBuilder();

        /**
         * 设置调用超时时间
         *
         * @param timeout 超时时间数值
         * @param unit    时间单位
         * @return 返回当前构建器实例，支持链式调用
         * @author loulan
         */
        public WoodHttpClientBuilder callTimeout(long timeout, TimeUnit unit) {
            clientBuilder.callTimeout(timeout, unit);
            return this;
        }

        /**
         * 设置调用超时时间
         *
         * @param timeout 超时时间Duration对象
         * @return 返回当前构建器实例，支持链式调用
         * @author loulan
         */
        public WoodHttpClientBuilder callTimeout(Duration timeout) {
            clientBuilder.callTimeout(timeout);
            return this;
        }

        /**
         * 设置连接超时时间
         *
         * @param timeout 超时时间数值
         * @param unit    时间单位
         * @return 返回当前构建器实例，支持链式调用
         * @author loulan
         */
        public WoodHttpClientBuilder connectTimeout(long timeout, TimeUnit unit) {
            clientBuilder.connectTimeout(timeout, unit);
            return this;
        }

        /**
         * 设置连接超时时间
         *
         * @param timeout 超时时间Duration对象
         * @return 返回当前构建器实例，支持链式调用
         * @author loulan
         */
        public WoodHttpClientBuilder connectTimeout(Duration timeout) {
            clientBuilder.connectTimeout(timeout);
            return this;
        }

        /**
         * 设置读取超时时间
         *
         * @param timeout 超时时间数值
         * @param unit    时间单位
         * @return 返回当前构建器实例，支持链式调用
         * @author loulan
         */
        public WoodHttpClientBuilder readTimeout(long timeout, TimeUnit unit) {
            clientBuilder.readTimeout(timeout, unit);
            return this;
        }

        /**
         * 设置读取超时时间
         *
         * @param timeout 超时时间Duration对象
         * @return 返回当前构建器实例，支持链式调用
         * @author loulan
         */
        public WoodHttpClientBuilder readTimeout(Duration timeout) {
            clientBuilder.readTimeout(timeout);
            return this;
        }

        /**
         * 设置写入超时时间
         *
         * @param timeout 超时时间数值
         * @param unit    时间单位
         * @return 返回当前构建器实例，支持链式调用
         * @author loulan
         */
        public WoodHttpClientBuilder writeTimeout(long timeout, TimeUnit unit) {
            clientBuilder.writeTimeout(timeout, unit);
            return this;
        }

        /**
         * 设置写入超时时间
         *
         * @param timeout 超时时间Duration对象
         * @return 返回当前构建器实例，支持链式调用
         * @author loulan
         */
        public WoodHttpClientBuilder writeTimeout(Duration timeout) {
            clientBuilder.writeTimeout(timeout);
            return this;
        }

        /**
         * 添加网络拦截器
         *
         * @param interceptor 拦截器实例
         * @return 返回当前构建器实例，支持链式调用
         * @author loulan
         */
        public WoodHttpClientBuilder addInterceptor(okhttp3.Interceptor interceptor) {
            clientBuilder.addInterceptor(interceptor);
            return this;
        }

        /**
         * 设置是否启用HTTP日志记录功能
         *
         * @param isLog 是否启用日志记录，true表示启用BODY级别日志，false表示禁用日志
         * @return 返回当前构建器实例，支持链式调用
         * @author loulan
         */
        public WoodHttpClientBuilder isLog(Boolean isLog) {
            if (isLog==Boolean.TRUE) {
                // 生产环境建议 BASIC 或 NONE
                clientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            } else {
                clientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE));
            }
            return this;
        }


        /**
         * 构建OkHttpClient实例
         *
         * @return 配置完成的OkHttpClient对象
         * @author loulan
         */
        public OkHttpClient build() {
            return clientBuilder.build();
        }

    }
}
