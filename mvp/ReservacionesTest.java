package com.example.mvp;

import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ReservacionesTest {

    private WebDriver driver;
    private Login login;
    private MainPage mainPage;

    private Reservaciones reservaciones;

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
        reservaciones = new Reservaciones(driver);

    }

    // R5 Navegación

    // R5-A Navegación a reservaciones

    @Test
    public void navReservaciones(){
        login.inputMatricula.sendKeys("A00831289");
        login.buttonBotonIntro.click();

        mainPage.linkRes.click();

        String ExpectedURL = "http://localhost:4200/reservaciones";

        WebDriverWait espera = new WebDriverWait(driver,Duration.ofSeconds(3));
        espera.until(ExpectedConditions.urlToBe(ExpectedURL));

        String currURL = driver.getCurrentUrl();
        assertEquals(currURL,ExpectedURL);
    }

    // R5-E Navegación a mis reservaciones
    @Test
    public void navMisReservaciones(){
        login.inputMatricula.sendKeys("A00831289");
        login.buttonBotonIntro.click();

        mainPage.linkRes.click();
        reservaciones.linkSubMisReservaciones.click();

        String ExpectedURL = "http://localhost:4200/misreservas";

        WebDriverWait espera = new WebDriverWait(driver,Duration.ofSeconds(3));
        espera.until(ExpectedConditions.urlToBe(ExpectedURL));

        String currURL = driver.getCurrentUrl();

        assertEquals(currURL,ExpectedURL);
    }



    // R15 - Lista de Deportes y R16 - Lista de espacios

    public boolean isElementPresent(By locator){
        WebElement element = driver.findElement(locator);

        return element != null;
    }

    @Test
    public void listaEspacios(){
        login.inputMatricula.sendKeys("A00831289");
        login.buttonBotonIntro.click();

        mainPage.linkRes.click();

        isElementPresent(By.id("tarjeta-deporte0"));

        try {
            reservaciones.divTarjetaDeporte.click();
        }catch (UnhandledAlertException e){

        }

        isElementPresent(By.id("tarjeta-espacio0"));

    }

    // R17 Reservación
    // Cancha 1 vacía
    @Test
    public void reservar(){
        login.inputMatricula.sendKeys("L00000000");
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

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait2.until(ExpectedConditions.alertIsPresent());

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

        isElementPresent(By.id("boton-cancelar-0"));

    }

    // R19 - Cancelar Reservación Administrador
    @Test
    public void reservarBorrarAdmin(){
        login.inputMatricula.sendKeys("L00000000");
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

        reservaciones.divBotonCancelar.click();

        isElementPresent(By.id("no-reservaciones"));
    }

    // R19 - Cancelar Reservación Alumno
    @Test
    public void reservarBorrarAlumno(){
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

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait2.until(ExpectedConditions.alertIsPresent());

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
            mainPage.linkRes.click();
        }
        catch (StaleElementReferenceException e){
            mainPage.linkRes.click();
        }

        try {
            reservaciones.linkSubMisReservaciones.click();
        }
        catch (StaleElementReferenceException e){
            try {
                reservaciones.linkSubMisReservaciones.click();
            }
            catch (ElementNotInteractableException e2){
                try {
                    mainPage.linkRes.click();
                }
                catch (StaleElementReferenceException e3){
                    mainPage.linkRes.click();
                }
                reservaciones.linkSubMisReservaciones.click();
            }
        }
        catch (ElementNotInteractableException e2){
            try {
                mainPage.linkRes.click();
            }
            catch (StaleElementReferenceException e3){
                mainPage.linkRes.click();
            }
            reservaciones.linkSubMisReservaciones.click();
        }

        reservaciones.divCancelarAlumno.click();

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
        }


        isElementPresent(By.id("id-cancelada-0"));
    }

    // R20 - Bloquear espacio
    @Test
    public void bloquearEspacio(){
        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        mainPage.linkRes.click();
        reservaciones.divTarjetaDeporte.click();
        reservaciones.spanTarjetaEspacio.click();
        reservaciones.divBloqueos.click();

        String diauno = LocalDate.now().getDayOfMonth() + "/";
        String mes = LocalDate.now().getMonthValue() + "/";
        String anio = LocalDate.now().getYear() + "";

        String inputuno = diauno + mes + anio;
        System.out.println(inputuno);


        reservaciones.inputDateInner.sendKeys(inputuno);
        reservaciones.inputDateInner2.sendKeys(inputuno);
        reservaciones.inputHoraInicioBloqueo.sendKeys("10:00a");
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

        driver.navigate().refresh();

        reservaciones.divBloqueos.click();
        try{
            isElementPresent(By.id("boton-liberar-0"));
        }
        catch (NoSuchElementException e){
            driver.navigate().refresh();

            reservaciones.divBloqueos.click();

            isElementPresent(By.id("boton-liberar-0"));
        }
    }

    //R21 Desbloquear espacio
    @Test
    public void desbloquearEspacio(){
        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        mainPage.linkRes.click();
        reservaciones.divTarjetaDeporte.click();
        reservaciones.spanTarjetaEspacio.click();
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

        reservaciones.divBloqueos.click();

        assertNotEquals(reservaciones.divHoraLiberar.getText(),"10:00 - 22:00");
    }


    // R22 - Lista Reservaciones
    @Test
    public void listaReservaciones(){
        login.inputMatricula.sendKeys("A00830337");
        login.buttonBotonIntro.click();

        mainPage.linkRes.click();
        reservaciones.linkSubMisReservaciones.click();

        isElementPresent(By.id("reserva-0"));
    }

    // R23 - Reservación finalizada
    @Test
    public void reservacionFinalizada(){
        login.inputMatricula.sendKeys("A00830337");
        login.buttonBotonIntro.click();

        mainPage.linkRes.click();
        reservaciones.linkSubMisReservaciones.click();

        isElementPresent(By.id("id-finalizada"));
    }

    // R24 - Confirmación de asistencia
    // No se puede automatizar por cuestiones de los tiempos de activación de la función después de la reserva:
    // La reserva solo se puede hacer 30 minutos después y la confirmación se puede hacer a la hora de la reserva

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
