package com.insung.lol.record.controller;


import com.insung.lol.record.dto.RecordDataDTO;
import com.insung.lol.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/record")
public class RecordController {
    private final RecordService recordService;

    @GetMapping("/search/{id}")
    @ResponseBody
    public RecordDataDTO getRecord(@PathVariable String id) {
        RecordDataDTO result = new RecordDataDTO();
        try {
            result = recordService.getRecord(id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
