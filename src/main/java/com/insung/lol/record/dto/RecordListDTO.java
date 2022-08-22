package com.insung.lol.record.dto;

import lombok.Data;

@Data
public class RecordListDTO {
    private GameDTO game;
    private InfoDTO info;
    private ParticipantDTO participant;
}
