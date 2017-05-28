package com.xidian.mti.rpc.service;

/**
 * 文件描述：回复服务接口
 * 创建作者：陈苗
 * 创建时间：2017/5/25 21:31
 */
public interface EchoService {
    /**
     * 回复接口
     * @param request
     * @return
     */
    String echo(String request);
}
