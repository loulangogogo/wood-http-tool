package io.github.loulangogogo.enums;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 测试 HttpMethod 枚举包含所有预期的 HTTP 方法。
 */
public class HttpMethodTest {

    @Test
    public void testAllHttpMethodsExist() {
        assertEquals(6, HttpMethod.values().length);
        assertNotNull(HttpMethod.GET);
        assertNotNull(HttpMethod.POST);
        assertNotNull(HttpMethod.HEAD);
        assertNotNull(HttpMethod.PUT);
        assertNotNull(HttpMethod.DELETE);
        assertNotNull(HttpMethod.PATCH);
    }

    @Test
    public void testValueOf() {
        assertEquals(HttpMethod.GET, HttpMethod.valueOf("GET"));
        assertEquals(HttpMethod.POST, HttpMethod.valueOf("POST"));
        assertEquals(HttpMethod.PATCH, HttpMethod.valueOf("PATCH"));
    }
}
