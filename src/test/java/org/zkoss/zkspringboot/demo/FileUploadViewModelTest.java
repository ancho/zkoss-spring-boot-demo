package org.zkoss.zkspringboot.demo;

import java.io.File;
import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class FileUploadViewModelTest {

    @MockBean
    FileUploadService fileUploadService;

    @Test
    void selenium_upload_bytData() throws Exception {

        ArgumentCaptor<byte[]> payloadCaptor = ArgumentCaptor.forClass(byte[].class);

        ChromeOptions options = new ChromeOptions();
        File chromeBinary = new File("C:\\Lokal\\chrome-win64\\chrome.exe");
        options.setBinary(chromeBinary);
        ChromeDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));


        driver.get("http://localhost:8080/");


        File file = new File(this.getClass().getResource("/test.xlsx").toURI());


        WebElement uploadInput = driver.findElement(By.cssSelector("input[type=file]"));

        uploadInput.sendKeys(file.getAbsolutePath());

        Thread.sleep(2000);

        verify(fileUploadService).uploadBytes(eq("test.xlsx"), payloadCaptor.capture());
        assertEquals(payloadCaptor.getValue().length, file.length());
    }

    @Test
    void selenium_upload_string() throws Exception {

        ArgumentCaptor<String> payloadCaptor = ArgumentCaptor.forClass(String.class);

        ChromeOptions options = new ChromeOptions();
        File chromeBinary = new File("C:\\Lokal\\chrome-win64\\chrome.exe");
        options.setBinary(chromeBinary);
        ChromeDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));


        driver.get("http://localhost:8080/");


        File file = new File(this.getClass().getResource("/test-upload-file.txt").toURI());


        WebElement uploadInput = driver.findElement(By.cssSelector("input[type=file]"));

        uploadInput.sendKeys(file.getAbsolutePath());

        Thread.sleep(2000);

        verify(fileUploadService).uploadString(eq("test-upload-file.txt"), payloadCaptor.capture());
        assertEquals(payloadCaptor.getValue().length(), file.length());
    }
}