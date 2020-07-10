package net.bestdata.game.darkpassage.messages;

import lombok.Getter;

@Getter
public enum RequestType {
    SUMMONER("summoner", 1), SUMMONER_DETAIL("summonerDetail", 2);

    private String name;
    private int priority;

    RequestType(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
}
