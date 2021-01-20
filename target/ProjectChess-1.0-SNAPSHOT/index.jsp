<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ChessGame_css.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<% String name = request.getParameter("name"); %>
<!-- Script initialisation jquery -->
<div style="position: absolute; top:10px;left: 20%;">
    <h1>Chess App</h1>
    <div id="output"></div>
    <div id="output2"></div>
</div>

<div class="board-shadow"></div><div class="board">
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
    <div class="cell"></div>
</div>


<div class="chat">
    <textarea class="form-control" id = "movesTextArea" readonly="readonly" rows="12" cols="45"></textarea><br>
    <div style="display: flex;flex-direction: row">
        <input placeholder="Move from" class="form-control" type="text" id="moveFrom" size="25">
        <input placeholder="Move to" class="form-control" type="text" id="moveGo" size="25">
    </div>
    <input style="margin-top: 5px;" class="btn btn-primary" type="button" value="Send" id="sendmove" size="10">
    <br>

    <br>
    <textarea class="form-control" id = "messagesTextArea" readonly="readonly" rows="12" cols="45"></textarea><br>
    <input placeholder="Enter your name" class="form-control" type="text" id="messageText" size="50">
    <input style="margin-top: 5px;" class="btn btn-primary" type="button" value="Send" id="sender" size="10">
    <input id="hiddenName" value="<%=name%>" style="display: none">
</div>
<%--</body>--%>

<%--<body ng-app="myApp" ng-keydown="rm($event)" key-event>--%>
<%--<div class="floating-chat">--%>
<%--    <i class="fa fa-comments" aria-hidden="true">Chat</i>--%>
<%--    <div class="chat">--%>
<%--        <div class="header">--%>
<%--            <span class="title">--%>
<%--                what's on your mind?--%>
<%--            </span>--%>
<%--            <button>--%>
<%--                <i class="fa fa-times" aria-hidden="true">Close</i>--%>
<%--            </button>--%>

<%--        </div>--%>
<%--        <ul class="messages">--%>
<%--            <li class="other">Name1: Hello</li>--%>
<%--            <li class="self">Name2: Hello</li>--%>
<%--        </ul>--%>
<%--        <div class="footer">--%>
<%--            <div class="text-box" contenteditable="true" disabled="true"></div>--%>
<%--            <button id="sendMessage">send</button>--%>
<%--        </div>--%>
<%--    </div>--%>
<script src="${pageContext.request.contextPath}/js/chatroom.js"></script>
</body>
</html>