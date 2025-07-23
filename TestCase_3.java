package com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCase_3 {

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://demoqa.com/automation-practice-form";

    @Before
    public void setUp() {
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/Driver/chromedriver.exe");
        driver = new ChromeDriver(co);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @Test
    public void caso1_envioExitosoFormulario() throws InterruptedException {
        // Llenar campos básicos
        driver.findElement(By.id("firstName")).sendKeys("Iván");
        driver.findElement(By.id("lastName")).sendKeys("Rodríguez");
        driver.findElement(By.id("userEmail")).sendKeys("ivan@example.com");

        // Seleccionar género Masculino
        WebElement genderMale = driver.findElement(By.xpath("//label[@for='gender-radio-1']"));
        genderMale.click();

        // Número móvil
        driver.findElement(By.id("userNumber")).sendKeys("1234567890");

        // Fecha de nacimiento: abrir selector
        driver.findElement(By.id("dateOfBirthInput")).click();

        // Seleccionar año 1995
        WebElement yearSelect = driver.findElement(By.className("react-datepicker__year-select"));
        yearSelect.click();
        yearSelect.findElement(By.xpath("//option[@value='1995']")).click();

        // Seleccionar mes septiembre (valor 8)
        WebElement monthSelect = driver.findElement(By.className("react-datepicker__month-select"));
        monthSelect.click();
        monthSelect.findElement(By.xpath("//option[@value='8']")).click();

        // Seleccionar día 10
        driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='10' and not(contains(@class,'outside-month'))]")).click();

     // Subjects: escribir "Maths" y seleccionar con Enter
        WebElement subjectsInput = driver.findElement(By.id("subjectsInput"));
        subjectsInput.sendKeys("Maths");
        subjectsInput.sendKeys(Keys.ENTER);
        
        // Hobbies: seleccionar Deportes (Sports)
        driver.findElement(By.xpath("//label[@for='hobbies-checkbox-1']")).click();

        // Dirección actual
        driver.findElement(By.id("currentAddress")).sendKeys("San José, Costa Rica");

        // Scroll hacia abajo para que aparezca dropdown State
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");

        // Seleccionar State "NCR"
        driver.findElement(By.id("react-select-3-input")).sendKeys("NCR");
        driver.findElement(By.id("react-select-3-input")).sendKeys("\n");

        // Seleccionar City "Delhi"
        driver.findElement(By.id("react-select-4-input")).sendKeys("Delhi");
        driver.findElement(By.id("react-select-4-input")).sendKeys("\n");

        // Hacer scroll y enviar formulario
        WebElement submitBtn = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        submitBtn.click();

        // Esperar modal de confirmación
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));

        // Verificar título modal
        WebElement modalTitle = driver.findElement(By.id("example-modal-sizes-title-lg"));
        assertEquals("Thanks for submitting the form", modalTitle.getText());

        // Cerrar modal
        WebElement closeBtn = driver.findElement(By.id("closeLargeModal"));
        closeBtn.click();
    }

    @After
    public void tearDown() {
        //driver.quit();
    }
}