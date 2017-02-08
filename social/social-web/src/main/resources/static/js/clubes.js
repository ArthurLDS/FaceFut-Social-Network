
var jogos = {};

$(function () {
    jogos.carregarElementos();
    jogos.carregarPlacares();
});

jogos.carregarElementos = function () {
    jogos.jogosContent = $('#box-jogos');
}

jogos.carregarPlacares = function () {
    $.getJSON('https://raw.githubusercontent.com/opendatajson/football.json/master/2016-17/en.1.json', function (json) {
        let rounds = json.rounds.map(r => r.matches);
        jogos.renderizarJogosPorData(rounds);
    });
};

jogos.renderizarJogosPorData = function(rounds) {
    
    for (var i = 0; i < rounds.length; i++) {
        for (var j = 0; j < rounds[i].length; j++) {
            if(rounds[i][j].date.includes("2017-02-11"))
                jogos.jogosContent.append(rounds[i][j].team1.code + " x " + rounds[i][j].team2.code + "<br/>Data: " + rounds[i][j].date + "<br/><br/>");
        }
    }
};



var a = [
    [{nome: "Arthur"}],
    [{nome: "Messias"}],
    [{nome: "Rafael"}]
];