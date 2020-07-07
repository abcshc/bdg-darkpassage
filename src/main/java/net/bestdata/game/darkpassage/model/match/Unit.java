package net.bestdata.game.darkpassage.model.match;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Unit {
    private String characterId;
    private List<Integer> items;
    private String name;
    private int rarity;
    private int tier;
}