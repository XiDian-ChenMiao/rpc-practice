# RPC总结

## 功能目标

RPC的主要功能目标是让构建分布式计算应用更容易，在提供强大的远程调用能力时不损失本地调用的语义简洁性。为实现该目标，RPC框架需提供一种透明调用机制让使用者不必显式的区分本地调用和远程调用。

## 调用分类

**同步调用**
客户端等待调用执行完成并返回结果；

**异步调用**
客户调用后不必等待执行结果返回，但依然可以通过回调通知等方式获取返回结果。若客户端不关系调用返回结果，则变成单向异步调用，单向调用不用返回结果。

## 结构分析

![RPC调用图](http://img.blog.csdn.net/20150108170231000?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbWluZGZsb2F0aW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

RPC 服务方通过 RpcServer 去导出（export）远程接口方法，而客户方通过 RpcClient 去引入（import）远程接口方法。客户方像调用本地方法一样去调用远程接口方法，RPC 框架提供接口的代理实现，实际的调用将委托给代理RpcProxy 。代理封装调用信息并将调用转交给RpcInvoker 去实际执行。在客户端的RpcInvoker 通过连接器RpcConnector 去维持与服务端的通道RpcChannel，并使用RpcProtocol 执行协议编码（encode）并将编码后的请求消息通过通道发送给服务方。

RPC 服务端接收器 RpcAcceptor 接收客户端的调用请求，同样使用RpcProtocol 执行协议解码（decode）。解码后的调用信息传递给RpcProcessor 去控制处理调用过程，最后再委托调用给RpcInvoker 去实际执行并返回调用结果。

## 组件职责

**RpcServer** 
负责导出（export）远程接口；

**RpcClient**
负责导入（import）远程接口的代理实现；

**RpcProxy**
远程接口的代理实现；

**RpcInvoker**
客户方实现：负责编码调用信息和发送调用请求到服务方并等待调用结果返回；服务方实现：负责调用服务端接口的具体实现并返回调用结果；

**RpcProtocol**
负责协议编/解码；

**RpcConnector**
负责维持客户方与服务方的连接通道和发送数据到服务方；

**RpcAcceptor**
负责接收客户方请求并返回请求结果；

**RpcProcessor**
负责在服务方控制调用过程，包括管理调用线程池，超时时间等；

**RpcChannel**
数据传输通道。

## RPC知识储备

ICE（Internet Communications Engine）是ZeroC提供的一款高性能的中间件，基于ICE可以实现电信级的解决方案。基于ICE的数据层可以在未来方便的进行扩展，ICE支持分布式的部署管理，消息中间件以及网格计算。

SOAP（Simple Object Access Protocol）简单对象访问协议，是一个通信协议，SOAP在HTTP协议的基础上，把编写成的XML的Request参数，放在Http请求体上提交到Web Server处理完成后，结果也写成XML作为Response送回客户端，为了使用户端Web Service可以相互对应，可以使用WSDL作为这种通信方式的描述文件，利用WSDL工具可以自动生成WS和用户端的框架文件，SOAP具备把复杂对象序列化捆绑到XML的能力。
SOAP的前身是RPC，这个协议安全性不是很好，多数防火墙都会阻挡RPC的通信包，而SOAP则使用HTTP协议作为基本的协议，使用端口80使得SOAP可以透过防火墙，完成RPC的功能。

## RPC应用

### XML-RPC

XML-RPC的定义是工作在互联网上的远程过程调用协议，它可以允许软件运行在分布式的系统之上，通过互联网进行软件程序之间的调用，其传输协议是HTTP，传送数据编码格式是XML，由于是通过HTTP传输数据，因此基于XML-RPC的软件不受操作环境、编程语言等限制，由于是通过HTTP协议传输，因此可以通过防火墙的限制，具有简单易用等特点。XML-RPC只是一个协议，Apache的XML-RPC是开源的一个实现。其缺点如下：

- XML-RPC的消息系统过于简单，没有完整意义上的消息模型；
- XML-RPC调用服务的方式要求直接指定对象和方法，称不上完整的面向服务的体系；
- XML-RPC服务器端提供的服务实际上是特定对象的某个方法，限制了服务器端的开发。

**数据格式**


### JSON-RPC



