package com.insung.lol.record.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecordRankDTO {
    private String soloRankHeader;
    private String soloRankImg;
    private String soloRankTier;
    private String soloRankLp;
    private String soloRankWinLose;
    private String soloRankRatio;
    private String teamRankHeader;
    private String teamRankImg;
    private String teamRankTire;
    private String teamRankLp;
    private String teamRankWinLose;
    private String teamRankRatio;
    private List<ChampionBoxDTO> championBoxDTOS = new ArrayList<>();
}
