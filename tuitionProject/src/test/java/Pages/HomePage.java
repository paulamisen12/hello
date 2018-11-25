package Pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.*;



public class HomePage extends TestBase{


    @FindBy(id="hp-widget__sfrom")public WebElement locationFrom;   

    @FindBy(id="hp-widget__sTo")public WebElement locationTo;

    @FindBy(id="searchBtn")public WebElement searchButton;   
 
    public void selectlocation(WebElement element, String Location){
    	element.click();
    	driver.findElement(By.xpath("//div[@style='display: block;']//ul//li[contains(.,'"+Location+"')]")).click();    	
    }
    public HomePage(WebDriver driver){
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
        log.debug("HomePage Initialized");
    }

    

}
