package com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class TestCase_4 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void SetUp() {
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/Driver/chromedriver.exe");
        driver = new ChromeDriver(co);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @Test
    public void ValidarCamposObligatoriosVacios() {
        // Cerrar anuncios si aparecen
        try {
            WebElement closeAd = wait.until(ExpectedConditions.elementToBeClickable(By.id("close-fixedban")));
            closeAd.click();
        } catch (Exception e) {
            // No hay anuncio
        }

        // Hacer scroll para mostrar botón de Submit
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");

        // Dar clic en Submit sin llenar campos
        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();

        // Esperar a que aparezcan los campos requeridos marcados en rojo
        boolean firstNameInvalid = driver.findElement(By.id("firstName")).getAttribute("class").contains("field-error");
        boolean lastNameInvalid = driver.findElement(By.id("lastName")).getAttribute("class").contains("field-error");
        boolean genderInvalid = driver.findElements(By.cssSelector("div[class*='gender'] .was-validated")).size() > 0;
        boolean mobileInvalid = driver.findElement(By.id("userNumber")).getAttribute("class").contains("field-error");

        // Verificación (podrías usar assert si estás con JUnit/TestNG)
        if (firstNameInvalid || lastNameInvalid || genderInvalid || mobileInvalid) {
            System.out.println("✅ Validación correcta: El formulario mostró errores por campos vacíos.");
        } else {
            System.out.println("❌ Fallo: El formulario no validó campos obligatorios vacíos.");
        }
    }

    @After
    public void cierre() {
        //driver.quit();
    }
}