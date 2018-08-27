# nginx

## 1. 概念
> 是一个高性能的HTTP和反向代理服务器，也是一个IMAP/POP3/SMIP服务器。Nginx是由Lgor Sysoev为俄罗斯访问量第二的Rambler.ru站点开发的，第一个公开版本0.1.0发布于2004年10月4日。
其将源代码以类BSD许可证的形式发布，因它的稳定性，丰富的功能集，示例配置文件和低系统资源的消耗而闻名。
2011年6月1日，nginx 1.0.4发布。

## 2. 组成
> nginx 由一个master 主进程多个worker进程，一般和系统内核数一致 ![nginx的进程模型](http://tengine.taobao.org/book/_images/chapter-2-1.PNG) [查看进程模型请到github查看]

## 3. kill -HUP pid
> 杀掉进程， HUP信号灯

## 4. 无缝对接
> nginx -s reload 

## 5. nginx的异步非阻塞
<pre>


</pre>