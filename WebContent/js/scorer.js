var name;
var serverURL = "http://localhost:8080/Scorer/rest/scorer";

function JSONifyUserAndPassword() {
    return JSON.stringify({
        "userName": $("#name").val(),
        "password": $("#password").val()
    });
}

function getScores(game) {
    $.ajax({
        url: serverURL + "/scores",
        type: "GET",
        contentType: "application/json",
        data: {
            game: game,
            userName: name
        },
        dataType: "json",
        success: function (response) {
            var table = "";
            //Get the scores
            var scores = $.parseJSON(response.scores);
            //Add every score data to a table row
            for (var i = scores.length - 1; i >= 0; i--) {
                table += "<tr><td>" + scores[i].score + "</td><td>" +
                        scores[i].date + "</td></tr>";
            }

            //Show the table
            $("#results").html(table);
        }
    });
}

$(document).ready(function () {
    $("#btnLogin").click(function () {
        name = $("#name").val();
        $("#welcome").css("display", "none");
        $("#userData").css("display", "");

        //Show name
        $("#userName").html("Welcome " + name);
        //Show games, get them by ajax
        $.ajax({
            url: serverURL + "/scores",
            type: "GET",
            contentType: "application/json",
            data: {
                userName: name
            },
            dataType: "json",
            success: function (response) {
                var grid = "<div class='row center'>";
                //Get the games
                var games = $.parseJSON(response.games);
                //Add every game data to the grid
                for (var i = 0; i < games.length; i++) {
                    grid += "<div class='col s6'><button class='btn getScore waves-effect waves-light'>" + games[i] + "</button></div>";
                }
                grid += "</div>";

                //Show the grid
                $("#games").html(grid);
            }
        });
    });

    $("#btnRegister").click(function () {
        //Get data
        var name = $("#name").val();
        var password = $("#password").val();

        //Validate data
        if (!name.trim()) {
            alert("User name can't be empty");
        }
        else if (!password.trim()) {
            alert("Password can't be empty");
        }
        else {
            //Send request to server
            $.ajax({
                url: serverURL + "/register",
                type: "POST",
                contentType: "application/json",
                data: JSONifyUserAndPassword(),
                dataType: "json",
                success: function (response) {
                    code = response.response;

                    if (code === false)
                        alert("User already exists");
                    else{
                        alert("User created");
                        window.location = "index.html";
                    }
                }
            });
        }
    });

    //Dynamic callback assignment
    $(document).on("click", ".getScore", function () {
        //Show the table
        $("#scoreTable").css("display", "");

        //Get the scores
        getScores($(this).text());
    });

    $(".tbi").click(function () {
        alert("To be implemented");
    });

});