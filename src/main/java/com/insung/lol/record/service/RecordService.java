package com.insung.lol.record.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RecordService {

    private ChromeDriver driver;

    public void getRecord(String id){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        try {
            log.debug("테스트중: {}", id);
            driver.get("https://www.op.gg/");
            driver.findElement(By.xpath("//*[@id=\"searchHome\"]")).sendKeys(id);
            //WebElement e11dnomr0 = driver.findElementByClassName("e11dnomr0");
            //System.out.println("테스트 성공1 = " + e11dnomr0);
            System.out.println("테스트 성공 = " + driver.getCurrentUrl());
        } finally {
            driver.quit();
        }
    }
}
