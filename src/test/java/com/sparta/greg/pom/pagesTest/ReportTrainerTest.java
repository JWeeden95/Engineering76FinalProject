package com.sparta.greg.pom.pagesTest;

import com.sparta.greg.pom.pages.Login;
import com.sparta.greg.pom.pages.ReportTrainer;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReportTrainerTest {

    private static final Properties     properties = new Properties();

    private static       Login          login;
    private static       ReportTrainer  reportTrainer;
    private static       WebDriver      webDriver;

    private static       String         trainerUsername;
    private static       String         trainerPassword;

    @BeforeEach
    void setup() {
        webDriver = new ChromeDriver();
        login     = new Login(webDriver);

        try {
            properties.load(new FileReader("src/test/resources/login.properties"));
            trainerUsername = properties.getProperty("traineeUsername");
            trainerPassword = properties.getProperty("traineePassword");
        } catch (IOException e) {
            e.printStackTrace();
        }

        login.logInAsTrainer(trainerUsername, trainerPassword);
        reportTrainer = new ReportTrainer(webDriver);
    }

    @AfterEach
    void closeWindow() {
        webDriver.close();
    }

    @Test
    @DisplayName("Does http://localhost:8080/trainer/report/41 load to WebDriver on class instantiation?")
    void isReportTrainerPageLoaded() {
        Assertions.assertEquals("http://localhost:8080/trainer/report/41", reportTrainer.getURL());
    }

    @AfterAll
    static void tearDown() {
        webDriver.quit();
    }
}
