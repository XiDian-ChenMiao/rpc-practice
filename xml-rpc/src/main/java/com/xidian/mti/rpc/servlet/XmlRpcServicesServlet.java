package com.xidian.mti.rpc.servlet;

import com.xidian.mti.rpc.service.impl.EchoServiceImpl;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件描述：XML-RPC的服务Servlet
 * 创建作者：陈苗
 * 创建时间：2017/5/25 21:36
 */
public class XmlRpcServicesServlet extends HttpServlet {
    private XmlRpcServletServer server;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            server = new XmlRpcServletServer();
            PropertyHandlerMapping mapping = new PropertyHandlerMapping();
            mapping.addHandler("EchoService", EchoServiceImpl.class);
            server.setHandlerMapping(mapping);
            XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) server.getConfig();
            serverConfig.setEnabledForExceptions(true);
            serverConfig.setContentLengthOptional(false);
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        server.execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        server.execute(req, resp);
    }
}
