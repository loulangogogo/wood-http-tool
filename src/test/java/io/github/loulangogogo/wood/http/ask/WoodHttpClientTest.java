package io.github.loulangogogo.wood.http.ask;

import io.github.loulangogogo.wood.http.ask.WoodHttpClient;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 测试 WoodHttpClient 的客户端配置逻辑。
 * 包含 Bug8 检测：验证 isLog(false) 不会添加无用的日志拦截器。
 */
public class WoodHttpClientTest {

    @Test
    public void testGetHttpClientReturnsDefaultInstance() {
        OkHttpClient client = WoodHttpClient.getHttpClient();
        assertNotNull(client);
    }

    @Test
    public void testBuilderReturnsInstance() {
        WoodHttpClient.WoodHttpClientBuilder builder = WoodHttpClient.builder();
        assertNotNull(builder);
    }

    @Test
    public void testBuilderBuildReturnsOkHttpClient() {
        OkHttpClient client = WoodHttpClient.builder().build();
        assertNotNull(client);
    }

    @Test
    public void testCallTimeoutWithTimeUnit() {
        OkHttpClient client = WoodHttpClient.builder()
                .callTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .build();
        assertNotNull(client);
    }

    @Test
    public void testConnectTimeoutWithDuration() {
        OkHttpClient client = WoodHttpClient.builder()
                .connectTimeout(java.time.Duration.ofSeconds(45))
                .build();
        assertNotNull(client);
    }

    @Test
    public void testReadTimeout() {
        OkHttpClient client = WoodHttpClient.builder()
                .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
                .build();
        assertNotNull(client);
    }

    @Test
    public void testWriteTimeout() {
        OkHttpClient client = WoodHttpClient.builder()
                .writeTimeout(java.time.Duration.ofSeconds(30))
                .build();
        assertNotNull(client);
    }

    @Test
    public void testAddInterceptor() {
        OkHttpClient client = WoodHttpClient.builder()
                .addInterceptor(new TestInterceptor())
                .build();
        assertNotNull(client);
    }

    /**
     * Bug8 检测：验证 isLog(true) 添加日志拦截器。
     */
    @Test
    public void testIsLogTrueAddsLoggingInterceptor() throws Exception {
        OkHttpClient client = WoodHttpClient.builder()
                .isLog(true)
                .build();
        List<Interceptor> interceptors = getInterceptors(client);
        assertFalse("isLog(true) should add a logging interceptor", interceptors.isEmpty());
    }

    /**
     * Bug8 检测：验证 isLog(false) 不应添加无用的日志拦截器。
     */
    @Test
    public void testIsLogFalseShouldNotAddInterceptor() throws Exception {
        OkHttpClient client = WoodHttpClient.builder()
                .isLog(false)
                .build();
        List<Interceptor> interceptors = getInterceptors(client);
        assertTrue("isLog(false) should NOT add a useless NONE-level interceptor",
                interceptors.isEmpty());
    }

    @Test
    public void testIsLogNullShouldNotAddInterceptor() throws Exception {
        OkHttpClient client = WoodHttpClient.builder()
                .isLog(null)
                .build();
        List<Interceptor> interceptors = getInterceptors(client);
        assertTrue("isLog(null) should NOT add a logging interceptor", interceptors.isEmpty());
    }

    /**
     * 通过反射获取 OkHttpClient 的 application interceptors 列表。
     */
    @SuppressWarnings("unchecked")
    private List<Interceptor> getInterceptors(OkHttpClient client) throws Exception {
        Field field = OkHttpClient.class.getDeclaredField("interceptors");
        field.setAccessible(true);
        return (List<Interceptor>) field.get(client);
    }

    private static class TestInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws java.io.IOException {
            return chain.proceed(chain.request());
        }
    }
}
