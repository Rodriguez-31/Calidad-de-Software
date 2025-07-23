package com;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCase_2 {

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
	
	// Login
    driver.findElement(By.id("user-name")).sendKeys("standard_user");
    driver.findElement(By.id("password")).sendKeys("secret_sauce");
    driver.findElement(By.id("login-button")).click();

    // Validar URL post-login
    String expectedInventoryUrl = "https://www.saucedemo.com/v1/inventory.html";
    assertEquals("No se redirigió al inventario", expectedInventoryUrl, driver.getCurrentUrl());

    // Agregar todos los productos al carrito
    List<WebElement> addToCartButtons = driver.findElements(By.className("btn_inventory"));
    for (WebElement button : addToCartButtons) {
        button.click();
    }

    // Ir al carrito
    driver.findElement(By.className("shopping_cart_link")).click();
    String expectedCartUrl = "https://www.saucedemo.com/v1/cart.html";
    assertEquals("No se redirigió al carrito", expectedCartUrl, driver.getCurrentUrl());

    // Remover todos los productos del carrito
    List<WebElement> removeButtons = driver.findElements(By.className("cart_button"));
    for (WebElement removeButton : removeButtons) {
        removeButton.click();
    }

    // Volver a la tienda
    driver.findElement(By.className("btn_secondary")).click();
    assertEquals("No regresó a la página de productos", expectedInventoryUrl, driver.getCurrentUrl());

    // Cerrar sesión con espera explícita
    driver.findElement(By.className("bm-burger-button")).click(); // Abrir menú lateral

    // Espera hasta que el enlace de logout esté visible y clickeable
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
    logoutLink.click();

    // Validar que regresó a la pantalla de login
    String expectedLoginUrl = "https://www.saucedemo.com/v1/index.html";
    assertEquals("No se cerró sesión correctamente", expectedLoginUrl, driver.getCurrentUrl());
	

	}

@After
public void cierre() {
	
	//driver.quit();
	}
}
