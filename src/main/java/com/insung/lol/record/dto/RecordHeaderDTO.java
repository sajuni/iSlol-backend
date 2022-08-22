package com.insung.lol.record.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class RecordHeaderDTO {
    private String winLose;
    private String winLosePercent;
    private String kda;
    private String ratio;
    private String killParticipantion;
    private String title;
    private List<WinLoseLowDTO> winLoseRow = new ArrayList<>();

    public void setWinLoseRow(WinLoseLowDTO winLoseRow) {
        this.winLoseRow.add(winLoseRow);
    }
}
