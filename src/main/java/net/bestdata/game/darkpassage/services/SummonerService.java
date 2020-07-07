package net.bestdata.game.darkpassage.services;

import net.bestdata.game.darkpassage.model.enums.Region;
import net.bestdata.game.darkpassage.repositories.SummonerRepository;
import net.bestdata.game.darkpassage.repositories.documents.Summoner;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SummonerService {
    private final SummonerRepository summonerRepository;

    public SummonerService(SummonerRepository summonerRepository) {
        this.summonerRepository = summonerRepository;
    }

    public Optional<Summoner> find(String summonerName, Region region) {
        // TODO
        return Optional.empty();
    }

    public Optional<Summoner> refresh(String summonerName, Region region) {
        // TODO
        return Optional.empty();
    }
}
