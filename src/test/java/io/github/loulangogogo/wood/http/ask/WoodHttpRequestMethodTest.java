package io.github.loulangogogo.wood.http.ask;

import io.github.loulangogogo.wood.http.ask.WoodHttpRequestMethod;
import io.github.loulangogogo.wood.http.enums.HttpMethod;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 测试 WoodHttpRequestMethod 的请求方法设置逻辑。
 */
public class WoodHttpRequestMethodTest {

    private static final RequestBody DUMMY_BODY =
            RequestBody.create("test body", MediaType.get("text/plain"));

    /**
     * 测试设置 GET 请求方法后，构建的 request 方法名为 GET
     */
    @Test
    public void testSetGetMethod() {
        Request.Builder builder = new Request.Builder().url("http://example.com");
        WoodHttpRequestMethod.setMethod(builder, HttpMethod.GET, DUMMY_BODY);
        Request request = builder.build();
        assertEquals("GET", request.method());
    }

    /**
     * 测试设置 POST 请求方法后，构建的 request 方法名为 POST 且 body 不为空
     */
    @Test
    public void testSetPostMethod() {
        Request.Builder builder = new Request.Builder().url("http://example.com");
        WoodHttpRequestMethod.setMethod(builder, HttpMethod.POST, DUMMY_BODY);
        Request request = builder.build();
        assertEquals("POST", request.method());
        assertNotNull(request.body());
    }

    /**
     * 测试设置 PUT 请求方法后，构建的 request 方法名为 PUT 且 body 不为空
     */
    @Test
    public void testSetPutMethod() {
        Request.Builder builder = new Request.Builder().url("http://example.com");
        WoodHttpRequestMethod.setMethod(builder, HttpMethod.PUT, DUMMY_BODY);
        Request request = builder.build();
        assertEquals("PUT", request.method());
        assertNotNull(request.body());
    }

    /**
     * 测试设置 DELETE 请求方法后，构建的 request 方法名为 DELETE
     */
    @Test
    public void testSetDeleteMethod() {
        Request.Builder builder = new Request.Builder().url("http://example.com");
        WoodHttpRequestMethod.setMethod(builder, HttpMethod.DELETE, DUMMY_BODY);
        Request request = builder.build();
        assertEquals("DELETE", request.method());
    }

    /**
     * 测试设置 HEAD 请求方法后，构建的 request 方法名为 HEAD
     */
    @Test
    public void testSetHeadMethod() {
        Request.Builder builder = new Request.Builder().url("http://example.com");
        WoodHttpRequestMethod.setMethod(builder, HttpMethod.HEAD, DUMMY_BODY);
        Request request = builder.build();
        assertEquals("HEAD", request.method());
    }

    /**
     * 测试设置 PATCH 请求方法后，构建的 request 方法名为 PATCH 且 body 不为空
     */
    @Test
    public void testSetPatchMethod() {
        Request.Builder builder = new Request.Builder().url("http://example.com");
        WoodHttpRequestMethod.setMethod(builder, HttpMethod.PATCH, DUMMY_BODY);
        Request request = builder.build();
        assertEquals("PATCH", request.method());
        assertNotNull(request.body());
    }

    /**
     * 测试传入 null 方法时应抛出异常
     */
    @Test(expected = Exception.class)
    public void testNullMethodThrowsException() {
        Request.Builder builder = new Request.Builder();
        WoodHttpRequestMethod.setMethod(builder, null, DUMMY_BODY);
    }

    /**
     * 测试传入 null builder 时应抛出异常
     */
    @Test(expected = Exception.class)
    public void testNullBuilderThrowsException() {
        WoodHttpRequestMethod.setMethod(null, HttpMethod.GET, DUMMY_BODY);
    }
}
