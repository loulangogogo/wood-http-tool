package io.github.loulangogogo.wood.http.ask;

import io.github.loulangogogo.wood.http.ask.WoodHttpRequestUrl;
import okhttp3.HttpUrl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 测试 WoodHttpRequestUrl 的 URL 解析和参数拼接逻辑。
 * 包含 Bug1 检测：HttpUrl.parse() 返回 null 时应抛异常而非 NPE。
 */
public class WoodHttpRequestUrlTest {

    @Test
    public void testUrlWithHttpProtocol() {
        HttpUrl result = WoodHttpRequestUrl.createUrl("http://example.com/api", null);
        assertNotNull(result);
        assertEquals("http", result.scheme());
        assertEquals("example.com", result.host());
        assertEquals("/api", result.encodedPath());
    }

    @Test
    public void testUrlWithHttpsProtocol() {
        HttpUrl result = WoodHttpRequestUrl.createUrl("https://example.com/api", null);
        assertNotNull(result);
        assertEquals("https", result.scheme());
    }

    @Test
    public void testUrlWithoutProtocolAddsHttp() {
        HttpUrl result = WoodHttpRequestUrl.createUrl("example.com/api", null);
        assertNotNull(result);
        assertEquals("http", result.scheme());
        assertEquals("example.com", result.host());
    }

    @Test
    public void testUrlWithParams() {
        Map<String, String> params = new HashMap<>();
        params.put("key1", "value1");
        params.put("key2", "value2");

        HttpUrl result = WoodHttpRequestUrl.createUrl("http://example.com/api", params);
        assertNotNull(result);
        assertEquals("value1", result.queryParameter("key1"));
        assertEquals("value2", result.queryParameter("key2"));
    }

    @Test
    public void testUrlWithParamsAndExistingQuery() {
        Map<String, String> params = new HashMap<>();
        params.put("extra", "param");

        HttpUrl result = WoodHttpRequestUrl.createUrl("http://example.com/api?existing=1", params);
        assertNotNull(result);
        assertEquals("1", result.queryParameter("existing"));
        assertEquals("param", result.queryParameter("extra"));
    }

    @Test
    public void testUrlWithWhitespaceProtocol() {
        HttpUrl result = WoodHttpRequestUrl.createUrl("  https://example.com  ", null);
        assertNotNull(result);
        assertEquals("https", result.scheme());
    }

    @Test(expected = Exception.class)
    public void testInvalidUrlThrowsException() {
        // Bug1 检测：修复前应抛 NPE，修复后应抛 IllegalArgumentException
        WoodHttpRequestUrl.createUrl("::not-a-valid-url::", null);
    }

    @Test(expected = Exception.class)
    public void testNullUrlThrowsException() {
        WoodHttpRequestUrl.createUrl(null, null);
    }

    @Test(expected = Exception.class)
    public void testEmptyUrlThrowsException() {
        WoodHttpRequestUrl.createUrl("", null);
    }

    @Test(expected = Exception.class)
    public void testBlankUrlThrowsException() {
        WoodHttpRequestUrl.createUrl("   ", null);
    }
}
