package io.github.loulangogogo.ask;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.Buffer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * 测试 WoodHttpRequestBody 的请求体创建逻辑。
 */
public class WoodHttpRequestBodyTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testCreateJsonRequestBody() throws IOException {
        String json = "{\"name\":\"test\",\"value\":123}";
        RequestBody body = WoodHttpRequestBody.createRequestBody(json);

        assertNotNull(body);
        assertEquals(MediaType.get("application/json; charset=utf-8"), body.contentType());

        Buffer buffer = new Buffer();
        body.writeTo(buffer);
        assertEquals(json, buffer.readUtf8());
    }

    @Test
    public void testCreateEmptyRequestBody() throws IOException {
        RequestBody body = WoodHttpRequestBody.createRequestBody("");
        assertNotNull(body);

        Buffer buffer = new Buffer();
        body.writeTo(buffer);
        assertEquals("", buffer.readUtf8());
    }

    @Test
    public void testCreateNullRequestBody() throws IOException {
        RequestBody body = WoodHttpRequestBody.createRequestBody(null);
        assertNotNull(body);

        Buffer buffer = new Buffer();
        body.writeTo(buffer);
        assertEquals("", buffer.readUtf8());
    }

    @Test
    public void testCreateFileUploadRequestBody() throws IOException {
        File testFile = tempFolder.newFile("test.txt");
        java.nio.file.Files.write(testFile.toPath(), "test content".getBytes());

        RequestBody body = WoodHttpRequestBody.createRequestBody(testFile, "file");

        assertNotNull(body);
        assertTrue(body instanceof MultipartBody);

        MultipartBody multipart = (MultipartBody) body;
        assertEquals(MultipartBody.FORM, multipart.type());
        assertEquals(1, multipart.size());
    }

    @Test(expected = Exception.class)
    public void testCreateFileUploadWithEmptyBodyNameThrows() {
        WoodHttpRequestBody.createRequestBody(new File("test.txt"), "");
    }

    @Test(expected = Exception.class)
    public void testCreateFileUploadWithNullBodyNameThrows() {
        WoodHttpRequestBody.createRequestBody(new File("test.txt"), null);
    }

    @Test(expected = Exception.class)
    public void testCreateFileUploadWithNullFileThrows() {
        WoodHttpRequestBody.createRequestBody((File) null, "file");
    }

    @Test
    public void testCreateBytesUploadRequestBody() throws IOException {
        byte[] bytes = "hello world".getBytes();
        RequestBody body = WoodHttpRequestBody.createRequestBody(bytes, "test.txt", "file");

        assertNotNull(body);
        assertTrue(body instanceof MultipartBody);

        MultipartBody multipart = (MultipartBody) body;
        assertEquals(MultipartBody.FORM, multipart.type());
        assertEquals(1, multipart.size());
    }

    @Test(expected = Exception.class)
    public void testCreateBytesUploadWithEmptyBodyNameThrows() {
        WoodHttpRequestBody.createRequestBody(new byte[]{1, 2, 3}, "test.txt", "");
    }

    @Test(expected = Exception.class)
    public void testCreateBytesUploadWithNullBytesThrows() {
        WoodHttpRequestBody.createRequestBody((byte[]) null, "test.txt", "file");
    }

    @Test(expected = Exception.class)
    public void testCreateBytesUploadWithNullFileNameThrows() {
        WoodHttpRequestBody.createRequestBody(new byte[]{1}, null, "file");
    }
}
