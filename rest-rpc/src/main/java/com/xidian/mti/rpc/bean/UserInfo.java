package com.xidian.mti.rpc.bean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 文件描述：用户信息获取
 * 创建作者：陈苗
 * 创建时间：2017/5/28 21:58
 */
@Path("user")
public class UserInfo {
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.TEXT_XML)
    public String username(@PathParam("name") String username) {
        return "<User><Name>" + username + "</Name></User>";
    }

    @GET
    @Path("/age/{age}")
    @Produces(MediaType.TEXT_XML)
    public String userage(@PathParam("age") Integer age) {
        return "<User><Age>" + age + "</Age></User>";
    }
}
