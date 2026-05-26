package io.github.loulangogogo.wood.http.tool;

import io.github.loulangogogo.wood.http.tool.HttpToolDelete;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 测试 HttpToolDelete 的所有方法。
 */
public class HttpToolDeleteTest {

    private MockWebServer server;
    private String baseUrl;

    @Before
    public void setUp() throws IOException {
        server = new MockWebServer();
        server.start();
        baseUrl = server.url("/api").toString();
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

    /**
     * 测试仅传入url时，toStr方法能正确返回响应体字符串
     */
    @Test
    public void testToStr() throws IOException {
        server.enqueue(new MockResponse().setBody("deleted").setResponseCode(200));
        assertEquals("deleted", HttpToolDelete.toStr(baseUrl));
    }

    /**
     * 测试仅传入url时，request方法能正确返回响应对象且状态码为204
     */
    @Test
    public void testRequest() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(204));
        try (Response response = HttpToolDelete.request(baseUrl)) {
            assertEquals(204, response.code());
        }
    }

    /**
     * 测试传入params时，toStr方法能正确返回响应体字符串
     */
    @Test
    public void testToStrWithParams() throws IOException {
        server.enqueue(new MockResponse().setBody("deleted_with_params").setResponseCode(200));
        Map<String, String> params = new HashMap<>();
        params.put("force", "true");
        assertEquals("deleted_with_params", HttpToolDelete.toStr(baseUrl, params));
    }

    /**
     * 测试传入params时，request方法能正确返回响应对象
     */
    @Test
    public void testRequestWithParams() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        Map<String, String> params = new HashMap<>();
        params.put("cascade", "true");
        try (Response response = HttpToolDelete.request(baseUrl, params)) {
            assertEquals(200, response.code());
        }
    }

    /**
     * 测试同时传入params和headers时，toStr方法能正确返回响应体字符串
     */
    @Test
    public void testToStrWithParamsAndHeaders() throws IOException {
        server.enqueue(new MockResponse().setBody("gone").setResponseCode(200));
        Map<String, String> params = new HashMap<>();
        params.put("force", "true");
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Confirm", "yes");
        assertEquals("gone", HttpToolDelete.toStr(baseUrl, params, headers));
    }

    /**
     * 测试同时传入params和headers时，request方法能正确返回响应对象
     */
    @Test
    public void testRequestWithParamsAndHeaders() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpToolDelete.request(baseUrl, new HashMap<>(), new HashMap<>())) {
            assertEquals(200, response.code());
        }
    }

    /**
     * 测试传入超时时间和日志开关时，request方法能正确返回响应对象
     */
    @Test
    public void testRequestWithTimeoutAndLog() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpToolDelete.request(baseUrl, null, null, Duration.ofSeconds(30), false)) {
            assertEquals(200, response.code());
        }
    }
}
