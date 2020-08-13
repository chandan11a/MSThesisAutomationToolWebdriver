package com.crossover.e2e;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class CrossoverTest {
	  WebDriver driver;
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
    
    @SuppressWarnings("deprecation")
	@Test
    public void CrossOver() throws Exception {
    	try {
    		setUp() ;
    		
    		driver.manage().window().maximize();
    		    
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Implicit wait
    	
    	String url=properties.getProperty("crossoverurl");
        driver.get(url);
        WebDriverWait wait=new WebDriverWait(driver,30);
        
      WebElement signup= webElement(properties.getProperty("SignUp"));
    
  
  
 
  
   wait.until(ExpectedConditions.elementToBeClickable(signup));//WebDriver Wait - Explicit wait
 
   signup.click();
   
   String CurrentURL=driver.getCurrentUrl();
   Assert.assertEquals("https://codeshare.io/register", CurrentURL);
   WebElement Name= webElement(properties.getProperty("Name"));
   wait.until(ExpectedConditions.elementToBeClickable(Name));//WebDriver Wait - Explicit wait
   
   Name.sendKeys("Chandan");
   WebElement Email= webElement(properties.getProperty("Email"));
   Email.sendKeys("ckc1243547@gmail.com");
   
   WebElement pass= webElement(properties.getProperty("pass"));
   pass.sendKeys("12345678");
   
   
   WebElement SignUP= webElement(properties.getProperty("SignUP"));
   SignUP.click();
  
   try {
	   WebElement LoggedInUser= webElement(properties.getProperty("LoggedInUser"));
	   wait.until(ExpectedConditions.elementToBeClickable(LoggedInUser));//WebDriver Wait - Explicit wait
	   String LoggediNUsername=LoggedInUser.getAttribute("alt");
	   System.out.println("End"+LoggediNUsername);
	   Assert.assertEquals("Chandan", LoggediNUsername);

	  
	   
   }	catch(org.openqa.selenium.StaleElementReferenceException ex)
   {
	   WebElement LoggedInUser= webElement(properties.getProperty("LoggedInUser"));
	   wait.until(ExpectedConditions.elementToBeClickable(LoggedInUser));//WebDriver Wait - Explicit wait
	   String LoggediNUsername=LoggedInUser.getAttribute("alt");
	   System.out.println("End"+LoggediNUsername);

	  
   }
   driver.quit();
   
  // tearDown();
   
     
   
   
   
   
   
   
   
    }
    	
    catch(Exception e)
    {
    	e.printStackTrace();
    	tearDown();
        
    }

}public WebElement webElement(String sTarget) {

	
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