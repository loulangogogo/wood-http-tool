package io.github.loulangogogo.wood.http.ask;

import io.github.loulangogogo.wood.http.ask.HttpResponseTool;
import okhttp3.*;
import okio.Buffer;
import okio.Okio;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

/**
 * 测试 HttpResponseTool 的响应处理逻辑。
 * 包含 Bug9 检测：验证 toStr 和 toByteArray 是否正确关闭 Response。
 */
public class HttpResponseToolTest {

    @Test
    public void testToStrReturnsResponseBody() throws IOException {
        TrackingResponse response = createTrackingResponse("hello world");
        String result = HttpResponseTool.toStr(response.delegate);
        assertEquals("hello world", result);
    }

    @Test
    public void testToByteArrayReturnsResponseBody() throws IOException {
        TrackingResponse response = createTrackingResponse("hello");
        byte[] result = HttpResponseTool.toByteArray(response.delegate);
        assertArrayEquals("hello".getBytes(), result);
    }

    @Test
    public void testToInputStreamReturnsStream() throws IOException {
        Response response = createResponse("stream data");
        InputStream is = HttpResponseTool.toInputStream(response);
        assertNotNull(is);
        response.close();
    }

    /**
     * Bug9 检测：验证 toStr 是否关闭了 Response。
     * 修复前：closed 为 false -> 测试失败。
     * 修复后：closed 为 true -> 测试通过。
     */
    @Test
    public void testToStrClosesResponse() throws IOException {
        TrackingResponse response = createTrackingResponse("test content");
        HttpResponseTool.toStr(response.delegate);
        assertTrue("Response body should be closed after toStr", response.bodyClosed.get());
    }

    @Test
    public void testToByteArrayClosesResponse() throws IOException {
        TrackingResponse response = createTrackingResponse("test content");
        HttpResponseTool.toByteArray(response.delegate);
        assertTrue("Response body should be closed after toByteArray", response.bodyClosed.get());
    }

    @Test
    public void testToStrWithEmptyBody() throws IOException {
        Response response = createResponse("");
        String result = HttpResponseTool.toStr(response);
        assertEquals("", result);
    }

    private Response createResponse(String bodyContent) throws IOException {
        return new Response.Builder()
                .request(new Request.Builder().url("http://example.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(ResponseBody.create(bodyContent, MediaType.get("text/plain; charset=utf-8")))
                .build();
    }

    private TrackingResponse createTrackingResponse(String bodyContent) {
        return new TrackingResponse(bodyContent);
    }

    private static class TrackingResponse {
        final AtomicBoolean bodyClosed = new AtomicBoolean(false);
        final Response delegate;

        TrackingResponse(String bodyContent) {
            Buffer buffer = new Buffer().writeUtf8(bodyContent);
            TrackingResponseBody trackingBody = new TrackingResponseBody(buffer, bodyClosed);
            this.delegate = new Response.Builder()
                    .request(new Request.Builder().url("http://example.com").build())
                    .protocol(Protocol.HTTP_1_1)
                    .code(200)
                    .message("OK")
                    .body(trackingBody)
                    .build();
        }
    }

    private static class TrackingResponseBody extends ResponseBody {
        private final Buffer buffer;
        private final AtomicBoolean closed;

        TrackingResponseBody(Buffer buffer, AtomicBoolean closed) {
            this.buffer = buffer;
            this.closed = closed;
        }

        @Override
        public MediaType contentType() {
            return MediaType.get("text/plain; charset=utf-8");
        }

        @Override
        public long contentLength() {
            return buffer.size();
        }

        @Override
        public okio.BufferedSource source() {
            return Okio.buffer(new okio.ForwardingSource(buffer) {
                @Override
                public void close() throws IOException {
                    closed.set(true);
                    super.close();
                }
            });
        }

        @Override
        public void close() {
            closed.set(true);
            buffer.close();
        }
    }
}
