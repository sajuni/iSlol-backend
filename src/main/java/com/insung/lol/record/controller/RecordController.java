package com.insung.lol.record.controller;


import com.insung.lol.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/record")
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/search/{id}")
    public Object getRecord(@PathVariable String id) {
        recordService.getRecord(id);
        return null;
    }
}
