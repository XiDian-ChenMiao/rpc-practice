package com.xidian.mti.rpc.servlet;

import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.xidian.mti.rpc.service.EchoService;
import com.xidian.mti.rpc.service.impl.EchoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件描述：JSON-RPC服务器端Servlet
 * 创建作者：陈苗
 * 创建时间：2017/5/26 16:51
 */
public class JsonRpcServicesServlet extends HttpServlet {

    private JsonRpcServer server = null;

    @Override
    public void init() throws ServletException {
        super.init();
        server = new JsonRpcServer(new EchoServiceImpl(), EchoService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        System.out.println("GET请求");
        server.handle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        System.out.println("POST请求");
        server.handle(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
