package com.insung.lol.record.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class Game {
    private String type;
    private String timeStamp;
    private String result;
    private String length;
    private List<String> items = new ArrayList<>();

    public void setRecordData(WebElement data) {
        type = data.findElement(By.cssSelector(".type")).getText();
        timeStamp = data.findElement(By.cssSelector(".time-stamp")).getText();
        result = data.findElement(By.cssSelector(".result")).getText();
        length = data.findElement(By.cssSelector(".length")).getText();
    }

    public void setItems(String item) {
        items.add(item);
    }
}
