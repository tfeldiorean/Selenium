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

    //Log in
    WebElement usernameField = webDriver.findElement(By.xpath("//input[@id='login-username']"));
    usernameField.sendKeys("entest2017");

    WebElement buttonNext = webDriver.findElement(By.xpath("//input[@id='login-signin']"));
    buttonNext.click();

    WebElement passwordField = webDriver.findElement(By.xpath("//input[@id='login-passwd']"));
    passwordField.sendKeys("Chrome22");

    WebElement signInButton = webDriver.findElement(By.xpath("//button[@id='login-signin']"));
    signInButton.click();

    //Compose
    WebElement composeButton = webDriver.findElement(By.xpath("//li[@id='Compose']//span"));
    composeButton.click();

    WebElement toButton = webDriver.findElement(By.xpath("//input[@id='to-field']"));
    toButton.sendKeys("entest2@yahoo.com");

    WebElement subjectField = webDriver.findElement(By.xpath("//input[@id='subject-field']"));
    subjectField.sendKeys("Test");

    WebElement textField = webDriver.findElement(By.xpath("//div[@id='rtetext']"));
    textField.sendKeys("Content test");

    //Send
    WebElement sendButton = webDriver.findElement(By.xpath("//span[@data-action='send']"));
    sendButton.click();

    //wait for email to be sent
    Thread.sleep(3000);

    //added scenarios to send the email to another email account and then log into that email and verify next steps
    WebElement profileButton = webDriver.findElement(By.xpath("//div[@id='uhWrapper']//td[@id='uhNavWrapper']//li[@id='yucs-profile']"));
    profileButton.click();

    //Signout
    WebElement signoutButton = webDriver.findElement(By.xpath("//li[a[@id='yucs-signout']]"));
    signoutButton.click();

    WebElement resignInButton = webDriver.findElement(By.xpath("//li[a[@id='uh-signin']]"));
    resignInButton.click();

    WebElement addAccount = webDriver.findElement(By.xpath("//*[@id='manage-account']//a[@class] [@role='button']"));
    addAccount.click();

    //Log in with the second account
    WebElement usernameField2 = webDriver.findElement(By.xpath("//input[@id='login-username']"));
    usernameField2.sendKeys("entest2");

    WebElement buttonNext2 = webDriver.findElement(By.xpath("//input[@id='login-signin']"));
    buttonNext2.click();

    WebElement passwordField2 = webDriver.findElement(By.xpath("//input[@id='login-passwd']"));
    passwordField2.sendKeys("Chrome23");

    WebElement signInButton2 = webDriver.findElement(By.xpath("//button[@id='login-signin']"));
    signInButton2.click();

    WebElement gotoMailButton = webDriver.findElement(By.xpath(".//a[@id='uh-mail-link']"));
    gotoMailButton.click();

    //wait 5 minutes until mail is received
    //Thread.sleep(300000)
    //The email is already sent, to skip the time I // the wait. But you can undo and test it:)
    WebElement receivedMessage = webDriver.findElement(By.xpath("//div[@id='msg-list']//div[2]//div[@class='subj']//span[@title='Test']"));
    Assert.assertEquals(receivedMessage.getText(), "Test");

    receivedMessage.click();

    //Verify email
    WebElement emailContent = webDriver.findElement(By.xpath("//div[@id='shellinner']//div[@class='thread-body']"));
    Assert.assertEquals(emailContent.getText(), "Content test");

    WebElement sentButton = webDriver.findElement(By.xpath("//li[@id='Sent']"));
    sentButton.click();

    //Verify Sent
    //I've managed to select the person that sent the last, in the sent compartment. It is possible for the xpath to change but after 3 tests it's ok

    WebElement sentLastEmail = webDriver.findElement(By.xpath(".//div[@class='list-view-items-page']/div[2]/div/div[2]/div[1]/div[@class='name-list']/div[@class='name first']"));
    Assert.assertEquals(sentLastEmail.getText(),"Entest En");

    //Why i cannot call the same profileButton and signoutButton?

    Thread.sleep(3000);
    WebElement profileButton2 = webDriver.findElement(By.xpath("//div[@id='uhWrapper']//td[@id='uhNavWrapper']//li[@id='yucs-profile']"));
    profileButton2.click();

    WebElement signoutButton2 = webDriver.findElement(By.xpath("//li[a[@id='yucs-signout']]"));
    signoutButton2.click();
}
}
