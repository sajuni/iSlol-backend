package com.insung.lol.record.controller;

import com.insung.lol.record.service.RecordService;
import org.junit.jupiter.api.Test;

class RecordControllerTest {

    private RecordService recordService = new RecordService();

    @Test
    public void recordTest() {
        try {
            recordService.getRecord("인천싸이코");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}