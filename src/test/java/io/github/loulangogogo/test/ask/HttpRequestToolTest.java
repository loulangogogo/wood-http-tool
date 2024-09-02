package io.github.loulangogogo.test.ask;

import io.github.loulangogogo.ask.HttpRequestTool;
import io.github.loulangogogo.enums.HttpMethod;
import io.github.loulangogogo.water.io.IoTool;
import okhttp3.Response;
import org.junit.Test;

import java.io.*;

/*********************************************************
 ** 测试类{@link HttpRequestTool}
 **
 ** @author loulan
 ** @since
 *********************************************************/
public class HttpRequestToolTest {
    /**
     * get请求
     * @param
     * @return
     * @exception
     * @author     :loulan
     * */
    @Test
    public void request01() throws IOException {
        Response response = HttpRequestTool.request("admin.hltq.cn", HttpMethod.GET, null, null, null);
        System.out.println(response.body().string());
    }

    /**
     * 文件的上床
     * @param
     * @return
     * @exception
     * @author     :loulan
     * */
    @Test
    public void uploadFile01() throws IOException {
        File file = new File("target/classes/xxx.png");
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        IoTool.copy(fis,bos);
        Response response = HttpRequestTool.uploadFile("http://127.0.0.1:8891/file/upload-multipart-file/false", HttpMethod.POST, null, null, bos.toByteArray(),file.getName(),"file");
        System.out.println(response.body().string());
    }

    /**
     * 文件图片的下载测试
     * @param
     * @return
     * @exception
     * @author     :loulan
     * */
    @Test
    public void request03() throws IOException {
        Response response = HttpRequestTool.request("https://inews.gtimg.com/om_bt/O3Sc2E9H7N-wJd68AWa_UAGNZLwoJ_Iap-pfD1ZGhtPyUAA/641", HttpMethod.GET, null, null, null);
        FileOutputStream fileOutputStream = new FileOutputStream("target/classes/xxxx.png");
        IoTool.copy(response.body().byteStream(),fileOutputStream);
    }
}
