package com.xidian.mti.rpc.service.impl;

import com.xidian.mti.rpc.service.EchoService;

/**
 * 文件描述：回复服务接口实现类
 * 创建作者：陈苗
 * 创建时间：2017/5/25 21:33
 */
public class EchoServiceImpl implements EchoService {
    /**
     * 回复接口
     *
     * @param name
     * @return
     */
    public String echo(String name) {
        return name.toLowerCase().indexOf("server") >= 0 ? name.toLowerCase().replace("server", "client") : name;
    }
}
