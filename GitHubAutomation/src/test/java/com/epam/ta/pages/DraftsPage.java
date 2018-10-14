package com.epam.ta.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class DraftsPage extends AbstractPage {
    private String BASEURL = "https://e.mail.ru/messages/drafts/";

    @FindBy(xpath = "//*[@id=\"b-letters\"]/div[1]/div[5]/div/div[2]/div[1]/div/a")
    private WebElement firstDraftInList;

    @FindBy(xpath = "//a[@data-shortcut=\"g,s\"]")
    private WebElement sentsFolder;

    public void openPage() {
        driver.navigate().to(BASEURL);
    }

    public DraftsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }


    public boolean isMessageInDrafts(){

        try{
            wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class=\"b-datalist__empty\"]"),1));
        }
        catch (TimeoutException e){
            return true;
        }
        catch (StaleElementReferenceException e){
            return true;
        }
        return false;
    }

    public boolean isDataOfMessageCorrect(String subject, String target, String message){

        if (firstDraftInList.getText().contains(subject+message+"\n"+target)){
            return true;
        } else {
            return false;
        }
    }

    public void clickOnDraft(){
        click(firstDraftInList);
    }

    public void openSentsFolder(){
        click(sentsFolder);
    }
}
