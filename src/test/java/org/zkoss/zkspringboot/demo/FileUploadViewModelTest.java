package org.zkoss.zkspringboot.demo;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.openqa.selenium.By;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileUploadViewModelTest {

    @LocalServerPort
    int localServerPort;

    @MockBean
    FileUploadService fileUploadService;

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    void selenium_upload_bytData() throws Exception {
        ArgumentCaptor<byte[]> payloadCaptor = ArgumentCaptor.forClass(byte[].class);
        WebDriver driver = getChromeDriver();

        driver.get(String.format("http://localhost:%d/", localServerPort));

        File file = resourceLoader.getResource("classpath:test.xlsx").getFile();
        WebElement uploadInput = driver.findElement(By.cssSelector("input[type=file]"));

        uploadInput.sendKeys(file.getAbsolutePath());

        verify(fileUploadService, timeout(1000)).uploadBytes(eq("test.xlsx"), payloadCaptor.capture());
        assertEquals(file.length(), payloadCaptor.getValue().length);
    }

    @Test
    void selenium_upload_string() throws Exception {
        File file = resourceLoader.getResource("classpath:test-upload-file.txt").getFile();
        ArgumentCaptor<String> payloadCaptor = ArgumentCaptor.forClass(String.class);
        WebDriver driver = getChromeDriver();

        driver.get(String.format("http://localhost:%d/", localServerPort));

        WebElement uploadInput = driver.findElement(By.cssSelector("input[type=file]"));

        uploadInput.sendKeys(file.getAbsolutePath());

        verify(fileUploadService, timeout(1000)).uploadString(eq("test-upload-file.txt"), payloadCaptor.capture());
        assertEquals(file.length(), payloadCaptor.getValue().length());
    }

    private static WebDriver getChromeDriver() {
        ChromeDriver driver = new ChromeDriver();
        driver.register((uri) -> true, UsernameAndPassword.of("user", "password"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        return driver;
    }

}
