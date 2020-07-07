package net.bestdata.game.darkpassage.model.summoner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bestdata.game.darkpassage.model.match.Trait;
import net.bestdata.game.darkpassage.model.match.Unit;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class MyMatch {
    private String dataVersion;
    private LocalDateTime gameDateTime;
    private float gameLength;
    private String gameVariation;

    private List<Unit> units;
    private List<Trait> traits;
    private List<ShortParticipant> shortParticipants;
    private int placement;
}
