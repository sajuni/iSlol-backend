package com.insung.lol.record.controller;


import com.insung.lol.record.dto.RecordListDTO;
import com.insung.lol.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/record")
public class RecordController {
    private final RecordService recordService;

    @GetMapping("/search/{id}")
    @ResponseBody
    public RecordListDTO getRecord(@PathVariable String id) {
        List<WebElement> record;
        try {
            record = recordService.getRecord(id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        RecordListDTO recordDTO = new RecordListDTO();

        return recordDTO;
    }
}
