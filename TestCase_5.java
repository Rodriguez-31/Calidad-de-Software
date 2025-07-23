package com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestCase_5 {

    private WebDriver driver;

    @Before
    public void SetUp() {
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/Driver/chromedriver.exe");
        driver = new ChromeDriver(co);
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void validarCorreoInvalido() throws InterruptedException {
        // Llenar campos con correo inválido
        driver.findElement(By.id("userName")).sendKeys("Wilberth Rodriguez Jimenez");
        driver.findElement(By.id("userEmail")).sendKeys("correo-invalido"); // sin @ ni dominio
        driver.findElement(By.id("currentAddress")).sendKeys("Costa Rica/San Jose");
        driver.findElement(By.id("permanentAddress")).sendKeys("Costa Rica/San Jose");

        // Enviar formulario
        driver.findElement(By.id("submit")).click();

        // Verificar si se aplica clase de error al campo de correo
        WebElement emailField = driver.findElement(By.id("userEmail"));
        String classAttr = emailField.getAttribute("class");

        // Validar que contiene clase de error
        assertTrue("El campo de email debería mostrar error", classAttr.contains("field-error"));
    }

    @After
    public void cierre() {
        //driver.quit();
    }
}
