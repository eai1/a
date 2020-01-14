package com.masteringSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	WebDriver driver;
	
	
	public WebDriver getDriver() {
		if(driver==null) {
			System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("*********** initalized");
		}
			return driver;
	}
	
	public void quitDriver() {
		if(driver!=null) {
			driver.quit();
			driver=null;
		}
		
	}
}
