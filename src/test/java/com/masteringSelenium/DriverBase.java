package com.masteringSelenium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class DriverBase {
	List<DriverFactory> driverList = Collections.synchronizedList(new ArrayList<DriverFactory>());

	ThreadLocal<DriverFactory> driverThread;

	@BeforeMethod
	public void initialValue() {
		driverThread = new ThreadLocal<DriverFactory>();
		System.out.println("*********1");
		DriverFactory driverFactory = new DriverFactory();
		System.out.println("*********2");
		driverList.add(driverFactory);
		driverThread.set(driverFactory);
		System.out.println("driverList size="+driverList.size());
	}
	
	public WebDriver getDriver() {
		return driverThread.get().getDriver();
	}
	
	@AfterMethod
	public void quit() {
		getDriver().quit();
	}
}
