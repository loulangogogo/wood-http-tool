package io.github.loulangogogo.wood.http.tool;

import io.github.loulangogogo.wood.http.tool.HttpToolGet;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 测试 HttpToolGet 的所有方法。
 */
public class HttpToolGetTest {

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
    public void testToStr() throws IOException {
        server.enqueue(new MockResponse().setBody("hello").setResponseCode(200));
        assertEquals("hello", HttpToolGet.toStr(baseUrl));
    }

    @Test
    public void testToByteArray() throws IOException {
        server.enqueue(new MockResponse().setBody("binary").setResponseCode(200));
        assertArrayEquals("binary".getBytes(StandardCharsets.UTF_8), HttpToolGet.toByteArray(baseUrl));
    }

    @Test
    public void testToInputStream() throws IOException {
        server.enqueue(new MockResponse().setBody("stream").setResponseCode(200));
        assertNotNull(HttpToolGet.toInputStream(baseUrl));
    }

    @Test
    public void testRequest() throws IOException {
        server.enqueue(new MockResponse().setBody("ok").setResponseCode(200));
        try (Response response = HttpToolGet.request(baseUrl)) {
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testToStrWithParamsAndHeaders() throws IOException {
        server.enqueue(new MockResponse().setBody("ok").setResponseCode(200));
        Map<String, String> params = new HashMap<>();
        params.put("key", "value");
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Test", "1");
        assertEquals("ok", HttpToolGet.toStr(baseUrl, params, headers));
    }

    @Test
    public void testToByteArrayWithParamsAndHeaders() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"id\":1}").setResponseCode(200));
        byte[] result = HttpToolGet.toByteArray(baseUrl, new HashMap<>(), new HashMap<>());
        assertTrue(new String(result).contains("id"));
    }

    @Test
    public void testToInputStreamWithParamsAndHeaders() throws IOException {
        server.enqueue(new MockResponse().setBody("stream").setResponseCode(200));
        assertNotNull(HttpToolGet.toInputStream(baseUrl, new HashMap<>(), new HashMap<>()));
    }

    @Test
    public void testRequestWithParamsAndHeaders() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpToolGet.request(baseUrl, new HashMap<>(), new HashMap<>())) {
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testRequestWithTimeoutAndLog() throws IOException {
        server.enqueue(new MockResponse().setBody("ok").setResponseCode(200));
        try (Response response = HttpToolGet.request(baseUrl, null, null, Duration.ofSeconds(30), false)) {
            assertEquals(200, response.code());
        }
    }
}
