$(document).ready(function() {
    var wsUri = "ws://localhost:8080/ProjectChess_war_exploded/chat";
    var wsUri2 = "ws://localhost:8080/ProjectChess_war_exploded/chess";

    var websocket = new WebSocket(wsUri);

    var websocket2 = new WebSocket(wsUri2);


    websocket.onerror = function(evt) { onError(evt) };

    websocket2.onerror = function(evt) { onError2(evt) };


    setTimeout(function() {
        websocket.send(hiddenName.value);
    }, 500);


    $("#sender").click(function (){
        sendMessage();
        $('#messageText').removeAttr('placeholder')
    });

    $('#messageText').keypress(function(e) {
        if (e.which == 13) {
            sendMessage();
            $('#messageText').removeAttr('placeholder')
        }
    });



    $("#sendmove").click(function (){
        sendMove();
    });

    function onError(evt) {
        writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
    }

    function onError2(evt) {
        writeToScreen2('<span style="color: red;">ERROR:</span> ' + evt.data);
    }


    // For testing purposes
    var output = document.getElementById("output");
    var output2 = document.getElementById("output2");

    websocket.onopen = function(evt) { onOpen(evt) };

    websocket2.onopen = function(evt) { onOpen2(evt) };


    function writeToScreen(message) {
        output.innerHTML += message + "<br>";
    }

    function writeToScreen2(message) {
        output2.innerHTML += message + "<br>";
    }

    function onOpen() {
        writeToScreen("Connected to " + wsUri);
    }
    function onOpen2() {
        writeToScreen2("Connected to " + wsUri2);
    }

    websocket.onmessage = function processMessage(message){
        var jsonData = JSON.parse(message.data);
        if(jsonData.message != null) messagesTextArea.value += jsonData.message + "\n";
    }
    function sendMessage(){
        websocket.send(messageText.value);
        messageText.value = "";
    }


    websocket2.onmessage = function processMove(move){
        var jsonData = JSON.parse(move.data);
        if(jsonData.moveFrom != null && jsonData.moveTo != null)
            setPiece(jsonData.moveTo,getPiece(jsonData.moveFrom));
            setPiece(jsonData.moveFrom,"")
            movesTextArea.value += ">>" + jsonData.moveFrom + " " + jsonData.moveTo + "\n";
    }

    function sendMove(){
        websocket2.send(moveFrom.value + " " + moveGo.value);
        moveFrom.value = "";
        moveGo.value = "";
    }


    //CHESS
    const board = qs(".board");
    const b = {}; // board lookup

    setupBoardLookup();
    drawLetters();
    drawNumbers();
    setupWhite();
    setupBlack();

// ----------------------------

    function drawLetters() {
        const a = String.fromCharCode(97);
        for (let i = 0; i < 8; i++) getCell(i + 74).textContent = getLetter(i);
    }

    function drawNumbers() {
        for (let i = 0; i < 8; i++) getCell(i * 9 + 1).textContent = 8 - i;
    }

    function setupWhite() {
        setPiece("a8", "♜");
        setPiece("h8", "♜");
        setPiece("b8", "♞");
        setPiece("g8", "♞");
        setPiece("c8", "♝");
        setPiece("f8", "♝");
        setPiece("e8", "♚");
        setPiece("d8", "♛");
        for (let i = 0; i < 8; i++) setPiece(getLetter(i) + "7", "♟");
    }

    function setupBlack() {
        setPiece("a1", "♖");
        setPiece("h1", "♖");
        setPiece("b1", "♘");
        setPiece("g1", "♘");
        setPiece("c1", "♗");
        setPiece("f1", "♗");
        setPiece("e1", "♔");
        setPiece("d1", "♕");
        for (let i = 0; i < 8; i++) setPiece(getLetter(i) + "2", "♙");
    }

    function setupBoardLookup() {
        for (let i = 0; i < 8; i++) b["a" + (8 - i)] = i * 9 + 2;
        for (let i = 0; i < 8; i++) b["b" + (8 - i)] = i * 9 + 3;
        for (let i = 0; i < 8; i++) b["c" + (8 - i)] = i * 9 + 4;
        for (let i = 0; i < 8; i++) b["d" + (8 - i)] = i * 9 + 5;
        for (let i = 0; i < 8; i++) b["e" + (8 - i)] = i * 9 + 6;
        for (let i = 0; i < 8; i++) b["f" + (8 - i)] = i * 9 + 7;
        for (let i = 0; i < 8; i++) b["g" + (8 - i)] = i * 9 + 8;
        for (let i = 0; i < 8; i++) b["h" + (8 - i)] = i * 9 + 9;
    }

// Utils
    function getCell(n) {
        return qs(`.cell:nth-of-type(${n})`, board);
    }

    function getLetter(n) {
        return String.fromCharCode(97 + n);
    }

    function setPiece(pos, piece) {
        setElement(getCell(b[pos]), piece, pos);
    }

    function setElement(el, piece, pos) {
        el.innerHTML = `<span class="${pos}">${piece}</span>`;
    }

    function getPiece(el){
        v = '.' + el;
        return $(v).text();
    }

    function qs(expr, context) {
        return (context || document).querySelector(expr);
    }

    function qsa(expr, context) {
        return [].slice.call((context || document).querySelectorAll(expr), 0);
    }

})