package com.epam.ta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;

public class WriteMessagePage extends AbstractPage {
    private String BASEURL = "https://e.mail.ru/compose/";

    @FindBy(xpath = "//textarea[@data-original-name = \"To\"]")
    private WebElement targetField;

    @FindBy(xpath = "//input[@name=\"Subject\"]")
    private WebElement subjectField;

    @FindBy(id = "tinymce")
    private WebElement messageField;

    @FindBy(xpath = "//div[@data-group=\"save-more\"]")
    private WebElement saveDropDownList;

    @FindBy(xpath = "//a[@data-text=\"Сохранить черновик\"]")
    private WebElement saveDraftButton;

    @FindBy(xpath = "//div[@data-mnemo=\"saveStatus\"]")
    private WebElement saveStatus;

    @FindBy(xpath = "//a[@data-mnemo=\"drafts\"]")
    private WebElement drafts;

    @FindBy(xpath = "//div[@data-name=\"send\"]")
    private WebElement sendButton;
     /*
    @FindBy(xpath = "//span[@class=\"js-compose-label compose__labels__label\"][2]")
    private WebElement filledFieldTarget;


    public String getTarget(){
        return filledFieldTarget.getText();
    }

    public String getSubject(){
        return subjectField.getText();
    }

    public  String getMessageText(){
        driver.switchTo().frame(0);
        String messsage = messageField.getText();
        driver.switchTo().defaultContent();
        return messsage;
    }
    */

    public void openPage() {
        driver.navigate().to(BASEURL);
    }
    public WriteMessagePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void writeMessage(String subject, String target, String message){
        targetField.sendKeys(target);
        subjectField.sendKeys(subject);
        driver.switchTo().frame(0);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].textContent= arguments[1];", messageField,message);
        driver.switchTo().defaultContent();

    }

    public String saveDraft(){
        click(saveDropDownList);
        wait.until(ExpectedConditions.visibilityOf(saveDraftButton));
        click(saveDraftButton);
        wait.until(ExpectedConditions.visibilityOf(saveStatus));
        return saveStatus.getText();
    }

    public void openDrafts(){
        click(drafts);
    }

    public void sendCurrent(){

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-name=\"send\"]")));
        //wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@data-name=\"send\"]"),2));
        click(sendButton);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//a[contains(text(),\"Перейти во Входящие\")]"),1));
    }

}
