package io.github.loulangogogo.ask;

import io.github.loulangogogo.enums.HttpMethod;
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

    @Test
    public void testSetGetMethod() {
        Request.Builder builder = new Request.Builder().url("http://example.com");
        WoodHttpRequestMethod.setMethod(builder, HttpMethod.GET, DUMMY_BODY);
        Request request = builder.build();
        assertEquals("GET", request.method());
    }

    @Test
    public void testSetPostMethod() {
        Request.Builder builder = new Request.Builder().url("http://example.com");
        WoodHttpRequestMethod.setMethod(builder, HttpMethod.POST, DUMMY_BODY);
        Request request = builder.build();
        assertEquals("POST", request.method());
        assertNotNull(request.body());
    }

    @Test
    public void testSetPutMethod() {
        Request.Builder builder = new Request.Builder().url("http://example.com");
        WoodHttpRequestMethod.setMethod(builder, HttpMethod.PUT, DUMMY_BODY);
        Request request = builder.build();
        assertEquals("PUT", request.method());
        assertNotNull(request.body());
    }

    @Test
    public void testSetDeleteMethod() {
        Request.Builder builder = new Request.Builder().url("http://example.com");
        WoodHttpRequestMethod.setMethod(builder, HttpMethod.DELETE, DUMMY_BODY);
        Request request = builder.build();
        assertEquals("DELETE", request.method());
    }

    @Test
    public void testSetHeadMethod() {
        Request.Builder builder = new Request.Builder().url("http://example.com");
        WoodHttpRequestMethod.setMethod(builder, HttpMethod.HEAD, DUMMY_BODY);
        Request request = builder.build();
        assertEquals("HEAD", request.method());
    }

    @Test
    public void testSetPatchMethod() {
        Request.Builder builder = new Request.Builder().url("http://example.com");
        WoodHttpRequestMethod.setMethod(builder, HttpMethod.PATCH, DUMMY_BODY);
        Request request = builder.build();
        assertEquals("PATCH", request.method());
        assertNotNull(request.body());
    }

    @Test(expected = Exception.class)
    public void testNullMethodThrowsException() {
        Request.Builder builder = new Request.Builder();
        WoodHttpRequestMethod.setMethod(builder, null, DUMMY_BODY);
    }

    @Test(expected = Exception.class)
    public void testNullBuilderThrowsException() {
        WoodHttpRequestMethod.setMethod(null, HttpMethod.GET, DUMMY_BODY);
    }
}
