package com.insung.lol.record.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecordDataDTO {
    private RecordRankDTO recordRankDTO;
    private RecordHeaderDTO recordHeaderDTO;
    private List<RecordListDTO> recordListDTO = new ArrayList<>();
}
