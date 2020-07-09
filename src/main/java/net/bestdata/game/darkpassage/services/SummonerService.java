package net.bestdata.game.darkpassage.services;

import net.bestdata.game.darkpassage.model.enums.Region;
import net.bestdata.game.darkpassage.repositories.SummonerRepository;
import net.bestdata.game.darkpassage.repositories.documents.RiotRequest;
import net.bestdata.game.darkpassage.repositories.documents.Summoner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class SummonerService {
    private final SummonerRepository summonerRepository;
    private final RabbitTemplate rabbitTemplate;

    public SummonerService(SummonerRepository summonerRepository, RabbitTemplate rabbitTemplate) {
        this.summonerRepository = summonerRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Mono<Summoner> find(String summonerName, Region region) {
        return summonerRepository.findBySummonerNameAndRegion(summonerName, region)
                .switchIfEmpty(
                        summonerRepository.findBySummonerNameAndRegion(summonerName, region)
                                .doFirst(() -> rabbitTemplate.convertAndSend(RiotRequest.newSummonerRequest(summonerName, region)))
                                .delaySubscription(Duration.ofSeconds(3))
                );
    }

    public Mono<RiotRequest> refresh(String summonerName, Region region) {
        RiotRequest riotRequest = RiotRequest.newSummonerRequest(summonerName, region);
        rabbitTemplate.convertAndSend(riotRequest);
        return Mono.just(riotRequest);
    }
}
