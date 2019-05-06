var socket, stompClient;

function connect() {

    socket = new SockJS('/farm');

    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {

        stompClient.subscribe('/topic/farm/' + getFarmName(), function (data) {
            handleIncomingResponse(JSON.parse(data.body));
        });

        stompClient.subscribe('/topic/farm/message/' + getFarmName(), function (data) {
            handleErrorMessage(JSON.parse(data.body));
        });

        stompClient.subscribe('/topic/farm/tourists/' + getFarmName(), function (data) {
            handleTourists(JSON.parse(data.body));
        });

        stompClient.send("/app/farm/get/" + getFarmName(), {},);

    }, function (error) {

        console.log(error);
    });
}

function handleIncomingResponse(data) {
    renderFarm(data);
    renderDashboard(data);
}

function handleErrorMessage(data) {
    setMessage(data.message);
}

function handleTourists(data) {
    setTouristList(data);
}