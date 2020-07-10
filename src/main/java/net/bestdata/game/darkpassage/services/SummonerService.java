package net.bestdata.game.darkpassage.services;

import net.bestdata.game.darkpassage.messages.RiotRequest;
import net.bestdata.game.darkpassage.model.enums.Region;
import net.bestdata.game.darkpassage.repositories.SummonerRepository;
import net.bestdata.game.darkpassage.repositories.documents.Summoner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class SummonerService {
    private final SummonerRepository summonerRepository;
    private final RabbitTemplate rabbitTemplate;

    @Value("${queue.request.topicExchangeName}")
    private String topicExchangeName;

    @Value("${queue.request.routingKey}")
    private String routingKey;

    public SummonerService(SummonerRepository summonerRepository, RabbitTemplate rabbitTemplate) {
        this.summonerRepository = summonerRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Mono<Summoner> find(String summonerName, Region region) {
        return summonerRepository.findBySummonerNameAndRegion(summonerName, region)
                .switchIfEmpty(
                        summonerRepository.findBySummonerNameAndRegion(summonerName, region)
                                .doFirst(() -> {
                                    RiotRequest riotRequest = RiotRequest.newSummonerRequest(summonerName, region);
                                    rabbitTemplate.convertAndSend(topicExchangeName, routingKey, riotRequest, message -> {
                                        message.getMessageProperties().setPriority(riotRequest.priority());
                                        return message;
                                    });
                                })
                                .delaySubscription(Duration.ofSeconds(3))
                );
    }

    public Mono<RiotRequest> refresh(String summonerName, Region region) {
        RiotRequest riotRequest = RiotRequest.newSummonerRequest(summonerName, region);
        rabbitTemplate.convertAndSend(riotRequest);
        return Mono.just(riotRequest);
    }
}
