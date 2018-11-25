package Tests;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.TestBase;
import Pages.*;

public class Test1 extends TestBase{

	HomePage homePage;

	
	@Test(dataProvider="ReadVariant") //It get values from ReadVariant function method	 
    public void HomePage(String to, String from) throws InterruptedException{
        //Create Page object
		System.out.println(to);
		System.out.println(from);		
		homePage = new HomePage(driver);
		homePage.selectlocation(homePage.locationFrom, to);
		homePage.selectlocation(homePage.locationTo, from);
		homePage.searchButton.click();
		Thread.sleep(3000);
		Assert.assertEquals(driver.getTitle(), "Flight Search");
    }
	

}
