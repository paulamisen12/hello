package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExtentManager;


public class TestBase {	
	
	public static Logger log = Logger.getLogger("rootLogger");
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static XSSFWorkbook workbook;
    public static XSSFSheet worksheet;
    public static DataFormatter formatter= new DataFormatter();
    public static String file_location = System.getProperty("user.dir")+"//Data.xlsx";
    public static String SheetName="TestCase1";

	
	public static Properties p = new Properties();	
	public static FileReader reader;
	public static WebDriver driver;
	
	
	@BeforeSuite(description="Before suite Setup")
	public void BeforeClass(){
		
		log.debug("Log Initialized");
		log.debug("Before Suite Run Set up begins");
		try{
			reader=new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\Config.properties");
		    p.load(reader);
		    log.debug("Properties File Initialized");
		}
		catch(Exception e){
			log.error("Properties File NOT Initialized");			
		}		
	}
	
	@BeforeMethod
	public void BeforeMethod(){
		System.out.println(p.getProperty("browser"));
		if(p.getProperty("browser").equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\dipra\\Desktop\\Driver\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.debug("Firefox Driver File Initialized");
		}
		
		driver.get(p.getProperty("url"));
	}
	
	@AfterMethod(description="After Method")
	public void AfterMethod(){
		driver.quit();
		log.debug("Driver Quit");
	}
	
	@DataProvider
    public static Object[][] ReadVariant() throws IOException{
    FileInputStream fileInputStream= new FileInputStream(file_location); //Excel sheet file location get mentioned here
        workbook = new XSSFWorkbook (fileInputStream); //get my workbook 
        worksheet=workbook.getSheet(SheetName);// get my sheet from workbook
        XSSFRow Row=worksheet.getRow(0);     //get my Row which start from 0       
        int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
        int ColNum= Row.getLastCellNum(); // get last ColNum         
        Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
         
            for(int i=0; i<RowNum-1; i++) //Loop work for Rows
            {  
                XSSFRow row= worksheet.getRow(i+1);
                 
                for (int j=0; j<ColNum; j++) //Loop work for colNu\m
                {
                    
                            String value=formatter.formatCellValue(row.getCell(j));
                            Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value         
                    
                }
            }
        log.debug("Data Provider Returned data");    
        return Data;
    }	
}
