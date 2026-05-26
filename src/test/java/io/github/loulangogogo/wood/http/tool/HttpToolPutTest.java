package io.github.loulangogogo.wood.http.tool;

import io.github.loulangogogo.wood.http.tool.HttpToolPut;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 测试 HttpToolPut 的所有方法。
 */
public class HttpToolPutTest {

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
        server.enqueue(new MockResponse().setBody("updated").setResponseCode(200));
        assertEquals("updated", HttpToolPut.toStr(baseUrl));
    }

    /**
     * 测试仅传入url时，toByteArray方法能正确返回响应体字节数组
     */
    @Test
    public void testToByteArray() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"ok\":true}").setResponseCode(200));
        assertNotNull(HttpToolPut.toByteArray(baseUrl));
    }

    /**
     * 测试仅传入url时，toInputStream方法能正确返回响应体输入流
     */
    @Test
    public void testToInputStream() throws IOException {
        server.enqueue(new MockResponse().setBody("stream").setResponseCode(200));
        assertNotNull(HttpToolPut.toInputStream(baseUrl));
    }

    /**
     * 测试传入body时，toStr方法能正确返回响应体字符串
     */
    @Test
    public void testToStrWithBody() throws IOException {
        server.enqueue(new MockResponse().setBody("updated").setResponseCode(200));
        assertEquals("updated", HttpToolPut.toStr(baseUrl, "{\"key\":\"val\"}"));
    }

    /**
     * 测试传入body时，toByteArray方法能正确返回响应体字节数组
     */
    @Test
    public void testToByteArrayWithBody() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"ok\":true}").setResponseCode(200));
        byte[] result = HttpToolPut.toByteArray(baseUrl, "{\"key\":\"val\"}");
        assertTrue(new String(result).contains("ok"));
    }

    /**
     * 测试传入body时，toInputStream方法能正确返回响应体输入流
     */
    @Test
    public void testToInputStreamWithBody() throws IOException {
        server.enqueue(new MockResponse().setBody("stream-body").setResponseCode(200));
        assertNotNull(HttpToolPut.toInputStream(baseUrl, "{\"key\":\"val\"}"));
    }

    /**
     * 测试传入body时，request方法能正确返回响应对象
     */
    @Test
    public void testRequestWithBody() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpToolPut.request(baseUrl, "{\"key\":\"val\"}")) {
            assertEquals(200, response.code());
        }
    }

    /**
     * 测试仅传入url时，request方法能正确返回响应对象
     */
    @Test
    public void testRequest() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpToolPut.request(baseUrl)) {
            assertEquals(200, response.code());
        }
    }

    /**
     * 测试传入headers时，toStr方法能正确返回响应体字符串
     */
    @Test
    public void testToStrWithHeaders() throws IOException {
        server.enqueue(new MockResponse().setBody("ok").setResponseCode(200));
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        assertEquals("ok", HttpToolPut.toStr(baseUrl, headers));
    }

    /**
     * 测试传入headers时，toByteArray方法能正确返回响应体字节数组
     */
    @Test
    public void testToByteArrayWithHeaders() throws IOException {
        server.enqueue(new MockResponse().setBody("data").setResponseCode(200));
        assertNotNull(HttpToolPut.toByteArray(baseUrl, new HashMap<>()));
    }

    /**
     * 测试传入headers时，toInputStream方法能正确返回响应体输入流
     */
    @Test
    public void testToInputStreamWithHeaders() throws IOException {
        server.enqueue(new MockResponse().setBody("stream").setResponseCode(200));
        assertNotNull(HttpToolPut.toInputStream(baseUrl, new HashMap<>()));
    }

    /**
     * 测试传入headers时，request方法能正确返回响应对象
     */
    @Test
    public void testRequestWithHeaders() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpToolPut.request(baseUrl, new HashMap<>())) {
            assertEquals(200, response.code());
        }
    }

    /**
     * 测试同时传入headers和body时，toStr方法能正确返回响应体字符串
     */
    @Test
    public void testToStrWithHeadersAndBody() throws IOException {
        server.enqueue(new MockResponse().setBody("ok").setResponseCode(200));
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        assertEquals("ok", HttpToolPut.toStr(baseUrl, headers, "{\"key\":\"val\"}"));
    }

    /**
     * 测试同时传入headers和body时，toByteArray方法能正确返回响应体字节数组
     */
    @Test
    public void testToByteArrayWithHeadersAndBody() throws IOException {
        server.enqueue(new MockResponse().setBody("data").setResponseCode(200));
        assertNotNull(HttpToolPut.toByteArray(baseUrl, new HashMap<>(), "{\"key\":\"val\"}"));
    }

    /**
     * 测试同时传入headers和body时，toInputStream方法能正确返回响应体输入流
     */
    @Test
    public void testToInputStreamWithHeadersAndBody() throws IOException {
        server.enqueue(new MockResponse().setBody("stream").setResponseCode(200));
        assertNotNull(HttpToolPut.toInputStream(baseUrl, new HashMap<>(), "{\"key\":\"val\"}"));
    }

    /**
     * 测试同时传入headers和body时，request方法能正确返回响应对象
     */
    @Test
    public void testRequestWithHeadersAndBody() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpToolPut.request(baseUrl, new HashMap<>(), "{\"key\":\"val\"}")) {
            assertEquals(200, response.code());
        }
    }

    /**
     * 测试同时传入params、headers和body时，toStr方法能正确返回响应体字符串
     */
    @Test
    public void testToStrWithParamsHeadersBody() throws IOException {
        server.enqueue(new MockResponse().setBody("replaced").setResponseCode(200));
        assertEquals("replaced", HttpToolPut.toStr(baseUrl, new HashMap<>(), new HashMap<>(), "{\"new\":\"data\"}"));
    }

    /**
     * 测试同时传入params、headers和body时，toByteArray方法能正确返回响应体字节数组
     */
    @Test
    public void testToByteArrayWithParamsHeadersBody() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"ok\":true}").setResponseCode(200));
        assertTrue(new String(HttpToolPut.toByteArray(baseUrl, new HashMap<>(), new HashMap<>(), "{\"ok\":true}")).contains("ok"));
    }

    /**
     * 测试同时传入params、headers和body时，toInputStream方法能正确返回响应体输入流
     */
    @Test
    public void testToInputStreamWithParamsHeadersBody() throws IOException {
        server.enqueue(new MockResponse().setBody("stream").setResponseCode(200));
        assertNotNull(HttpToolPut.toInputStream(baseUrl, new HashMap<>(), new HashMap<>(), null));
    }

    /**
     * 测试同时传入params、headers和body时，request方法能正确返回响应对象
     */
    @Test
    public void testRequestWithParamsHeadersBody() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpToolPut.request(baseUrl, new HashMap<>(), new HashMap<>(), "{\"key\":\"val\"}")) {
            assertEquals(200, response.code());
        }
    }

    /**
     * 测试传入超时时间和日志开关时，request方法能正确返回响应对象
     */
    @Test
    public void testRequestWithTimeoutAndLog() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpToolPut.request(baseUrl, null, null, null, Duration.ofSeconds(30), false)) {
            assertEquals(200, response.code());
        }
    }

    /**
     * 测试使用字节数组上传文件时，uploadFile方法能正确返回响应对象
     */
    @Test
    public void testUploadFileBytes() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"uploaded\":true}").setResponseCode(200));
        try (Response response = HttpToolPut.uploadFile(baseUrl, null, new HashMap<>(), "content".getBytes(), "test.txt", "file")) {
            assertEquals(200, response.code());
        }
    }

    /**
     * 测试使用字节数组并传入params时，uploadFile方法能正确返回响应对象
     */
    @Test
    public void testUploadFileBytesWithParams() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"uploaded\":true}").setResponseCode(200));
        try (Response response = HttpToolPut.uploadFile(baseUrl, new HashMap<>(), new HashMap<>(), "content".getBytes(), "test.txt", "file")) {
            assertEquals(200, response.code());
        }
    }

    /**
     * 测试使用字节数组并传入超时和日志开关时，uploadFile方法能正确返回响应对象
     */
    @Test
    public void testUploadFileBytesWithTimeoutAndLog() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"uploaded\":true}").setResponseCode(200));
        try (Response response = HttpToolPut.uploadFile(baseUrl, new HashMap<>(), new HashMap<>(), "content".getBytes(), "test.txt", "file", Duration.ofSeconds(30), false)) {
            assertEquals(200, response.code());
        }
    }

    /**
     * 测试使用File对象上传文件时，uploadFile方法能正确返回响应对象
     */
    @Test
    public void testUploadFileWithFileObject() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"uploaded\":true}").setResponseCode(200));
        File tmpFile = File.createTempFile("upload", ".txt");
        Files.write(tmpFile.toPath(), "file content".getBytes(StandardCharsets.UTF_8));
        try (Response response = HttpToolPut.uploadFile(baseUrl, new HashMap<>(), new HashMap<>(), tmpFile, "file")) {
            assertEquals(200, response.code());
        }
        tmpFile.delete();
    }

    /**
     * 测试使用File对象上传文件并传入超时和日志开关时，uploadFile方法能正确返回响应对象
     */
    @Test
    public void testUploadFileWithFileObjectAndTimeout() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"uploaded\":true}").setResponseCode(200));
        File tmpFile = File.createTempFile("upload", ".txt");
        Files.write(tmpFile.toPath(), "file content".getBytes(StandardCharsets.UTF_8));
        try (Response response = HttpToolPut.uploadFile(baseUrl, new HashMap<>(), new HashMap<>(), tmpFile, "file", Duration.ofSeconds(30), false)) {
            assertEquals(200, response.code());
        }
        tmpFile.delete();
    }
}
