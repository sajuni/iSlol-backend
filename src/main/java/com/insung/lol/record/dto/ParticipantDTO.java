package com.insung.lol.record.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParticipantDTO {

    private List<String> playerName = new ArrayList<>();
    private List<String> playerImg = new ArrayList<>();

    public void setPlayerName(String name) {
        playerName.add(name);
    }

    public void setPlayerImg(String img) {
        playerImg.add(img);
    }

}
