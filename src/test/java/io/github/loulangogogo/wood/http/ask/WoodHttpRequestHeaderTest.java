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

    @Test
    public void testSetEmptyHeadersDoesNotAffectBuilder() {
        Request.Builder builder = new Request.Builder();
        Map<String, String> headers = new HashMap<>();

        WoodHttpRequestHeader.setHeader(builder, headers);
        Request request = builder.url("http://example.com").build();
        assertNotNull(request);
    }

    @Test
    public void testSetNullHeadersDoesNotAffectBuilder() {
        Request.Builder builder = new Request.Builder();

        WoodHttpRequestHeader.setHeader(builder, null);
        Request request = builder.url("http://example.com").build();
        assertNotNull(request);
    }

    @Test(expected = Exception.class)
    public void testNullBuilderThrowsException() {
        WoodHttpRequestHeader.setHeader(null, new HashMap<String, String>());
    }
}
