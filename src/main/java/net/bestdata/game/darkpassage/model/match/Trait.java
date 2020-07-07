package net.bestdata.game.darkpassage.model.match;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Trait {
    private String name;
    private int numUnits;
    private int tierCurrent;
    private int tierTotal;
}
