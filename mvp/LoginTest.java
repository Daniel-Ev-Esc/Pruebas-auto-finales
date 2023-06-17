package com.example.mvp;

import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class LoginTest {

    public boolean isElementPresent(By locator){
        WebElement element = driver.findElement(locator);

        return element != null;
    }

    public boolean isElementNotPresent(By locator){
        WebElement element = driver.findElement(locator);

        return element == null;
    }

    private WebDriver driver;
    private Login login;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:4200/");

        login = new Login(driver);
    }

    // R1 Inicio de Sesión Alumno

    // R1-A Inicio-Sesión-Positivo
    @Test
    public void AlumnoExitoso(){
        login.inputMatricula.sendKeys("A00830337");
        login.buttonBotonIntro.click();

        String ExpectedURL = "http://localhost:4200/inicio";

        WebDriverWait espera = new WebDriverWait(driver,Duration.ofSeconds(3));
        espera.until(ExpectedConditions.urlToBe(ExpectedURL));

        String currURL = driver.getCurrentUrl();
        assertEquals(currURL,ExpectedURL);
    }

    // R1-B Inicio-Sesión-Negativo
    @Test
    public void AlumnoFallido(){
        String colorInicial = login.inputMatricula.getCssValue("background-color");


        login.inputMatricula.sendKeys("A00000000");
        login.buttonBotonIntro.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Wait for a maximum of 10 seconds

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(login.inputMatricula, "background-color", colorInicial)));


        String colorAct = login.inputMatricula.getCssValue("background-color");

        assertNotEquals(colorInicial,colorAct);

    }

    // R1-C Inicio-Sesión-Formato-Incorrecto
    @Test
    public void AlumnoIncorrecto(){
        String ingresoAleatorio = RandomString.make();
        String colorInicial = login.inputMatricula.getCssValue("background-color");

        login.inputMatricula.sendKeys(ingresoAleatorio);
        login.buttonBotonIntro.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Wait for a maximum of 10 seconds

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(login.inputMatricula, "background-color", colorInicial)));

        String colorAct = login.inputMatricula.getCssValue("background-color");

        assertNotEquals(colorInicial,colorAct);
    }

    // R2 Inicio de Sesión Alumno

    // R2-A Inicio-Sesión-Positivo
    @Test
    public void AdminExitoso(){
        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        String ExpectedURL = "http://localhost:4200/inicio";

        WebDriverWait espera = new WebDriverWait(driver,Duration.ofSeconds(3));
        espera.until(ExpectedConditions.urlToBe(ExpectedURL));

        String currURL = driver.getCurrentUrl();
        assertEquals(currURL,ExpectedURL);
    }

    // R2-B Inicio-Sesión-Negativo
    @Test
    public void AdminFallido(){
        String colorInicial = login.inputMatricula.getCssValue("background-color");


        login.inputMatricula.sendKeys("L00000001");
        login.buttonBotonIntro.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Wait for a maximum of 10 seconds

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(login.inputMatricula, "background-color", colorInicial)));

        String colorAct = login.inputMatricula.getCssValue("background-color");

        assertNotEquals(colorInicial,colorAct);

    }

    // R1-C Inicio-Sesión-Formato-Incorrecto
    @Test
    public void AdminIncorrecto(){
        String ingresoAleatorio = RandomString.make();
        String colorInicial = login.inputMatricula.getCssValue("background-color");

        login.inputMatricula.sendKeys(ingresoAleatorio);
        login.buttonBotonIntro.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Wait for a maximum of 10 seconds

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(login.inputMatricula, "background-color", colorInicial)));

        String colorAct = login.inputMatricula.getCssValue("background-color");

        assertNotEquals(colorInicial,colorAct);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
