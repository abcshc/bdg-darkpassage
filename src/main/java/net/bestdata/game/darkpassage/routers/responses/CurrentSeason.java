package net.bestdata.game.darkpassage.routers.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bestdata.game.darkpassage.model.summoner.SeasonStatistic;

@AllArgsConstructor
@Getter
public class CurrentSeason {
    private String tier;
    private int leaguePoints;
    private int wins;
    private int topFour;
    private int plays;
    private String averagePlacement;

    public static CurrentSeason newBySeasonStatistic(SeasonStatistic seasonStatistic) {
        if (seasonStatistic == null) return null;
        return new CurrentSeason(seasonStatistic.getTier(), seasonStatistic.getLeaguePoints(),
                seasonStatistic.getWins(), seasonStatistic.getTopFour(), seasonStatistic.getPlays(),
                seasonStatistic.getAveragePlacement());
    }
}
