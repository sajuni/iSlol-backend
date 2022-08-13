package com.insung.lol.record.controller;

import com.insung.lol.record.dto.RecordDTO;
import com.insung.lol.record.service.RecordService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordControllerTest {

    private RecordService recordService = new RecordService();

    @Test
    public void recordTest() {
        try {
            recordService.getRecord("애니비효");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}