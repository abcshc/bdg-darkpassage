package net.bestdata.game.darkpassage.controllers;

import net.bestdata.game.darkpassage.controllers.responses.CurrentSeason;
import net.bestdata.game.darkpassage.controllers.responses.SummonerResponse;
import net.bestdata.game.darkpassage.exceptions.HttpNotFoundException;
import net.bestdata.game.darkpassage.model.enums.Region;
import net.bestdata.game.darkpassage.repositories.documents.Summoner;
import net.bestdata.game.darkpassage.services.SummonerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SummonerController {
    @Value("${riot.tft.current.version}")
    private String currentSeason;

    private final SummonerService summonerService;

    public SummonerController(SummonerService summonerService) {
        this.summonerService = summonerService;
    }

    @GetMapping("/summoner/{region}/{summonerName}")
    public SummonerResponse getSummoner(@PathVariable(value = "region") String region,
                                        @PathVariable(value = "summonerName") String summonerName) {
        Summoner summoner = summonerService.find(summonerName, Region.valueOf(region)).orElseThrow(HttpNotFoundException::new);
        return convertSummonerResponse(summoner, currentSeason);
    }

    @PostMapping("/summoner/{region}/{summonerName}/refresh")
    public SummonerResponse refreshSummoner(@PathVariable(value = "region") String region,
                                            @PathVariable(value = "summonerName") String summonerName) {
        Summoner summoner = summonerService.refresh(summonerName, Region.valueOf(region)).orElseThrow(HttpNotFoundException::new);
        return convertSummonerResponse(summoner, currentSeason);
    }

    private SummonerResponse convertSummonerResponse(Summoner summoner, String currentSeason) {
        return new SummonerResponse(summoner.getSummonerName(), summoner.getProfileIconId(), summoner.getSummonerLevel(),
                CurrentSeason.newBySeasonStatistic(summoner.findSeasonStatistic(currentSeason)));
    }
}
