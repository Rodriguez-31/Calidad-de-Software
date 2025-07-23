package com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestCase_6 {

    private WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/Driver/chromedriver.exe");
        driver = new ChromeDriver(co);
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");
    }

    @Test
    public void llenarFormularioYVerificar() {
        // Datos para llenar
        String fullName = "Juan Pérez";
        String email = "juan.perez@example.com";
        String currentAddress = "123 Calle Principal\nSan José, Costa Rica";
        String permanentAddress = "456 Avenida Secundaria\nHeredia, Costa Rica";

        // Llenar campos
        driver.findElement(By.id("userName")).sendKeys(fullName);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("currentAddress")).sendKeys(currentAddress);
        driver.findElement(By.id("permanentAddress")).sendKeys(permanentAddress);

        // Enviar formulario
        driver.findElement(By.id("submit")).click();

        // Verificar que los datos aparecen en la sección de resultados
        WebElement output = driver.findElement(By.id("output"));
        String outputText = output.getText();

        assertTrue(outputText.contains(fullName));
        assertTrue(outputText.contains(email));
        assertTrue(outputText.contains("123 Calle Principal"));
        assertTrue(outputText.contains("San José"));
        assertTrue(outputText.contains("456 Avenida Secundaria"));
        assertTrue(outputText.contains("Heredia"));
    }

    @After
    public void tearDown() {
        //driver.quit();
    }
}