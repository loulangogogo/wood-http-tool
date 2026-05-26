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

    /**
     * 测试 http 协议的 URL 能正确解析 scheme、host 和 path
     */
    @Test
    public void testUrlWithHttpProtocol() {
        HttpUrl result = WoodHttpRequestUrl.createUrl("http://example.com/api", null);
        assertNotNull(result);
        assertEquals("http", result.scheme());
        assertEquals("example.com", result.host());
        assertEquals("/api", result.encodedPath());
    }

    /**
     * 测试 https 协议的 URL 能正确解析 scheme
     */
    @Test
    public void testUrlWithHttpsProtocol() {
        HttpUrl result = WoodHttpRequestUrl.createUrl("https://example.com/api", null);
        assertNotNull(result);
        assertEquals("https", result.scheme());
    }

    /**
     * 测试不带协议的 URL 应自动添加 http 前缀
     */
    @Test
    public void testUrlWithoutProtocolAddsHttp() {
        HttpUrl result = WoodHttpRequestUrl.createUrl("example.com/api", null);
        assertNotNull(result);
        assertEquals("http", result.scheme());
        assertEquals("example.com", result.host());
    }

    /**
     * 测试 URL 携带查询参数时能正确拼接
     */
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

    /**
     * 测试 URL 已有查询参数时，新增参数能正确追加
     */
    @Test
    public void testUrlWithParamsAndExistingQuery() {
        Map<String, String> params = new HashMap<>();
        params.put("extra", "param");

        HttpUrl result = WoodHttpRequestUrl.createUrl("http://example.com/api?existing=1", params);
        assertNotNull(result);
        assertEquals("1", result.queryParameter("existing"));
        assertEquals("param", result.queryParameter("extra"));
    }

    /**
     * 测试 URL 前后包含空白字符时能正确 trim 后解析
     */
    @Test
    public void testUrlWithWhitespaceProtocol() {
        HttpUrl result = WoodHttpRequestUrl.createUrl("  https://example.com  ", null);
        assertNotNull(result);
        assertEquals("https", result.scheme());
    }

    /**
     * 测试无效 URL 应抛出异常（Bug1检测：修复前抛NPE，修复后抛IllegalArgumentException）
     */
    @Test(expected = Exception.class)
    public void testInvalidUrlThrowsException() {
        WoodHttpRequestUrl.createUrl("::not-a-valid-url::", null);
    }

    /**
     * 测试 null URL 应抛出异常
     */
    @Test(expected = Exception.class)
    public void testNullUrlThrowsException() {
        WoodHttpRequestUrl.createUrl(null, null);
    }

    /**
     * 测试空字符串 URL 应抛出异常
     */
    @Test(expected = Exception.class)
    public void testEmptyUrlThrowsException() {
        WoodHttpRequestUrl.createUrl("", null);
    }

    /**
     * 测试纯空白 URL 应抛出异常
     */
    @Test(expected = Exception.class)
    public void testBlankUrlThrowsException() {
        WoodHttpRequestUrl.createUrl("   ", null);
    }
}
