import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Vector;

/**
 * 文件描述：XML-RPC远程调用测试类
 * 创建作者：陈苗
 * 创建时间：2017/5/25 21:58
 */
public class XmlRpcServicesTest {

    /**
     * 客户端远程RPC使用Echo服务
     */
    public static void main(String[] args) throws IOException, XmlRpcException {
        Properties properties = new Properties();
        properties.load(XmlRpcServicesTest.class.getClassLoader().getResourceAsStream("service-config.properties"));
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(properties.getProperty("echo")));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        Vector<String> params = new Vector<String>();
        params.addElement("Hello, Server!");
        String result = (String) client.execute("EchoService.echo", params);
        System.out.println("客户端收到：" + result + "，收到时间为：" + System.currentTimeMillis());
    }
}
