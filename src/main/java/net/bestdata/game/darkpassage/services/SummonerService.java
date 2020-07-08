package net.bestdata.game.darkpassage.services;

import net.bestdata.game.darkpassage.model.enums.Region;
import net.bestdata.game.darkpassage.repositories.RiotRequestRepository;
import net.bestdata.game.darkpassage.repositories.SummonerRepository;
import net.bestdata.game.darkpassage.repositories.documents.RiotRequest;
import net.bestdata.game.darkpassage.repositories.documents.Summoner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class SummonerService {
    private final SummonerRepository summonerRepository;
    private final RiotRequestRepository riotRequestRepository;

    public SummonerService(SummonerRepository summonerRepository, RiotRequestRepository riotRequestRepository) {
        this.summonerRepository = summonerRepository;
        this.riotRequestRepository = riotRequestRepository;
    }

    public Mono<Summoner> find(String summonerName, Region region) {
        return summonerRepository.findBySummonerNameAndRegion(summonerName, region)
                .switchIfEmpty(riotRequestRepository.save(RiotRequest.newSummonerRequest(summonerName, region))
                        .then(summonerRepository.findBySummonerNameAndRegion(summonerName, region).delaySubscription(Duration.ofSeconds(3))));
    }

    public Mono<RiotRequest> refresh(String summonerName, Region region) {
        return riotRequestRepository.save(RiotRequest.newSummonerDetailRequest(summonerName, region));
    }
}
