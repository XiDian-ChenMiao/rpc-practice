package com.xidian.mti.rpc.servlet;

import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.xidian.mti.rpc.service.EchoService;
import com.xidian.mti.rpc.service.impl.EchoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final static Logger logger = LoggerFactory.getLogger(JsonRpcServicesServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        server = new JsonRpcServer(new EchoServiceImpl(), EchoService.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userkey = req.getHeader("UserKey");
        if ("daqinzhidi".equals(userkey)) {
            logger.info("received request, addr = {}, method = {}", req.getRemoteAddr(), req.getMethod());
            server.handle(req, resp);
        }
    }
}
