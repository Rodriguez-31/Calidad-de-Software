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

public class TestCase_1 {

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
    List<org.openqa.selenium.WebElement> addToCartButtons = driver.findElements(By.className("btn_inventory"));
    for (org.openqa.selenium.WebElement button : addToCartButtons) {
        button.click();
    }

    // Ir al carrito
    driver.findElement(By.className("shopping_cart_link")).click();
    String expectedCartUrl = "https://www.saucedemo.com/v1/cart.html";
    assertEquals("No se redirigió al carrito", expectedCartUrl, driver.getCurrentUrl());

    // Clic en Checkout
    driver.findElement(By.className("checkout_button")).click();
    String expectedStepOneUrl = "https://www.saucedemo.com/v1/checkout-step-one.html";
    assertEquals("No se redirigió al paso 1 del checkout", expectedStepOneUrl, driver.getCurrentUrl());

    // Llenar formulario de datos
    driver.findElement(By.id("first-name")).sendKeys("Ivan");
    driver.findElement(By.id("last-name")).sendKeys("Rodriguez");
    driver.findElement(By.id("postal-code")).sendKeys("10302");

    // Continuar al paso 2
    driver.findElement(By.className("cart_button")).click();
    String expectedStepTwoUrl = "https://www.saucedemo.com/v1/checkout-step-two.html";
    assertEquals("No se redirigió al paso 2 del checkout", expectedStepTwoUrl, driver.getCurrentUrl());

    // Finalizar compra
    driver.findElement(By.className("cart_button")).click();

    // Validar que se llegó al final (opcional)
    String expectedFinishUrl = "https://www.saucedemo.com/v1/checkout-complete.html";
    assertEquals("No se completó la orden", expectedFinishUrl, driver.getCurrentUrl());
	

	}

@After
public void cierre() {
	
	//driver.quit();
	}
}
