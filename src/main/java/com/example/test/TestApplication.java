package com.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class TestApplication  {

    private static Random random = new Random();

    private static void waitFor(long millis) throws InterruptedException{
        System.out.println("Sleep for " + millis + " ms");
        Thread.sleep(millis);
    }

    private static void randomWait() throws InterruptedException{
        waitFor(random.nextInt(3*60) * 1000);
    }

    public static void vote() throws InterruptedException{
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://dev.odb.sh.cn/CSJGKYQSEHHZJHD/Home/WorksVote?pAction=0");
        _vote(driver);
        driver.close();
    }

    public static void _vote(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/a")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/ul/li[2]/a")).click();
        waitFor(1000);
        List<WebElement> buttons = driver.findElements(By.className("addtocart"));
        for (WebElement button: buttons){
            if (button.isDisplayed())
                button.click();
        }
        waitFor(1000);
        driver.findElement(By.className("ky_gouwuche")).click();
        waitFor(1000);
        driver.findElement(By.className("ky_toupiaoconfirm_main_btn")).click();
        waitFor(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\zzbslayer\\github\\test\\chromedriver_win32\\chromedriver.exe");

        for (int i = 0; i < 20000; i++){
            vote();
            randomWait();
        }
    }

}
