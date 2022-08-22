package com.insung.lol.record.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InfoDTO {
    private String championLevel;
    private String championImg;
    private List<String> championSpell = new ArrayList<>();
    private List<String> championRune = new ArrayList<>();
    private List<String> championKDA = new ArrayList<>();

    public void setChampionSpell(String spell) {
        championSpell.add(spell);
    }

    public void setChampionRune(String rune) {
        championRune.add(rune);
    }

    public void setChampionKDA(String kda) {
        championKDA.add(kda);
    }
}
