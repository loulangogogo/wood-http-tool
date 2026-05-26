package io.github.loulangogogo.tool;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    @Test
    public void testToStr() throws IOException {
        server.enqueue(new MockResponse().setBody("updated").setResponseCode(200));
        assertEquals("updated", HttpToolPut.toStr(baseUrl));
    }

    @Test
    public void testToByteArray() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"ok\":true}").setResponseCode(200));
        assertNotNull(HttpToolPut.toByteArray(baseUrl));
    }

    @Test
    public void testToInputStream() throws IOException {
        server.enqueue(new MockResponse().setBody("stream").setResponseCode(200));
        assertNotNull(HttpToolPut.toInputStream(baseUrl));
    }

    @Test
    public void testRequest() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpToolPut.request(baseUrl)) {
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testToStrWithHeaders() throws IOException {
        server.enqueue(new MockResponse().setBody("ok").setResponseCode(200));
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        assertEquals("ok", HttpToolPut.toStr(baseUrl, headers));
    }

    @Test
    public void testToByteArrayWithHeaders() throws IOException {
        server.enqueue(new MockResponse().setBody("data").setResponseCode(200));
        assertNotNull(HttpToolPut.toByteArray(baseUrl, new HashMap<>()));
    }

    @Test
    public void testToInputStreamWithHeaders() throws IOException {
        server.enqueue(new MockResponse().setBody("stream").setResponseCode(200));
        assertNotNull(HttpToolPut.toInputStream(baseUrl, new HashMap<>()));
    }

    @Test
    public void testRequestWithHeaders() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpToolPut.request(baseUrl, new HashMap<>())) {
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testToStrWithHeadersAndBody() throws IOException {
        server.enqueue(new MockResponse().setBody("ok").setResponseCode(200));
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        assertEquals("ok", HttpToolPut.toStr(baseUrl, headers, "{\"key\":\"val\"}"));
    }

    @Test
    public void testToByteArrayWithHeadersAndBody() throws IOException {
        server.enqueue(new MockResponse().setBody("data").setResponseCode(200));
        assertNotNull(HttpToolPut.toByteArray(baseUrl, new HashMap<>(), "{\"key\":\"val\"}"));
    }

    @Test
    public void testToInputStreamWithHeadersAndBody() throws IOException {
        server.enqueue(new MockResponse().setBody("stream").setResponseCode(200));
        assertNotNull(HttpToolPut.toInputStream(baseUrl, new HashMap<>(), "{\"key\":\"val\"}"));
    }

    @Test
    public void testRequestWithHeadersAndBody() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpToolPut.request(baseUrl, new HashMap<>(), "{\"key\":\"val\"}")) {
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testToStrWithParamsHeadersBody() throws IOException {
        server.enqueue(new MockResponse().setBody("replaced").setResponseCode(200));
        assertEquals("replaced", HttpToolPut.toStr(baseUrl, new HashMap<>(), new HashMap<>(), "{\"new\":\"data\"}"));
    }

    @Test
    public void testToByteArrayWithParamsHeadersBody() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"ok\":true}").setResponseCode(200));
        assertTrue(new String(HttpToolPut.toByteArray(baseUrl, new HashMap<>(), new HashMap<>(), "{\"ok\":true}")).contains("ok"));
    }

    @Test
    public void testToInputStreamWithParamsHeadersBody() throws IOException {
        server.enqueue(new MockResponse().setBody("stream").setResponseCode(200));
        assertNotNull(HttpToolPut.toInputStream(baseUrl, new HashMap<>(), new HashMap<>(), null));
    }

    @Test
    public void testRequestWithParamsHeadersBody() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpToolPut.request(baseUrl, new HashMap<>(), new HashMap<>(), "{\"key\":\"val\"}")) {
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testRequestWithTimeoutAndLog() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        try (Response response = HttpToolPut.request(baseUrl, null, null, null, Duration.ofSeconds(30), false)) {
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testUploadFileBytes() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"uploaded\":true}").setResponseCode(200));
        try (Response response = HttpToolPut.uploadFile(baseUrl, null, new HashMap<>(), "content".getBytes(), "test.txt", "file")) {
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testUploadFileBytesWithParams() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"uploaded\":true}").setResponseCode(200));
        try (Response response = HttpToolPut.uploadFile(baseUrl, new HashMap<>(), new HashMap<>(), "content".getBytes(), "test.txt", "file")) {
            assertEquals(200, response.code());
        }
    }

    @Test
    public void testUploadFileBytesWithTimeoutAndLog() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"uploaded\":true}").setResponseCode(200));
        try (Response response = HttpToolPut.uploadFile(baseUrl, new HashMap<>(), new HashMap<>(), "content".getBytes(), "test.txt", "file", Duration.ofSeconds(30), false)) {
            assertEquals(200, response.code());
        }
    }

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
