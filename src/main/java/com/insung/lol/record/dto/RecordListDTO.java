package com.insung.lol.record.dto;

import com.insung.lol.record.domain.Game;
import com.insung.lol.record.domain.Info;
import com.insung.lol.record.domain.Participant;
import lombok.Data;

@Data
public class RecordListDTO {
    private Game game;
    private Info info;
    private Participant participant;
}
