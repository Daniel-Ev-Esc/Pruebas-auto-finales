package com.example.mvp;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.LocalDate;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class SistemaNotificacionesTest {

    private WebDriver driver;
    private Login login;
    private MainPage mainPage;
    private Reservaciones reservaciones;

    private SistemaNotificaciones sistemaNotificaciones;

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
        sistemaNotificaciones = new SistemaNotificaciones(driver);
        reservaciones = new Reservaciones(driver);
    }

    // R5 Navegación

    // R5-C Navegación a notificaciones
    @Test
    public void navNotificaciones(){
        login.inputMatricula.sendKeys("A00831289");
        login.buttonBotonIntro.click();

        sistemaNotificaciones.linkNav.click();

        String ExpectedURL = "http://localhost:4200/avisos";

        WebDriverWait espera = new WebDriverWait(driver,Duration.ofSeconds(3));
        espera.until(ExpectedConditions.urlToBe(ExpectedURL));

        String currURL = driver.getCurrentUrl();
        assertEquals(currURL,ExpectedURL);

    }

    // R26 Notificación Creación
    @Test
    public void NotificacionCreacion(){
        login.inputMatricula.sendKeys("A00831289");
        login.buttonBotonIntro.click();

        mainPage.linkRes.click();
        reservaciones.divTarjetaDeporte.click();
        reservaciones.spanTarjetaEspacio.click();

        reservaciones.divHora.click();
        reservaciones.buttonReservar.click();

        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert Text: " + alertText);


            // Handle the alert dialog
            alert.accept();
        } catch (UnhandledAlertException ex) {
            // Print the alert text
            System.out.println("Alert Text: " + ex.getAlertText());


            // Handle the alert dialog
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }

        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert Text: " + alertText);


            // Handle the alert dialog
            alert.accept();
        } catch (UnhandledAlertException ex) {
            // Print the alert text
            System.out.println("Alert Text: " + ex.getAlertText());


            // Handle the alert dialog
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e2){

        }

        try{
            sistemaNotificaciones.linkNav.click();
        }
        catch (StaleElementReferenceException e) {
            sistemaNotificaciones.linkNav.click();
        }

        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert Text: " + alertText);


            // Handle the alert dialog
            alert.accept();
        } catch (UnhandledAlertException ex) {
            // Print the alert text
            System.out.println("Alert Text: " + ex.getAlertText());


            // Handle the alert dialog
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e2){

        }

        try{
            sistemaNotificaciones.linkNav.click();
        }
        catch (StaleElementReferenceException e) {
            sistemaNotificaciones.linkNav.click();
        }

        assertEquals(sistemaNotificaciones.divEncabezado.getText(),"Reservacion Confirmada");
    }

    //R27 Notificación Cancelación
    @Test
    public void NotificacionCancelacion(){
        login.inputMatricula.sendKeys("A00831289");
        login.buttonBotonIntro.click();

        mainPage.linkRes.click();
        reservaciones.divTarjetaDeporte.click();
        reservaciones.spanTarjetaEspacio.click();

        reservaciones.divHora.click();
        reservaciones.buttonReservar.click();

        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert Text: " + alertText);


            // Handle the alert dialog
            alert.accept();
        } catch (UnhandledAlertException ex) {
            // Print the alert text
            System.out.println("Alert Text: " + ex.getAlertText());


            // Handle the alert dialog
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }

        driver.get("http://localhost:4200/");

        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        mainPage.linkRes.click();
        reservaciones.divTarjetaDeporte.click();
        reservaciones.spanTarjetaEspacio.click();

        reservaciones.divBotonCancelar.click();

        driver.get("http://localhost:4200/");

        login.inputMatricula.sendKeys("A00831289");
        login.buttonBotonIntro.click();

        sistemaNotificaciones.linkNav.click();

        assertEquals(sistemaNotificaciones.divEncabezado.getText(),"Reservacion Cancelada");
    }

    //R27 Notificación Cancelación
    @Test
    public void NotificacionCancelacionEspacio(){
        WebDriverWait espera = new WebDriverWait(driver,Duration.ofSeconds(3));
        login.inputMatricula.sendKeys("A00831289");
        login.buttonBotonIntro.click();

        mainPage.linkRes.click();
        reservaciones.divTarjetaDeporte.click();
        reservaciones.spanTarjetaEspacio.click();

        reservaciones.divHora.click();
        reservaciones.buttonReservar.click();

        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert Text: " + alertText);


            // Handle the alert dialog
            alert.accept();
        } catch (UnhandledAlertException ex) {
            // Print the alert text
            System.out.println("Alert Text: " + ex.getAlertText());


            // Handle the alert dialog
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }

        driver.get("http://localhost:4200/");

        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        mainPage.linkRes.click();
        reservaciones.divTarjetaDeporte.click();
        reservaciones.spanTarjetaEspacio.click();
        reservaciones.divBloqueos.click();

        String diauno = LocalDate.now().getDayOfMonth() + "/";
        String mes = LocalDate.now().getMonthValue() + "/";
        String anio = LocalDate.now().getYear() + "";

        String inputuno = mes + diauno + anio;
        System.out.println(inputuno);


        reservaciones.inputDateInner.sendKeys(inputuno);
        reservaciones.inputDateInner2.sendKeys(inputuno);
        reservaciones.inputHoraInicioBloqueo.sendKeys("01:00a");
        reservaciones.inputHoraFinBloqueo.sendKeys("10:00p");

        reservaciones.divBotonBloqueo.click();

        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert Text: " + alertText);


            // Handle the alert dialog
            alert.accept();
        } catch (UnhandledAlertException ex) {
            // Print the alert text
            System.out.println("Alert Text: " + ex.getAlertText());


            // Handle the alert dialog
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }

        try {
            espera.until(elementToBeClickable(By.id("null")));
        }catch (TimeoutException e){

        }

        driver.navigate().refresh();

        reservaciones.divBloqueos.click();

        reservaciones.divBotonLiberar.click();

        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert Text: " + alertText);


            // Handle the alert dialog
            alert.accept();
        } catch (UnhandledAlertException ex) {
            // Print the alert text
            System.out.println("Alert Text: " + ex.getAlertText());


            // Handle the alert dialog
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }

        driver.navigate().refresh();

        driver.get("http://localhost:4200/");

        login.inputMatricula.sendKeys("A00831289");
        login.buttonBotonIntro.click();

        sistemaNotificaciones.linkNav.click();

        assertEquals(sistemaNotificaciones.divMensaje.getText().contains("debido a un bloqueo de la instalación."), true);


    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
