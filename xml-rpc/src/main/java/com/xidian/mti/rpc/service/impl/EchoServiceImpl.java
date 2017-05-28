package com.xidian.mti.rpc.service.impl;

import com.xidian.mti.rpc.service.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件描述：回复服务接口实现类
 * 创建作者：陈苗
 * 创建时间：2017/5/25 21:33
 */
public class EchoServiceImpl implements EchoService {
    private final static Logger logger = LoggerFactory.getLogger(EchoServiceImpl.class);
    /**
     * 回复接口
     *
     * @param info
     * @return
     */
    public String echo(String info) {
        logger.info("服务器收到：" + info + "，收到时间为：" + System.currentTimeMillis());
        return info.toLowerCase().indexOf("server") >= 0 ? info.toLowerCase().replace("server", "client") : info;
    }
}
