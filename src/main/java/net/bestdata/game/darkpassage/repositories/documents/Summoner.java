package net.bestdata.game.darkpassage.repositories.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bestdata.game.darkpassage.model.enums.Region;
import net.bestdata.game.darkpassage.model.summoner.MyMatch;
import net.bestdata.game.darkpassage.model.summoner.SeasonStatistic;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Document(collection = "summoners")
@CompoundIndexes({
        @CompoundIndex(def = "{'region' : 1, 'summonerName': 1}", name = "summoner_name_index", unique = true),
        @CompoundIndex(def = "{'puuid' : 1}", name = "puuid_index", unique = true)
})
@AllArgsConstructor
@Getter
public class Summoner {
    @Id
    private String id;
    private String summonerName;
    private Region region;

    private String accountId;
    private String riotId;
    private String puuid;
    private List<MyMatch> myMatches;
    private Map<String, SeasonStatistic> seasonStatisticMap;

    private int profileIconId;
    private long summonerLevel;
    private String lastMatch;

    public SeasonStatistic findSeasonStatistic(String season) {
        return seasonStatisticMap.get(season);
    }

    public static Summoner newEmpty() {
        return new Summoner(null, "", Region.NONE, "", "", "", Collections.emptyList(), Collections.emptyMap(), 0, 0, null);
    }

    public boolean isCheckedMatch(String matchId) {
        if (lastMatch == null) {
            return false;
        }
        if (lastMatch.equals(matchId)) {
            return true;
        }
        return false;
    }

    public void addMyMatch(MyMatch myMatch) {
        if(myMatches.equals(Collections.emptyList())) {
            myMatches = new ArrayList<>();
        }
        myMatches.add(myMatch);
    }
}
