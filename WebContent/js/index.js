var name;

function getScores(game) {
    $.ajax({
        url: "http://localhost:8080/Scorer/rest/scorer/scores",
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
            for (var i = scores.length - 1; i >= 0; i--){
                table += "<tr><td>" + scores[i].score + "</td><td>"+
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
            url: "http://localhost:8080/Scorer/rest/scorer/scores",
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
    
    //Dynamic callback assignment
    $(document).on("click", ".getScore", function(){        
        //Show the table
        $("#scoreTable").css("display", "");
        
        //Get the scores
        getScores($(this).text());
    });

    $(".tbi").click(function () {
        alert("To be implemented");
    });

});