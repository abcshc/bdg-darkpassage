package net.bestdata.game.darkpassage.routers;

import net.bestdata.game.darkpassage.model.enums.Region;
import net.bestdata.game.darkpassage.repositories.documents.Summoner;
import net.bestdata.game.darkpassage.routers.responses.CurrentSeason;
import net.bestdata.game.darkpassage.routers.responses.SummonerResponse;
import net.bestdata.game.darkpassage.services.SummonerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class SummonerRouter {
    private final SummonerService summonerService;

    public SummonerRouter(SummonerService summonerService) {
        this.summonerService = summonerService;
    }

    @Bean
    public RouterFunction<ServerResponse> routeSummoner() {
        return RouterFunctions
                .route(GET("/summoner/{region}/{summonerName}"),
                        request -> {
                            String summonerName = request.pathVariable("summonerName");
                            String region = request.pathVariable("region").toUpperCase();
                            return summonerService.find(summonerName, Region.valueOf(region))
                                    .flatMap(summoner -> {
                                        // TODO: 시즌 처리
                                        SummonerResponse response = convertSummonerResponse(summoner, "4");
                                        return ServerResponse.ok().contentType(APPLICATION_JSON).bodyValue(response);
                                    })
                                    .switchIfEmpty(ServerResponse.notFound().build());
                        })
                .andRoute(POST("/summoner/{region}/{summonerName}"),
                        request -> {
                            String summonerName = request.pathVariable("summonerName");
                            String region = request.pathVariable("region").toUpperCase();
                            return summonerService.refresh(summonerName, Region.valueOf(region))
                                    .flatMap(it -> ServerResponse.ok().build())
                                    .switchIfEmpty(ServerResponse.notFound().build());
                        });
    }


    private SummonerResponse convertSummonerResponse(Summoner summoner, String currentSeason) {
        return new SummonerResponse(summoner.getSummonerName(), summoner.getProfileIconId(), summoner.getSummonerLevel(),
                CurrentSeason.newBySeasonStatistic(summoner.findSeasonStatistic(currentSeason)));
    }
}
