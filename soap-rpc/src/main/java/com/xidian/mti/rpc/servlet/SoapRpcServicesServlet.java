package com.xidian.mti.rpc.servlet;

import org.apache.soap.Constants;
import org.apache.soap.Fault;
import org.apache.soap.SOAPException;
import org.apache.soap.rpc.Call;
import org.apache.soap.rpc.Parameter;
import org.apache.soap.rpc.Response;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Vector;

/**
 * 文件描述：SOAP Servlet
 * 创建作者：陈苗
 * 创建时间：2017/5/29 16:36
 */
public class SoapRpcServicesServlet extends HttpServlet {
    private final String ENDPOINT;

    public SoapRpcServicesServlet() throws IOException {
        Properties properties = new Properties();
        properties.load(SoapRpcServicesServlet.class.getClassLoader().getResourceAsStream("service-config.properties"));
        ENDPOINT = properties.getProperty("endpoint");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Call call = new Call();/*创建一个RPC调用对象*/
        call.setTargetObjectURI("Echo");/*设置远程的服务名ID*/
        call.setMethodName("echo");/*设置访问的方法名*/
        call.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);/*设置编码风格*/
        Vector<Parameter> params = new Vector<Parameter>();
        Parameter param = new Parameter("name", String.class, "大秦之帝", null);
        params.add(param);
        call.setParams(params);
        URL url = new URL(ENDPOINT);
        Response response = null;
        try {
            response = call.invoke(url, "");
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        if (response.generatedFault()) {
            Fault fault = response.getFault();
            System.out.println("Fault Occured:");
            System.out.println("Fault Code:" + fault.getFaultCode());
            System.out.println("Fault String:" + fault.getFaultString());
        } else {
            Parameter result = response.getReturnValue();
            System.out.println("Return Value:" + result.getValue());
            req.setAttribute("result", result.getValue());
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
