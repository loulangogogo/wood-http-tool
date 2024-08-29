package io.github.loulangogogo;

import io.github.loulangogogo.water.tool.AssertTool;
import io.github.loulangogogo.water.tool.StrTool;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;

/*********************************************************
 ** 请求体
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
class WoodHttpRequestBody {

    /**
     * 创建请求体
     *
     * @param body 请求体的json字符串
     * @return {@link RequestBody}请求体对象
     * @author :loulan
     */
    public static RequestBody createRequestBody(String body) {
        // 生成请求体，如果body存在就正常盛昌，如果不存在就生成空请求体
        RequestBody requestBody = RequestBody.create(
                StrTool.isNotEmpty(body) ? body : "",
                MediaType.get("application/json; charset=utf-8")
        );

        return requestBody;
    }

    /**
     * 创建文件上传请求体
     *
     * @param file     要进行上传的文件
     * @param bodyName 请求体参数名称
     * @return {@link RequestBody}请求体对象
     * @author :loulan
     */
    public static RequestBody createFileRequestBody(File file, String bodyName) {
        AssertTool.notEmpty(bodyName, "请求体参数名称不能为空");
        AssertTool.notNull(file, "文件不能为空");
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(bodyName, file.getName(), RequestBody.create(file, MediaType.get("application/octet-stream")))
                .build();
    }

    /**
     * 创建文件上传请求体
     *
     * @param bytes    文件字节数组
     * @param bodyName 请求体参数名称
     * @return {@link RequestBody}请求体对象
     * @author :loulan
     */
    public static RequestBody createFileRequestBody(byte[] bytes,String fileName ,String bodyName) {
        AssertTool.notEmpty(bodyName, "请求体参数名称不能为空");
        AssertTool.notNull(bytes, "文件不能为空");
        AssertTool.notNull(fileName,"文件名不能为空");
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(bodyName, fileName, RequestBody.create(bytes, MediaType.get("application/octet-stream")))
                .build();
    }
}
