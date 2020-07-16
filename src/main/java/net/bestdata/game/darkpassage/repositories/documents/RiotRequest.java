package net.bestdata.game.darkpassage.repositories.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bestdata.game.darkpassage.model.enums.Region;
import net.bestdata.game.darkpassage.model.enums.RequestType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "requests")
@AllArgsConstructor
@Getter
public class RiotRequest {
    private RequestType requestType;
    private String keyword;
    private Region region;
    @CreatedDate
    private LocalDateTime createAt;

    public static RiotRequest newSummonerRequest(String summonerName, Region region) {
        return new RiotRequest(RequestType.SUMMONER, summonerName, region, null);
    }

    public static RiotRequest newSummonerDetailRequest(String summonerName, Region region) {
        return new RiotRequest(RequestType.SUMMONER_DETAIL, summonerName, region, null);
    }
}
