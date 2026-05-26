package io.github.loulangogogo.wood.http.ask;

import io.github.loulangogogo.wood.http.ask.WoodHttpRequestBody;
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

    /**
     * 测试创建 JSON 格式的请求体，内容类型和写入内容正确
     */
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

    /**
     * 测试创建空字符串请求体时内容为空
     */
    @Test
    public void testCreateEmptyRequestBody() throws IOException {
        RequestBody body = WoodHttpRequestBody.createRequestBody("");
        assertNotNull(body);

        Buffer buffer = new Buffer();
        body.writeTo(buffer);
        assertEquals("", buffer.readUtf8());
    }

    /**
     * 测试创建 null 字符串请求体时内容为空
     */
    @Test
    public void testCreateNullRequestBody() throws IOException {
        RequestBody body = WoodHttpRequestBody.createRequestBody(null);
        assertNotNull(body);

        Buffer buffer = new Buffer();
        body.writeTo(buffer);
        assertEquals("", buffer.readUtf8());
    }

    /**
     * 测试使用 File 对象创建的文件上传请求体应为 multipart/form-data 格式
     */
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

    /**
     * 测试创建文件上传请求体时传入空 bodyName 应抛出异常
     */
    @Test(expected = Exception.class)
    public void testCreateFileUploadWithEmptyBodyNameThrows() {
        WoodHttpRequestBody.createRequestBody(new File("test.txt"), "");
    }

    /**
     * 测试创建文件上传请求体时传入 null bodyName 应抛出异常
     */
    @Test(expected = Exception.class)
    public void testCreateFileUploadWithNullBodyNameThrows() {
        WoodHttpRequestBody.createRequestBody(new File("test.txt"), null);
    }

    /**
     * 测试创建文件上传请求体时传入 null File 应抛出异常
     */
    @Test(expected = Exception.class)
    public void testCreateFileUploadWithNullFileThrows() {
        WoodHttpRequestBody.createRequestBody((File) null, "file");
    }

    /**
     * 测试使用字节数组创建的文件上传请求体应为 multipart/form-data 格式
     */
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

    /**
     * 测试创建字节数组上传请求体时传入空 bodyName 应抛出异常
     */
    @Test(expected = Exception.class)
    public void testCreateBytesUploadWithEmptyBodyNameThrows() {
        WoodHttpRequestBody.createRequestBody(new byte[]{1, 2, 3}, "test.txt", "");
    }

    /**
     * 测试创建字节数组上传请求体时传入 null 字节数组应抛出异常
     */
    @Test(expected = Exception.class)
    public void testCreateBytesUploadWithNullBytesThrows() {
        WoodHttpRequestBody.createRequestBody((byte[]) null, "test.txt", "file");
    }

    /**
     * 测试创建字节数组上传请求体时传入 null 文件名应抛出异常
     */
    @Test(expected = Exception.class)
    public void testCreateBytesUploadWithNullFileNameThrows() {
        WoodHttpRequestBody.createRequestBody(new byte[]{1}, null, "file");
    }
}
