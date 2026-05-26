package io.github.loulangogogo.wood.http.ask;

import io.github.loulangogogo.wood.http.ask.WoodHttpRequestHeader;
import okhttp3.Request;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 测试 WoodHttpRequestHeader 的请求头设置逻辑。
 */
public class WoodHttpRequestHeaderTest {

    /**
     * 测试设置多个请求头后能正确读取
     */
    @Test
    public void testSetMultipleHeaders() {
        Request.Builder builder = new Request.Builder();
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer token123");
        headers.put("Content-Type", "application/json");

        WoodHttpRequestHeader.setHeader(builder, headers);
        Request request = builder.url("http://example.com").build();

        assertEquals("Bearer token123", request.header("Authorization"));
        assertEquals("application/json", request.header("Content-Type"));
    }

    /**
     * 测试设置空请求头映射时不影响 builder 构建
     */
    @Test
    public void testSetEmptyHeadersDoesNotAffectBuilder() {
        Request.Builder builder = new Request.Builder();
        Map<String, String> headers = new HashMap<>();

        WoodHttpRequestHeader.setHeader(builder, headers);
        Request request = builder.url("http://example.com").build();
        assertNotNull(request);
    }

    /**
     * 测试设置 null 请求头时不影响 builder 构建
     */
    @Test
    public void testSetNullHeadersDoesNotAffectBuilder() {
        Request.Builder builder = new Request.Builder();

        WoodHttpRequestHeader.setHeader(builder, null);
        Request request = builder.url("http://example.com").build();
        assertNotNull(request);
    }

    /**
     * 测试传入 null builder 时应抛出异常
     */
    @Test(expected = Exception.class)
    public void testNullBuilderThrowsException() {
        WoodHttpRequestHeader.setHeader(null, new HashMap<String, String>());
    }
}
