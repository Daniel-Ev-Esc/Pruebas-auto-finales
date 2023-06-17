package com.example.mvp;

import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class EstadisticasTest {

    private WebDriver driver;
    private Login login;
    private MainPage mainPage;

    private Estadisticas estadisticas;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:4200/");

        mainPage = new MainPage(driver);
        login = new Login(driver);
        estadisticas = new Estadisticas(driver);
    }

    // R5 Navegación

    // R5-D Navegación a estadísticas
    @Test
    public void navEstadisticas(){
        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        estadisticas.spanEstadisticas.click();

        String ExpectedURL = "http://localhost:4200/estadisticas";

        WebDriverWait espera = new WebDriverWait(driver,Duration.ofSeconds(3));
        espera.until(ExpectedConditions.urlToBe(ExpectedURL));

        String currURL = driver.getCurrentUrl();
        assertEquals(currURL,ExpectedURL);

    }

    // R25 Página de estadísticas

    public boolean isElementPresent(By locator){
        WebElement element = driver.findElement(locator);

        return element != null;
    }

    @Test
    public void despliegueGraficas(){
        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        estadisticas.spanEstadisticas.click();

        isElementPresent(By.id("afluencia-semanal"));
        isElementPresent(By.id("historial-aforo"));
        isElementPresent(By.id("estancia-promedio"));

    }

    //R29 Filtrado por día
    // Debido a que la gráfica es un elemento externo, esta no puede ser detectada por las herramientas de aqua

    //R30 Descarga de datos
    // Debido a que se descarga un archivo externo, este no puede ser detectado por las herrmientas de aqua

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
