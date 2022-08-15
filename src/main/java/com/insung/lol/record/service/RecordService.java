package com.insung.lol.record.service;

import com.insung.lol.record.domain.Game;
import com.insung.lol.record.domain.Info;
import com.insung.lol.record.domain.Participant;
import com.insung.lol.record.dto.RecordListDTO;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

            // 검색 후 버튼 클릭
            driver.findElement(By.cssSelector("#searchHome")).sendKeys(id);
            List<WebElement> elements = driver.findElements(By.cssSelector(".e11dnomr0 button"));
            WebElement searchBtn = (WebElement) elements.stream().filter(v -> v.getText().equals(".GG")).toArray()[0];
            searchBtn.click();

            // 검색 결과 뜰 때 까지 잠시 대기
            Thread.sleep(800);

            // 전적 헤더
            List<WebElement> recordHeader = driver.findElements(By.cssSelector(".ehasqiv3"));
            // 전적 리스트
            List<WebElement> recordList = driver.findElements(By.cssSelector(".css-1qq23jn.e1iiyghw3"));

            // 전적 헤더 파싱
            recordHeaderParsing(recordHeader);
            // 전적 리스트 파싱
            List<RecordListDTO> result = recordListParsing(recordList);
            log.debug("테스트 {}", result);
            return recordList;
        } finally {
            driver.quit();
        }
    }

    private void recordHeaderParsing(List<WebElement> recordHeader) {


    }

    private List<RecordListDTO> recordListParsing(List<WebElement> recordList) {
        List<RecordListDTO> result = new ArrayList<>();
        for (WebElement webElement : recordList) {
            RecordListDTO recordListDTO = new RecordListDTO();
            Game game = new Game();
            Info info = new Info();
            Participant participant = new Participant();
            game.setRecordData(webElement);
            webElement.findElements(By.cssSelector(".items li img")).stream().forEach(v ->
                    game.setItems(v.getAttribute("src")));

            info.setChampionLevel(webElement.findElement(By.cssSelector(".champion-level")).getText());
            info.setChampionImg(webElement.findElement(By.cssSelector(".icon a img")).getAttribute("src"));
            webElement.findElements(By.cssSelector(".spell img")).stream().forEach(v ->
                    info.setChampionSpell(v.getAttribute("src")));

            webElement.findElements(By.cssSelector(".rune img")).stream().forEach(v ->
                    info.setChampionRune(v.getAttribute("src")));

            webElement.findElements(By.cssSelector(".k-d-a span")).stream().forEach(v ->
                    info.setChampionKDA(v.getText()));

            webElement.findElements(By.cssSelector(".participants")).stream().forEach(v ->
                    v.findElements(By.cssSelector(".name")).stream().forEach(o ->
                           participant.setPlayerName(o.getText())));

            webElement.findElements(By.cssSelector(".participants")).stream().forEach(v ->
                    v.findElements(By.cssSelector(".icon img")).stream().forEach(o ->
                            participant.setPlayerImg(o.getAttribute("src"))));

            recordListDTO.setGame(game);
            recordListDTO.setInfo(info);
            recordListDTO.setParticipant(participant);
            result.add(recordListDTO);
        }
        return result;
    }

}
