package com;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MiPrimeraSimulacion {

	private WebDriver driver;
	
	@Before
	public void SetUp() {
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		System.getProperty("webdriver.chrome.driver", "./src/test/resources/Driver/chromedriver.exe");
		driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/v1/index.html");
	}

@Test
public void MiPrimerTest()	{
	

	}

@After
public void cierre() {
	
	//driver.quit();
	}
}
