package com.masteringSelenium;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class BasicTest extends DriverBase{
	
	public ExpectedCondition<Boolean> pageTitleToBecome(String t){
		return driver-> driver.getTitle().contains(t);
	}
	
	public void googleCheeseExampleThatSearchesFor(String s) throws AWTException, InterruptedException {
		
		getDriver().get("https://www.google.com");
		WebElement searchField = getDriver().findElement(By.name("q"));
		searchField.clear();
		searchField.sendKeys(s);
		System.out.println("Page title is: " + getDriver().getTitle());
		searchField.submit();
		WebDriverWait wait = new WebDriverWait(getDriver(), 10, 100);
		wait.until(pageTitleToBecome(s));
		System.out.println("Page title is: " + getDriver().getTitle());
		getDriver().quit();
	}

	@Test
	public void googleCheeseExample() throws AWTException, InterruptedException {
		googleCheeseExampleThatSearchesFor("Cheese");
		
	}
	
	@Test
	public void googleBreadExample() throws AWTException, InterruptedException {
		googleCheeseExampleThatSearchesFor("Bread");
		
	}
}
