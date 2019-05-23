package com.example.test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CnmoocTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "D:\\Tool\\Katalon_Studio_Windows_64-6.1.5\\configuration\\resources\\drivers\\chromedriver_win32\\chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  public void loginTest() throws InterruptedException{
    //输入框, 按钮
    // 登录
    driver.findElement(By.linkText("登录")).click();
    driver.findElement(By.id("loginName")).clear();
    driver.findElement(By.id("loginName")).sendKeys("604239669@qq.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("yourpassword");
    driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
    driver.findElement(By.id("userLogin")).click();
    waitFor(1000);
  }

  public void collectionTest() throws InterruptedException{
    // 模态
    // 初始教程跳过
    driver.findElement(By.xpath("/html/body/div[11]/div/div[5]/a[1]")).click();
    // 搜索课程
    driver.findElement(By.id("keyWord")).clear();
    driver.findElement(By.id("keyWord")).sendKeys("任锐");
    driver.findElement(By.id("keyWord")).sendKeys(Keys.ENTER);
    waitFor(1000);

    driver.findElement(By.xpath("//*[@id=\"myCourse\"]/div/div/ul/li/div/div[2]/div[2]/div/a[1]")).click();

    driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div[2]/span/i")).click();
    driver.findElement(By.xpath("//*[@id=\"inner-header\"]/div[1]/a[2]")).click();
    waitFor(1000);
    // 多选
    driver.findElement(By.xpath("//*[@id=\"org\"]/li[3]/div/a")).click();
    waitFor(1000);
    driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/ul/li[1]/div/div[2]/h3/a")).click();
    waitFor(1000);
    driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div[2]/span/i")).click();
    // 我的课程
    driver.findElement(By.xpath("//*[@id=\"inner-header\"]/div[2]/div[1]/a")).click();
    waitFor(1000);
    // 查看收藏
    // 需要当前登录账号测试之前，收藏夹为空
    driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div/div/ul/li[1]/ul/li[2]/a")).click();
    waitFor(1000);

    String title = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/ul/li[1]/div/div[2]/h3")).getText();
    assertEquals("互联网应用开发技术", title);
    title = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/ul/li[2]/div/div[2]/h3")).getText();
    assertEquals("2018 ICBC-IBM金融科技创新大赛相关技术培训课程", title);

    driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div/div/ul/li[1]/ul/li[2]/a")).click();
    waitFor(1000);
    // 删除收藏
    driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/ul/li[1]/div/i")).click();
    waitFor(1000);
    driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[3]/td/div/input[1]")).click();
    waitFor(1000);
    // 删除收藏
    driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/ul/li/div/i")).click();
    waitFor(1000);
    driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[3]/td/div/input[1]")).click();
    waitFor(1000);
  }

  public void personalInfoTest() throws InterruptedException{
    // 个人信息
    driver.findElement(By.xpath("//*[@id=\"inner-header\"]/div[2]/div[1]/div/div/span")).click();
    driver.findElement(By.xpath("//*[@id=\"inner-header\"]/div[2]/div[1]/div/ul/li[2]/a")).click();
    waitFor(1000);
    // 隐私 仅限自己
    driver.findElement(By.xpath("//*[@id=\"isPrivate\"]/span[1]/a")).click();
    // 学历本科
    driver.findElement(By.id("dk0-education")).click();
    driver.findElement(By.id("dk0-30")).click();
    // 保存
    driver.findElement(By.xpath("//*[@id=\"saveBtn\"]")).click();
    driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[3]/td/div/input")).click();
    // 检查刚刚修改的信息
    // 刷新
    driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div/ul/li[6]/a")).click();
    // 隐私 仅限自己
    String onlyMe = driver.findElement(By.xpath("//*[@id=\"isPrivate\"]/span[1]/a")).getAttribute("class");
    assertTrue(onlyMe.contains("selected"));
    String education = driver.findElement(By.xpath("//*[@id=\"dk0-combobox\"]")).getText();
    assertEquals("本科", education);
  }

  @Test
  public void testUntitledTestCase() throws Exception {
    driver.get("https://www.cnmooc.org/home/index.mooc");
    loginTest();
    collectionTest();
    personalInfoTest();
    driver.close();
  }

  private void waitFor(long millis) throws InterruptedException{
    Thread.sleep(millis);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
