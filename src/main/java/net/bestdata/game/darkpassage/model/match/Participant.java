package net.bestdata.game.darkpassage.model.match;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Participant {
    private String puuid;
    private LittleLegend littleLegend;
    private Composition composition;
    private int goldLeft;
    private int lastRound;
    private int level;
    private int placement;
    private int playersEliminated;
    private float timeEliminated;
    private int totalDamageToPlayers;
    private String summonerName;
}
