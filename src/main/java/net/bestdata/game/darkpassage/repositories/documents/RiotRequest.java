package net.bestdata.game.darkpassage.repositories.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bestdata.game.darkpassage.model.enums.Region;
import net.bestdata.game.darkpassage.model.enums.RequestType;

@AllArgsConstructor
@Getter
public class RiotRequest {
    private RequestType requestType;
    private String keyword;
    private Region region;

    public static RiotRequest newSummonerRequest(String summonerName, Region region) {
        return new RiotRequest(RequestType.SUMMONER, summonerName, region);
    }

    public static RiotRequest newSummonerDetailRequest(String summonerName, Region region) {
        return new RiotRequest(RequestType.SUMMONER_DETAIL, summonerName, region);
    }
}
