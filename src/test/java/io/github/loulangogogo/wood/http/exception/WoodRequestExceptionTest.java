package io.github.loulangogogo.wood.http.exception;

import io.github.loulangogogo.wood.http.exception.WoodRequestException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 测试 WoodRequestException 异常构造器。
 */
public class WoodRequestExceptionTest {

    @Test
    public void testNoArgConstructor() {
        WoodRequestException ex = new WoodRequestException();
        assertNull(ex.getMessage());
    }

    @Test
    public void testMessageConstructor() {
        WoodRequestException ex = new WoodRequestException("请求失败");
        assertEquals("请求失败", ex.getMessage());
    }

    @Test
    public void testMessageAndCauseConstructor() {
        Throwable cause = new RuntimeException("原始异常");
        WoodRequestException ex = new WoodRequestException("包装消息", cause);
        assertEquals("包装消息", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }

    @Test
    public void testCauseConstructor() {
        Throwable cause = new RuntimeException("原始异常");
        WoodRequestException ex = new WoodRequestException(cause);
        assertEquals(cause, ex.getCause());
        assertEquals("java.lang.RuntimeException: 原始异常", ex.getMessage());
    }
}
