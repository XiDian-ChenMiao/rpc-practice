import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 文件描述：JSON-RPC测试类
 * 创建作者：陈苗
 * 创建时间：2017/5/26 16:58
 */
public class JsonRpcServicesTest {
    /**
     * 客户端远程RPC使用Echo服务
     */
    public static void main(String[] args) throws Throwable {
        Properties properties = new Properties();
        properties.load(JsonRpcServicesTest.class.getClassLoader().getResourceAsStream("service-config.properties"));
        JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(properties.getProperty("echo")));
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("UserKey", "daqinzhidi");
        client.setHeaders(headers);
        String[] send = new String[] {"Hello, Server!"};
        String response = client.invoke("echo", send, String.class);
        System.out.println("客户端收到：" + response + "，收到时间为：" + System.currentTimeMillis());
    }
}
