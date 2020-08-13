package com.crossover.e2e;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.codehaus.plexus.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Sulis extends TestCase {
     WebDriver driver;
     String testCasename=this.getClass().getSimpleName();
		String extentReportFile = System.getProperty("user.dir")
				
				+ "\\Report\\"+testCasename+".html";
     ExtentReports extent = new ExtentReports(extentReportFile, false);

		// Start the test using the ExtentTest class object.
		ExtentTest extentTest = extent.startTest("Sulis Web Check",
				"Join and Unjoin Course");
    private Properties properties = new Properties();

    public void setUp() throws Exception {
    	String path=System.getProperty("user.dir");
        properties.load(new FileReader(new File(path+"/src/test/resources/test.properties")));
        //Dont Change below line. Set this value in test.properties file incase you need to change it..
        System.setProperty("webdriver.chrome.driver",properties.getProperty("webdriver.chrome.driver") );
        driver = new ChromeDriver();
    }

    public void tearDown() throws Exception {
        driver.quit();
    }

    /*
     * Please focus on completing the task
     * 
     */
    @Test
    public void testSendEmail() throws Exception {
    	
    	try {
    		
			
			
    		 WebDriverWait wait=new WebDriverWait(driver, 20); //Webdriverwait for Explicit Wait
    		    
    	String url=properties.getProperty("url");
        driver.get(url);
        driver.manage().window().maximize() ;
         driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);		//Implicit Wait		
         extentTest.log(LogStatus.INFO, "Browser Launched");
			
        WebElement user = webElement(properties.getProperty("userElement"));
        user.sendKeys(properties.getProperty("username"));
    	extentTest.log(LogStatus.INFO, "Entering Username");	
        WebElement pass = webElement(properties.getProperty("passElement"));
       
      
      
        pass.sendKeys(properties.getProperty("password"));
        
        WebElement Submit = webElement(properties.getProperty("Submit"));
        extentTest.log(LogStatus.INFO, "Entering password");	
        Submit.click();
        extentTest.log(LogStatus.INFO, "Click SignIn");	
        
        
        WebElement Membership = webElement(properties.getProperty("Compose"));
        wait.until(ExpectedConditions.elementToBeClickable(Membership));
        extentTest.log(LogStatus.PASS, "Login Successfull");		
        
     
        Membership.click();
        extentTest.log(LogStatus.INFO, "Click Membership");	

        WebElement JoinableSites = webElement(properties.getProperty("JoinableSites"));
        JoinableSites.click();
		
        extentTest.log(LogStatus.INFO, "Click Joinable Sites");	

  
        webElement(properties.getProperty("JoinNow")).click();
        
        extentTest.log(LogStatus.INFO, "Join Now");
        
        webElement(properties.getProperty("MemberSites")).click();
        extentTest.log(LogStatus.INFO, "My Current Sites");
        
        webElement(properties.getProperty("Subject")).click();
        extentTest.log(LogStatus.PASS, "Subject Selected");
        webElement(properties.getProperty("Unjoin1")).click();
        extentTest.log(LogStatus.INFO, "Unjoin the Course");    
        webElement(properties.getProperty("Unjoin2")).click();
        
      
        extentTest.log(LogStatus.PASS, "Course Unenrolled");
		
    	extentTest.log(LogStatus.INFO, "Browser closed");

		
    	// close report.
		extent.endTest(extentTest);

		// writing everything to document.
		extent.flush();
       
        Thread.sleep(10000);
    	}catch(Exception e)
    	{
    		// close report.
    		extent.endTest(extentTest);

    		// writing everything to document.
    		extent.flush();
    		e.printStackTrace();
    	}
        
        
        
    }
    public String captureScreenShot(String testCasename) {
		String screenShotFilePath=null;
		String x=null;
		Random rand = new Random(); 
		int rand_int1 = rand.nextInt(100000); 
		try {	
			
			File scrFile =null;
				scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
		

			String screenShotPah=System.getProperty("user.dir")
					
					+ "\\Report\\";

			File fi= new File(screenShotPah);
			if(!fi.exists()){
				fi.mkdirs();
			}
			FileUtils.copyFile(scrFile, new File(screenShotPah+ testCasename+rand_int1+ ".png"));
			screenShotFilePath="Screenshot:" + ""+testCasename+rand_int1+".png";

			 x=screenShotPah+ testCasename+rand_int1+ ".png";
			 System.out.println(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	

    public void javascriptExecutorClick(String locator){
    	 WebDriverWait wait=new WebDriverWait(driver, 20); //Webdriverwait for Explicit Wait
    	    
    	 WebElement element=webElement(locator);
    	wait.until(ExpectedConditions.elementToBeClickable(element));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",element );
	}
	public WebElement webElement(String sTarget) {

		
		if (sTarget.startsWith("css")) {
			sTarget = sTarget.substring(4);
			return driver.findElement(By.cssSelector(sTarget));
		}
		if(sTarget.startsWith("(/"))
		{
			sTarget = sTarget.substring(0);
			return driver.findElement(By.xpath(sTarget));
		}
		if (sTarget.startsWith("//") ) {
			return driver.findElement(By.xpath(sTarget));
		}
		if (sTarget.startsWith("xpath")) {
			sTarget = sTarget.substring(6);
			return driver.findElement(By.xpath(sTarget));
		}
		if (sTarget.startsWith("name")) {
			sTarget = sTarget.substring(5);
			return driver.findElement(By.name(sTarget));
		}
		if (sTarget.startsWith("id")) {
			sTarget = sTarget.substring(3);
			return driver.findElement(By.id(sTarget));
		}
		if (sTarget.startsWith("link")) {
			sTarget = sTarget.substring(5);
			return driver.findElement(By.linkText(sTarget));
		}
		return null;
	}
}
