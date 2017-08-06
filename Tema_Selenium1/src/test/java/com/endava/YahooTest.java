package com.endava;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class YahooTest {

private static WebDriver webDriver;

@BeforeClass
public static void before() {

    System.setProperty("webdriver.chrome.driver", "d:/chromedriver.exe");

    webDriver = new ChromeDriver();

    webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    webDriver.manage().window().maximize();
    }

@Before

public void goToPage(){

    webDriver.get("http://mailyahoo.com/");
    }

@AfterClass

 public static void closeBrowser() {
    webDriver.close();
    }

@Test
public void sendEmail() throws InterruptedException {

    WebElement usernameField = webDriver.findElement(By.xpath("//input[@id='login-username']"));
    usernameField.sendKeys("entest2017");

    WebElement buttonNext = webDriver.findElement(By.xpath("//input[@id='login-signin']"));
    buttonNext.click();

    WebElement passwordField = webDriver.findElement(By.xpath("//input[@id='login-passwd']"));
    passwordField.sendKeys("Chrome22");

    WebElement signInButton = webDriver.findElement(By.xpath("//button[@id='login-signin']"));
    signInButton.click();

    WebElement composeButton = webDriver.findElement(By.xpath("//li[@id='Compose']//span"));
    composeButton.click();

    WebElement toButton = webDriver.findElement(By.xpath("//input[@id='to-field']"));
    toButton.sendKeys("feldiorean_traian@yahoo.com");

    WebElement subjectField = webDriver.findElement(By.xpath("//input[@id='subject-field']"));
    subjectField.sendKeys("Test");

    WebElement textField = webDriver.findElement(By.xpath("//div[@id='rtetext']"));
    textField.sendKeys("Content test");

    WebElement sendButton = webDriver.findElement(By.xpath("//span[@data-action='send']"));
    sendButton.click();

    //wait for email to be sent
    Thread.sleep(3000);

    //added scenarios to send the email to another email account and then log into that email and verify next steps
    WebElement profileButton = webDriver.findElement(By.xpath("//div[@id='uhWrapper']//td[@id='uhNavWrapper']//li[@id='yucs-profile']"));
    profileButton.click();

    WebElement signoutButton = webDriver.findElement(By.xpath("//li[a[@id='yucs-signout']]"));
    signoutButton.click();

    WebElement resignInButton = webDriver.findElement(By.xpath("//li[a[@id='uh-signin']]"));
    resignInButton.click();

    WebElement addAccount = webDriver.findElement(By.xpath("//*[@id='manage-account']//a[@class] [@role='button']"));
    addAccount.click();

    WebElement usernameField2 = webDriver.findElement(By.xpath("//input[@id='login-username']"));
    usernameField2.sendKeys("feldiorean_traian");

    WebElement buttonNext2 = webDriver.findElement(By.xpath("//input[@id='login-signin']"));
    buttonNext2.click();

    WebElement passwordField2 = webDriver.findElement(By.xpath("//input[@id='login-passwd']"));
    passwordField2.sendKeys("**************");

    WebElement signInButton2 = webDriver.findElement(By.xpath("//button[@id='login-signin']"));
    signInButton2.click();

    WebElement gotoMailButton = webDriver.findElement(By.xpath(".//a[@id='uh-mail-link']"));
    gotoMailButton.click();

    //wait 5 minutes until mail is received
    Thread.sleep(300000);
    WebElement receivedMessage = webDriver.findElement(By.xpath("//div[@id='msg-list']//div[2]//div[@class='subj']//span[@title='Test']"));
    Assert.assertEquals(receivedMessage.getText(), "Test");

    receivedMessage.click();

    WebElement emailContent = webDriver.findElement(By.xpath("//div[@id='shellinner']//div[@class='thread-body']"));
    Assert.assertEquals(emailContent.getText(), "Content test");

    WebElement sentButton = webDriver.findElement(By.xpath("//li[@id='Sent']"));
    sentButton.click();

    //inconsistent requirement -Verify with an assert the name of the person which sent the latest mail
    //I verified the email of the person which I have sent the last email. Is it ok?
    //also i cannot find xpath of las email
//    WebElement sentLastEmail = webDriver.findElement(By.xpath("//div[@id='msg-list']//div[2]//div[@class='from']//div[@title='feldiorean_traian@yahoo.com']"));
//    Assert.assertEquals(sentLastEmail.getText(), "Traian Feldiorean");

    //why i cannot call the same profileButton and signoutButton
    WebElement profileButton2 = webDriver.findElement(By.xpath("//div[@id='uhWrapper']//td[@id='uhNavWrapper']//li[@id='yucs-profile']"));
    profileButton2.click();

    WebElement signoutButton2 = webDriver.findElement(By.xpath("//li[a[@id='yucs-signout']]"));
    signoutButton2.click();
}
}
