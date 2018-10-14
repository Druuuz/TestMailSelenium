package com.epam.ta.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SentPage extends AbstractPage {
    private String BASEURL = "https://e.mail.ru/messages/sent/";

    @FindBy(xpath = "//*[@id=\"b-letters\"]/div[1]/div[5]/div/div[6]/div[4]/div/div/div/div/div/a")
    private WebElement firstSentMessage;
    public void openPage() {
        driver.navigate().to(BASEURL);
    }
    public SentPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }


        public boolean isMessageInSents(){
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

        public void deleteSentMessage(){

        }




}
