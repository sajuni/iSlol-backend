package com.insung.lol.record.dto;

import lombok.Data;
import org.openqa.selenium.WebElement;

import java.util.List;

@Data
public class RecordDTO {
    private List<WebElement> webElement;
}
