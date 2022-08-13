package com.insung.lol.record.service;

import com.insung.lol.record.dto.RecordDTO;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
public class RecordService {

    private ChromeDriver driver;

    public List<WebElement> getRecord(String id) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");       //팝업안띄움
        options.addArguments("headless");                       //브라우저 안띄움
        options.addArguments("--disable-gpu");			//gpu 비활성화
        options.addArguments("--blink-settings=imagesEnabled=false"); //이미지 다운 안받음
        driver = new ChromeDriver(options);
        try {
            driver.get("https://www.op.gg/");

            driver.findElement(By.cssSelector("#searchHome")).sendKeys(id);
            List<WebElement> elements = driver.findElements(By.cssSelector(".e11dnomr0 button"));
            WebElement searchBtn = (WebElement) elements.stream().filter(v -> v.getText().equals(".GG")).toArray()[0];
            searchBtn.click();
            Thread.sleep(500);
            List<WebElement> recordList = driver.findElements(By.cssSelector(".css-164r41r.e1r5v5160"));
            for (WebElement webElement : recordList) {
                System.out.println(webElement.getText()) ;
            }
            return recordList;
        } finally {
            driver.quit();
        }
    }
}
