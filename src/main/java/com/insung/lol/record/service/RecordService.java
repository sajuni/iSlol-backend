package com.insung.lol.record.service;

import com.insung.lol.record.dto.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class RecordService {

    private ChromeDriver driver;

    public RecordDataDTO getRecord(String id) throws InterruptedException {
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
            Thread.sleep(1000);

            RecordDataDTO recordDataDTO = new RecordDataDTO();

            // 사이드 바 파싱
            RecordRankDTO recordRank = recordSideBarParsing();
            // 전적 헤더 파싱
            RecordHeaderDTO recordHeader = recordHeaderParsing();
            // 전적 리스트 파싱
            List<RecordListDTO> recordList = recordListParsing();

            recordDataDTO.setRecordRankDTO(recordRank);
            recordDataDTO.setRecordHeaderDTO(recordHeader);
            recordDataDTO.setRecordListDTO(recordList);

            return recordDataDTO;
        } finally {
            driver.quit();
        }
    }

    private RecordRankDTO recordSideBarParsing() {
        WebElement recordSideBar = driver.findElement(By.cssSelector(".e8nboil0 div"));
        RecordRankDTO recordRankDTO = new RecordRankDTO();

        //솔로랭크 영역
        WebElement soloRankData = recordSideBar.findElement(By.cssSelector(".css-1v663t"));
        recordRankDTO.setSoloRankHeader(soloRankData.findElement(By.cssSelector(".header")).getText());
        recordRankDTO.setSoloRankImg(soloRankData.findElement(By.cssSelector(".content img")).getAttribute("src"));
        recordRankDTO.setSoloRankTier(soloRankData.findElement(By.cssSelector(".tier")).getText());
        recordRankDTO.setSoloRankLp(soloRankData.findElement(By.cssSelector(".lp")).getText());
        recordRankDTO.setSoloRankWinLose(soloRankData.findElement(By.cssSelector(".win-lose")).getText());
        recordRankDTO.setSoloRankRatio(soloRankData.findElement(By.cssSelector(".ratio")).getText());

        //자유랭크 영역
        WebElement teamRankData = recordSideBar.findElement(By.cssSelector(".css-1474l3c"));
        recordRankDTO.setTeamRankHeader(teamRankData.findElement(By.cssSelector(".header")).getText());
        recordRankDTO.setTeamRankImg(teamRankData.findElement(By.cssSelector(".content img")).getAttribute("src"));
        recordRankDTO.setTeamRankTire(teamRankData.findElement(By.cssSelector(".tier")).getText());
        recordRankDTO.setTeamRankLp(teamRankData.findElement(By.cssSelector(".lp")).getText());
        recordRankDTO.setTeamRankWinLose(teamRankData.findElement(By.cssSelector(".win-lose")).getText());
        recordRankDTO.setTeamRankRatio(teamRankData.findElement(By.cssSelector(".ratio")).getText());

        //시즌전적 영역
        List<WebElement> championRecordElements = recordSideBar.findElements(By.cssSelector(".css-e9xk5o .champion-box"));
        List<ChampionBoxDTO> championBoxDTO = new ArrayList<>();
        championRecordElements.stream().forEach(v -> {
            ChampionBoxDTO dto = new ChampionBoxDTO();
            dto.setChampImg(v.findElement(By.cssSelector(".face img")).getAttribute("src"));
            dto.setChampName(v.findElement(By.cssSelector(".info .name")).getText());
            dto.setCs(v.findElement(By.cssSelector(".info .cs")).getText());
            dto.setGrade(v.findElement(By.cssSelector(".kda .e1g7spwk1")).getText());
            dto.setDetail(v.findElement(By.cssSelector(".kda .detail")).getText());
            dto.setWinRate(v.findElement(By.cssSelector(".played .e1g7spwk0")).getText());
            dto.setCount(v.findElement(By.cssSelector(".played .count")).getText());
            championBoxDTO.add(dto);
        });

        recordRankDTO.setChampionBoxDTOS(championBoxDTO);

        return recordRankDTO;
    }

    private RecordHeaderDTO recordHeaderParsing() {
        WebElement recordHeader = driver.findElement(By.cssSelector(".ehasqiv3"));
        RecordHeaderDTO recordHeaderDTO = new RecordHeaderDTO();
        recordHeaderDTO.setWinLose(recordHeader.findElement(By.cssSelector(".win-lose")).getText());
        recordHeaderDTO.setWinLosePercent(recordHeader.findElement(By.cssSelector(".kda .text")).getText());
        recordHeaderDTO.setKda(recordHeader.findElement(By.cssSelector(".k-d-a")).getText());
        recordHeaderDTO.setRatio(recordHeader.findElement(By.cssSelector(".ratio")).getText());
        recordHeaderDTO.setKillParticipantion(recordHeader.findElement(By.cssSelector(".kill-participantion")).getText());
        recordHeaderDTO.setTitle(recordHeader.findElement(By.cssSelector(".title")).getText());
        recordHeader.findElements(By.cssSelector(".champions li")).stream()
                .forEach(v -> {
                    WinLoseLowDTO winLoseLowDTO = new WinLoseLowDTO();
                    winLoseLowDTO.setImg(v.findElement(By.cssSelector("img")).getAttribute("src"));
                    winLoseLowDTO.setWinLose(v.findElement(By.cssSelector(".win-lose")).getText());
                    winLoseLowDTO.setGrade(v.findElement(By.cssSelector(".ehasqiv1")).getText());
                    recordHeaderDTO.setWinLoseRow(winLoseLowDTO);
                });
        return recordHeaderDTO;
    }

    private List<RecordListDTO> recordListParsing() {
        List<WebElement> recordList = driver.findElements(By.cssSelector(".e1iiyghw3"));
        List<RecordListDTO> result = new ArrayList<>();
        for (WebElement webElement : recordList) {
            RecordListDTO recordListDTO = new RecordListDTO();
            GameDTO game = new GameDTO();
            InfoDTO info = new InfoDTO();
            ParticipantDTO participant = new ParticipantDTO();
            setGameData(webElement, game);
            setInfoData(webElement, info);
            setParticipantData(webElement, participant);

            recordListDTO.setGame(game);
            recordListDTO.setInfo(info);
            recordListDTO.setParticipant(participant);
            result.add(recordListDTO);
        }
        return result;
    }

    private void setParticipantData(WebElement webElement, ParticipantDTO participant) {
        webElement.findElements(By.cssSelector(".participants .name")).stream().forEach(v ->
                participant.setPlayerName(v.getText()));

        webElement.findElements(By.cssSelector(".participants .icon img")).stream().forEach(v ->
                participant.setPlayerImg(v.getAttribute("src")));
    }

    private void setInfoData(WebElement webElement, InfoDTO info) {
        info.setChampionLevel(webElement.findElement(By.cssSelector(".champion-level")).getText());
        info.setChampionImg(webElement.findElement(By.cssSelector(".icon a img")).getAttribute("src"));
        webElement.findElements(By.cssSelector(".spell img")).stream().forEach(v ->
                info.setChampionSpell(v.getAttribute("src")));

        webElement.findElements(By.cssSelector(".rune img")).stream().forEach(v ->
                info.setChampionRune(v.getAttribute("src")));

        webElement.findElements(By.cssSelector(".k-d-a span")).stream().forEach(v ->
                info.setChampionKDA(v.getText()));
    }

    private void setGameData(WebElement webElement, GameDTO game) {
        game.setRecordData(webElement);
        webElement.findElements(By.cssSelector(".items li img")).stream().forEach(v ->
            game.addItem(v.getAttribute("src")));
        game.addItem(webElement.findElement(By.cssSelector(".items .ward img")).getAttribute("src"));

    }

}
