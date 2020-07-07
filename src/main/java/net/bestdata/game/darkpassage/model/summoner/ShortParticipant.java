package net.bestdata.game.darkpassage.model.summoner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bestdata.game.darkpassage.model.match.LittleLegend;

@AllArgsConstructor
@Getter
public class ShortParticipant {
    private String summonerName;
    private LittleLegend littleLegend;
}
