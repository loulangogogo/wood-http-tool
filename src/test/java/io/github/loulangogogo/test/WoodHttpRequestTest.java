package io.github.loulangogogo.test;

import io.github.loulangogogo.HttpMethod;
import io.github.loulangogogo.WoodHttpRequest;
import io.github.loulangogogo.water.crypto.Base64Tool;
import io.github.loulangogogo.water.io.IoTool;
import io.github.loulangogogo.water.json.JsonMap;
import io.github.loulangogogo.water.json.JsonTool;
import okhttp3.Response;
import org.junit.Test;

import java.io.*;

/*********************************************************
 ** 测试类{@link io.github.loulangogogo.WoodHttpRequest}
 **
 ** @author loulan
 ** @since
 *********************************************************/
public class WoodHttpRequestTest {

    @Test
    public void request01() throws IOException {
        Response response = WoodHttpRequest.request("admin.hltq.cn", HttpMethod.GET, null, null, null);
        System.out.println(response.body().string());
    }

    @Test
    public void request02() throws IOException {
        File file = new File("target/classes/291625.png");
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        IoTool.copy(fis,bos);
        Response response = WoodHttpRequest.uploadFile("http://127.0.0.1:8891/file/upload-multipart-file/false", HttpMethod.POST, null, null, bos.toByteArray(),file.getName(),"file");
        System.out.println(response.body().string());
    }

    @Test
    public void request03() throws IOException {
        Response response = WoodHttpRequest.request("http://127.0.0.1:8891/file/download-file-byte-by-id?fileId=3912", HttpMethod.GET, null, null, null);
        FileOutputStream fileOutputStream = new FileOutputStream("target/classes/xxxx.png");
        String res = response.body().string();
        System.out.println(res);
        JsonMap jsonMap = JsonTool.parseJsonMap(res);
        String data = jsonMap.getString("data");
        InputStream inputStream = new ByteArrayInputStream(Base64Tool.toDecode(data));
        IoTool.copy(inputStream,fileOutputStream);
    }
}
