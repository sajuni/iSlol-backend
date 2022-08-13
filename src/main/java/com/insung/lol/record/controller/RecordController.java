package com.insung.lol.record.controller;


import com.insung.lol.record.dto.RecordDTO;
import com.insung.lol.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/record")
public class RecordController {
    private final RecordService recordService;

    @GetMapping("/search/{id}")
    @ResponseBody
    public RecordDTO getRecord(@PathVariable String id) {
        List<WebElement> record;
        try {
            record = recordService.getRecord(id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        RecordDTO recordDTO = new RecordDTO();
        recordDTO.setWebElement(record);

        return recordDTO;
    }
}
