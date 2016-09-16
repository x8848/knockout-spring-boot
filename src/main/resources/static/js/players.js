function ViewModel() {
    var self = this;

    self.players = ko.observableArray();
    self.playerContent = ko.observableArray();

    self.goToPlayer = function (player) {
        location.hash = player.playerId
    };

    Sammy(function () {
        this.get('#:playerId', function () {
            $.getJSON("/api/player/" + this.params.playerId, function (content) {
                for (var key in content) {
                    self.playerContent.push({
                        "key": key,
                        "value": content[key]
                    });
                }
                self.players(null);
            });
        });

        this.get('', function () {
            $.getJSON("/api/players", function (players) {
                self.playerContent.removeAll();
                self.players(players);
            });
        });
    }).run();
};

ko.applyBindings(new ViewModel());

function goBack() {
    window.history.back();
}


