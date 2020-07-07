package net.bestdata.game.darkpassage.repositories;

import net.bestdata.game.darkpassage.model.enums.Region;
import net.bestdata.game.darkpassage.repositories.documents.Summoner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SummonerRepository extends MongoRepository<Summoner, String> {
    Optional<Summoner> findBySummonerNameAndRegion(String summonerName, Region region);
    Optional<Summoner> findByPuuid(String puuid);
}
