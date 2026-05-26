package io.github.loulangogogo.wood.http.tool;

import io.github.loulangogogo.wood.http.tool.HttpTool;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 测试 HttpTool 便捷方法（GET/POST/PUT/DELETE 嵌套类）。
 */
public class HttpToolTest {

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

    // ========== GET 测试 ==========

    @Test
    public void testGetToStr() throws IOException {
        server.enqueue(new MockResponse().setBody("hello").setResponseCode(200));
        assertEquals("hello", HttpTool.GET.toStr(baseUrl));
    }

    @Test
    public void testGetToByteArray() throws IOException {
        server.enqueue(new MockResponse().setBody("binary").setResponseCode(200));
        byte[] result = HttpTool.GET.toByteArray(baseUrl);
        assertArrayEquals("binary".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testGetToInputStream() throws IOException {
        server.enqueue(new MockResponse().setBody("stream").setResponseCode(200));
        InputStream is = HttpTool.GET.toInputStream(baseUrl);
        assertNotNull(is);
    }

    @Test
    public void testGetRequest() throws IOException {
        server.enqueue(new MockResponse().setBody("ok").setResponseCode(200));
        try (Response response = HttpTool.GET.request(baseUrl)) {
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testGetToStrWithParamsAndHeaders() throws IOException {
        server.enqueue(new MockResponse().setBody("ok").setResponseCode(200));
        Map<String, String> params = new HashMap<>();
        params.put("key", "value");
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Test", "1");
        assertEquals("ok", HttpTool.GET.toStr(baseUrl, params, headers));
    }

    // ========== POST 测试 ==========

    @Test
    public void testPostToStr() throws IOException {
        server.enqueue(new MockResponse().setBody("created").setResponseCode(201));
        assertEquals("created", HttpTool.POST.toStr(baseUrl));
    }

    @Test
    public void testPostToByteArray() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"id\":1}").setResponseCode(201));
        byte[] result = HttpTool.POST.toByteArray(baseUrl);
        assertTrue(new String(result).contains("id"));
    }

    @Test
    public void testPostToInputStream() throws IOException {
        server.enqueue(new MockResponse().setBody("streamed").setResponseCode(200));
        InputStream is = HttpTool.POST.toInputStream(baseUrl);
        assertNotNull(is);
    }

    @Test
    public void testPostRequest() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpTool.POST.request(baseUrl)) {
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testPostWithBody() throws IOException {
        server.enqueue(new MockResponse().setBody("ok").setResponseCode(200));
        assertEquals("ok", HttpTool.POST.toStr(baseUrl, null, "{\"key\":\"val\"}"));
    }

    @Test
    public void testPostWithHeadersAndBody() throws IOException {
        server.enqueue(new MockResponse().setBody("ok").setResponseCode(200));
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        assertEquals("ok", HttpTool.POST.toStr(baseUrl, headers, "{\"data\":1}"));
    }

    // ========== PUT 测试 ==========

    @Test
    public void testPutToStr() throws IOException {
        server.enqueue(new MockResponse().setBody("updated").setResponseCode(200));
        assertEquals("updated", HttpTool.PUT.toStr(baseUrl));
    }

    @Test
    public void testPutToByteArray() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"ok\":true}").setResponseCode(200));
        assertNotNull(HttpTool.PUT.toByteArray(baseUrl));
    }

    @Test
    public void testPutToInputStream() throws IOException {
        server.enqueue(new MockResponse().setBody("put stream").setResponseCode(200));
        assertNotNull(HttpTool.PUT.toInputStream(baseUrl));
    }

    @Test
    public void testPutRequest() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpTool.PUT.request(baseUrl)) {
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testPutWithBody() throws IOException {
        server.enqueue(new MockResponse().setBody("replaced").setResponseCode(200));
        assertEquals("replaced", HttpTool.PUT.toStr(baseUrl, null, "{\"new\":\"data\"}"));
    }

    // ========== DELETE 测试 ==========

    @Test
    public void testDeleteToStr() throws IOException {
        server.enqueue(new MockResponse().setBody("deleted").setResponseCode(200));
        assertEquals("deleted", HttpTool.DELETE.toStr(baseUrl));
    }

    @Test
    public void testDeleteRequest() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(204));
        try (Response response = HttpTool.DELETE.request(baseUrl)) {
            assertEquals(204, response.code());
        }
    }

    @Test
    public void testDeleteWithParamsAndHeaders() throws IOException {
        server.enqueue(new MockResponse().setBody("gone").setResponseCode(200));
        Map<String, String> params = new HashMap<>();
        params.put("force", "true");
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Confirm", "yes");
        assertEquals("gone", HttpTool.DELETE.toStr(baseUrl, params, headers));
    }
}
