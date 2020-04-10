<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Java 原生 WebSocket 的 Jetty 实现</title>
</head>
<body>
  Welcome <br><input id="text" type="text" />
<button onclick="send()">发送消息</button>
<hr>
<button onclick="closeWebScoekt()">关闭WEB SOCKET 连接</button>
<hr>
<div id="message"></div>
</body>


<script type="application/javascript">

    var ws = null;
    // 判断当前浏览器是否支持 ws
    if('WebSocket' in window){
        ws = new WebSocket("ws://localhost:8080/simpleWs");
    }else {
        alert("当前浏览器不支持WS");
    }
    
    // 连接发生错误回调方法
    ws.onerror = function () {
        setMessageInnerHTML("WS连接发生错误");
    }

    // 连接成功建立的回调方法
    ws.onopen = function () {
        setMessageInnerHTML("WS 连接成功");
    }

    //接收到消息的回调方法
    ws.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }

    // 关闭连接
    function closeWebScoekt () {
        closeWs();
    }

    // 将消息展示到页面
    function setMessageInnerHTML(innerHTML) {
        document.getElementById("message").innerHTML += innerHTML + "<br/>";
    }

    // 关闭 ws 连接
    function closeWs() {
        ws.close();
    }

    // 发送消息
    function send() {
        var message = document.getElementById("text").value;
        ws.send(message);
    }
    

</script>

</html>