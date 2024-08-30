package io.github.loulangogogo.ask;

import io.github.loulangogogo.exception.WoodRequestException;
import io.github.loulangogogo.water.tool.ObjectTool;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.io.InputStream;

/*********************************************************
 ** 请求响应处理
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
public class HttpResponseTool {

    /**
     * 获取响应体数据的输入流
     *
     * @param response 响应对象
     * @return 响应体输入流
     * @author :loulan
     */
    public static InputStream toInputStream(Response response) {
        ResponseBody responseBody = getResponseBody(response);
        if (ObjectTool.isNull(responseBody)) {
            return null;
        } else {
            return responseBody.byteStream();
        }
    }

    /**
     * 获取响应体数据字节数组数据
     *
     * @param response 响应对象
     * @return 响应体字节数组
     * @author :loulan
     */
    public static byte[] toByteArray(Response response) {
        ResponseBody responseBody = getResponseBody(response);
        if (ObjectTool.isNull(responseBody)) {
            return null;
        } else {
            try {
                return responseBody.bytes();
            } catch (IOException ex) {
                throw new WoodRequestException(ex);
            }
        }
    }

    /**
     * 获取响应体数据的字符串
     *
     * @param response 响应对象
     * @return 响应体字符串
     * @author :loulan
     */
    public static String toStr(Response response) {
        ResponseBody responseBody = getResponseBody(response);
        if (ObjectTool.isNull(responseBody)) {
            return null;
        } else {
            try {
                return responseBody.string();
            } catch (IOException ex) {
                throw new WoodRequestException(ex);
            }
        }
    }

    /**
     * 获取响应体
     *
     * @param response 响应对象
     * @return {@link ResponseBody}对象
     * @author :loulan
     */
    private static ResponseBody getResponseBody(Response response) {
        return response.body();
    }
}
