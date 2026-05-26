package io.github.loulangogogo.wood.http.ask;

import io.github.loulangogogo.wood.http.ask.HttpRequestTool;
import io.github.loulangogogo.wood.http.enums.HttpMethod;
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
 * 测试 HttpRequestTool 的核心请求逻辑。
 * 使用 MockWebServer 模拟 HTTP 响应。
 */
public class HttpRequestToolTest {

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

    @Test
    public void testGetRequest() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"status\":\"ok\"}").setResponseCode(200));
        try (Response response = HttpRequestTool.request(baseUrl, HttpMethod.GET, null, null, null)) {
            assertNotNull(response);
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testPostRequestWithBody() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"id\":1}").setResponseCode(201));
        try (Response response = HttpRequestTool.request(baseUrl, HttpMethod.POST, null, null, "{\"name\":\"test\"}")) {
            assertNotNull(response);
            assertEquals(201, response.code());
        }
    }

    @Test
    public void testPutRequest() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"updated\":true}").setResponseCode(200));
        try (Response response = HttpRequestTool.request(baseUrl, HttpMethod.PUT, null, null, "{\"key\":\"val\"}")) {
            assertNotNull(response);
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testDeleteRequest() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(204));
        try (Response response = HttpRequestTool.request(baseUrl, HttpMethod.DELETE, null, null, null)) {
            assertNotNull(response);
            assertEquals(204, response.code());
        }
    }

    @Test
    public void testRequestWithHeaders() throws IOException {
        server.enqueue(new MockResponse().setBody("ok").setResponseCode(200));
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer test-token");
        try (Response response = HttpRequestTool.request(baseUrl, HttpMethod.GET, headers, null, null)) {
            assertNotNull(response);
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testRequestWithParams() throws IOException {
        server.enqueue(new MockResponse().setBody("ok").setResponseCode(200));
        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("limit", "10");
        try (Response response = HttpRequestTool.request(baseUrl, HttpMethod.GET, null, params, null)) {
            assertNotNull(response);
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testRequestWithTimeout() throws IOException {
        server.enqueue(new MockResponse().setBody("ok").setResponseCode(200));
        try (Response response = HttpRequestTool.request(
                baseUrl, HttpMethod.GET, null, null, null,
                Duration.ofSeconds(30), false)) {
            assertNotNull(response);
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testRequestWithLogging() throws IOException {
        server.enqueue(new MockResponse().setBody("ok").setResponseCode(200));
        try (Response response = HttpRequestTool.request(
                baseUrl, HttpMethod.GET, null, null, null,
                Duration.ofSeconds(30), true)) {
            assertNotNull(response);
            assertEquals(200, response.code());
        }
    }

    @Test(expected = Exception.class)
    public void testRequestWithNullUrlThrowsException() {
        HttpRequestTool.request(null, HttpMethod.GET, null, null, null);
    }

    @Test(expected = Exception.class)
    public void testRequestWithEmptyUrlThrowsException() {
        HttpRequestTool.request("", HttpMethod.GET, null, null, null);
    }

    @Test(expected = Exception.class)
    public void testRequestWithNullMethodThrowsException() {
        HttpRequestTool.request(baseUrl, null, null, null, null);
    }

    @Test(expected = Exception.class)
    public void testUploadFileWithNullUrlThrowsException() {
        HttpRequestTool.uploadFile(null, HttpMethod.POST, null, null, new byte[]{1}, "test.txt", "file");
    }

    @Test(expected = Exception.class)
    public void testUploadFileWithNullMethodThrowsException() {
        HttpRequestTool.uploadFile(baseUrl, null, null, null, new byte[]{1}, "test.txt", "file");
    }

    @Test(expected = Exception.class)
    public void testUploadFileWithGetMethodThrowsException() {
        HttpRequestTool.uploadFile(baseUrl, HttpMethod.GET, null, null, new byte[]{1}, "test.txt", "file");
    }

    @Test(expected = Exception.class)
    public void testUploadFileWithNullBodyFileThrowsException() {
        HttpRequestTool.uploadFile(baseUrl, HttpMethod.POST, null, null, (byte[]) null, "test.txt", "file");
    }

    @Test(expected = Exception.class)
    public void testUploadFileWithNullFileNameThrowsException() {
        HttpRequestTool.uploadFile(baseUrl, HttpMethod.POST, null, null, new byte[]{1}, null, "file");
    }

    @Test(expected = Exception.class)
    public void testUploadFileWithNullBodyNameThrowsException() {
        HttpRequestTool.uploadFile(baseUrl, HttpMethod.POST, null, null, new byte[]{1}, "test.txt", null);
    }

    @Test
    public void testUploadFileBytes() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"uploaded\":true}").setResponseCode(200));
        try (Response response = HttpRequestTool.uploadFile(
                baseUrl, HttpMethod.POST, null, null,
                "file content".getBytes(), "test.txt", "file")) {
            assertNotNull(response);
            assertEquals(200, response.code());
        }
    }
}
