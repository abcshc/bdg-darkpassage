package net.bestdata.game.darkpassage.repositories;

import net.bestdata.game.darkpassage.model.enums.Region;
import net.bestdata.game.darkpassage.repositories.documents.Summoner;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SummonerRepository extends ReactiveCrudRepository<Summoner, String> {
    Mono<Summoner> findBySummonerNameAndRegion(String summonerName, Region region);
}
