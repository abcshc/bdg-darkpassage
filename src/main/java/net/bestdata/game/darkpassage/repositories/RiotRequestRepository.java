package net.bestdata.game.darkpassage.repositories;

import net.bestdata.game.darkpassage.repositories.documents.RiotRequest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiotRequestRepository extends ReactiveCrudRepository<RiotRequest, String> {
}
