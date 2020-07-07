package net.bestdata.game.darkpassage.model.match;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Composition {
    private List<Trait> traits;
    private List<Unit> units;
}
