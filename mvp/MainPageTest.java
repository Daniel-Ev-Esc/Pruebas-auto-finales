package com.example.mvp;

import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Alert;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class MainPageTest {

    public String diaSemana() {

        int day = LocalDate.now().getDayOfWeek().getValue();

        switch (day){
            case 1:
                return "LUNES";
            case 2:
                return "MARTES";
            case 3:
                return "MIÉRCOLES";
            case 4:
                return "JUEVES";
            case 5:
                return "VIERNES";
            case 6:
                return "SÁBADO";
            case 7:
                return "DOMINGO";
        }
        return " ";
    }

    public String diaMes(){
        int diam = LocalDate.now().getDayOfMonth();
        int mes = LocalDate.now().getMonthValue();
        String cero = diam < 10 ? "0" : "";

        switch (mes){
            case 1:
                return cero + diam + " de enero";
            case 2:
                return cero + diam + " de febrero";
            case 3:
                return cero + diam + " de marzo";
            case 4:
                return cero + diam + " de abril";
            case 5:
                return cero + diam + " de mayo";
            case 6:
                return cero + diam + " de junio";
            case 7:
                return cero + diam + " de julio";
            case 8:
                return cero + diam + " de agosto";
            case 9:
                return cero + diam + " de septiembre";
            case 10:
                return cero + diam + " de octubre";
            case 11:
                return cero + diam + " de noviembre";
            case 12:
                return cero + diam + " de diciembre";
        }
        return " ";
    }

    public String obtenerHora(){

        int horaAct = LocalTime.now().getHour() < 12 ? LocalTime.now().getHour() - 1 : LocalTime.now().getHour() - 13;
        horaAct = horaAct == -1 ? 12 : horaAct;
        int minAct = LocalTime.now().getMinute();
        String cero = horaAct < 10 ? "0" : "";
        String ceroMin = minAct < 10 ? "0" : "";

        String a = LocalTime.now().getHour() - 1 < 12 ? "a. m." : "p. m.";

        return cero + horaAct + ":" + ceroMin + minAct + " " + a;
    }

    public String obtenerHoraMinDespues(){

        int horaAct = LocalTime.now().getHour() < 12 ? LocalTime.now().getHour() - 1 : LocalTime.now().getHour() - 13;
        horaAct = horaAct == -1 ? 12 : horaAct;
        int minAct = LocalTime.now().getMinute()+2;
        String cero = horaAct < 10 ? "0" : "";
        String ceroMin = minAct < 10 ? "0" : "";

        String a = LocalTime.now().getHour() - 1 < 12 ? "a.m." : "p.m.";

        return cero + horaAct + ":" + ceroMin + minAct + " " + a;
    }

    public String obtenerHoraMinDespuesDespues(){

        int horaAct = LocalTime.now().getHour() < 12 ? LocalTime.now().getHour() - 1 : LocalTime.now().getHour() - 13;
        horaAct = horaAct == -1 ? 12 : horaAct;
        int minAct = LocalTime.now().getMinute()+3;
        String cero = horaAct < 10 ? "0" : "";
        String ceroMin = minAct < 10 ? "0" : "";

        String a = LocalTime.now().getHour() - 1 < 12 ? "a. m." : "p. m.";

        return cero + horaAct + ":" + ceroMin + minAct + " " + a;
    }

    public boolean isElementPresent(By locator){
        WebElement element = driver.findElement(locator);

        return element != null;
    }

    private WebDriver driverAPI;

    public static final String aforoActualURL = "http://localhost:8888/api/test/aforo";
    public static final String estadoGymURL = "http://localhost:8888/api/test/estado";

    private WebDriver driver;
    private Login login;
    private MainPage mainPage;

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

    }

    // R3 Mensaje Bienvenida

    @Test
    public void mensajeBienvenida(){
        String mensajeEsperado = "HOY, " + diaSemana();
        String fechaEsperada = diaMes();
        String horaEsperada = obtenerHora();

        login.inputMatricula.click();
        login.inputMatricula.sendKeys("A00830337");
        login.buttonBotonIntro.click();

        assertEquals(mainPage.pSaludo.getText(),mensajeEsperado);
        assertEquals(mainPage.pFecha.getText(),fechaEsperada);
        assertEquals(mainPage.pHora.getText(), horaEsperada);
    }

    // R4 Aforo actual
    @Test
    public void aforoActual(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driverAPI = new ChromeDriver(options);

        login.inputMatricula.sendKeys("A00831289");
        login.buttonBotonIntro.click();

        driverAPI.get(aforoActualURL);

        String responseData = driverAPI.getPageSource();
        int start = responseData.indexOf("\"aforo_actual\":") + "\"aforo_actual\":".length();
        int end = responseData.indexOf("}", start);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Wait for a maximum of 10 seconds

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(mainPage.divPersonas,"0 / 280 Personas")));

        String aforoActualStr = responseData.substring(start, end);
        int aforoActual = Integer.parseInt(aforoActualStr);

        String aforoEnPaginaStr = mainPage.divPersonas.getText().substring(0,3);
        int aforoEnPagina = 0;
        try {
            aforoEnPagina = Integer.parseInt(aforoEnPaginaStr);
        }
        catch(NumberFormatException e){
            aforoEnPaginaStr = mainPage.divPersonas.getText().substring(0,2);
            try {
                aforoEnPagina = Integer.parseInt(aforoEnPaginaStr);
            }
            catch (NumberFormatException e2){
                aforoEnPaginaStr = mainPage.divPersonas.getText().substring(0,1);
                aforoEnPagina = Integer.parseInt(aforoEnPaginaStr);
            }
        }

        assertEquals(aforoEnPagina,aforoActual);

        driverAPI.quit();

    }

    // R5 Navegación

    // R5-B Navegación Home

    @Test
    public void navHome(){
        login.inputMatricula.sendKeys("A00831289");
        login.buttonBotonIntro.click();

        mainPage.linkRes.click();
        mainPage.linkHome.click();

        String ExpectedURL = "http://localhost:4200/inicio";

        WebDriverWait espera = new WebDriverWait(driver,Duration.ofSeconds(3));
        espera.until(ExpectedConditions.urlToBe(ExpectedURL));

        String currURL = driver.getCurrentUrl();
        assertEquals(currURL,ExpectedURL);
    }

    // R6 Estado Gym - Database
    @Test
    public void estadoGym(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driverAPI = new ChromeDriver(options);

        login.inputMatricula.sendKeys("A00831289");
        login.buttonBotonIntro.click();

        driverAPI.navigate().to(estadoGymURL);

        String responseData = driverAPI.getPageSource();

        int start = responseData.indexOf("\"estado\":") + "\"estado\":".length();
        int end = responseData.indexOf("}", start);

        String estadoStr = responseData.substring(start, end);
        int estado = Integer.parseInt(estadoStr);

        if (estado == 1){
            estadoStr = "Abierto";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Wait for a maximum of 10 seconds

            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(mainPage.divAbierto,"Cerrado")));

        }else {
            estadoStr = "Cerrado";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Wait for a maximum of 10 seconds

            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(mainPage.divAbierto,"Abierto")));
        }

        String estadoStrPagina = mainPage.divAbierto.getText();

        assertEquals(estadoStrPagina,estadoStr);

        driverAPI.quit();
    }

    // R7 Cambio Estado Gym

    // R7 Cambio Estado Gym - Abierto
    @Test
    public void cambioEstadoAbierto(){
        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        try {
            mainPage.spanBotonCierre.click();
        } catch (ElementClickInterceptedException e){
            mainPage.spanBotonCierre.click();
        }
        mainPage.inputHoraCambio.sendKeys(obtenerHoraMinDespues());
        mainPage.divAbrirManual.click();

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

        WebDriverWait espera = new WebDriverWait(driver,Duration.ofSeconds(3));
        espera.until(ExpectedConditions.textToBePresentInElement(mainPage.divAbierto, "Abierto"));


    }

    // R7 Cambio Estado Gym - Cerrado
    @Test
    public void cambioEstadoAACerrado(){
        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        try {
            mainPage.spanBotonCierre.click();
        } catch (ElementClickInterceptedException e){
            mainPage.spanBotonCierre.click();
        }
        mainPage.inputHoraCambio.sendKeys(obtenerHoraMinDespues());
        mainPage.divCerrarManual.click();

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


        WebDriverWait espera = new WebDriverWait(driver,Duration.ofSeconds(3));
        espera.until(ExpectedConditions.textToBePresentInElement(mainPage.divAbierto, "Cerrado"));

    }

    // R7 Cambiar Estado Fallido
    @Test
    public void cambioEstadoFallido() {
        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        try {
            mainPage.spanBotonCierre.click();
        } catch (ElementClickInterceptedException e){
            mainPage.spanBotonCierre.click();
        }

        mainPage.inputHoraCambio.sendKeys(obtenerHora());


        mainPage.divCerrarManual.click();

        isElementPresent(By.id("Warning-manual"));

    }

    // R8 Programar Cierre

    // R8-A Programar Cierre
    @Test
    public void bbprogramarCierre(){
        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        try {
            mainPage.spanBotonCierre.click();
        } catch (ElementClickInterceptedException e){
            mainPage.spanBotonCierre.click();
        }

        mainPage.inputSeleLunes.click();
        mainPage.inputHoraCierre.sendKeys("12:01a.m.");
        mainPage.inputHora.sendKeys("01:00a.m.");
        mainPage.divBotonProgramar.click();

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

        String texto = "";

        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert Text: " + alertText);

            texto = alertText;

            // Handle the alert dialog
            alert.accept();
        } catch (UnhandledAlertException ex) {
            // Print the alert text
            System.out.println("Alert Text: " + ex.getAlertText());

            texto = ex.getAlertText();

            // Handle the alert dialog
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }

        driver.navigate().refresh();

        try {
            mainPage.spanBotonCierre.click();
        } catch (ElementClickInterceptedException e){
            mainPage.spanBotonCierre.click();
        }

        WebElement selectElement2 = driver.findElement(By.id("dia-borrar"));
        WebElement selectElement3 = driver.findElement(By.id("select-borrar"));


        Select select2 = new Select(selectElement2);
        Select select3 = new Select(selectElement3);

        select2.selectByIndex(1);
        select2.selectByIndex(2);
        select2.selectByIndex(3);
        select2.selectByIndex(4);
        select2.selectByIndex(5);
        select2.selectByIndex(6);
        select2.selectByIndex(7);
        select2.selectByIndex(2);

        select3.selectByIndex(1);

        WebElement selectedOption = select3.getFirstSelectedOption();
        String visibleText = selectedOption.getText();

        assertEquals(visibleText,"00:01-01:00");
    }

    // R8-B Programar Cierre Fallido
    //@Test


    // R9 Borrar Cierre
    // Precondiciones: cierre el lunes de 00:01 a 01:00
    @Test
    public void borrarCierre() throws InterruptedException {
        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        try {
            mainPage.spanBotonCierre.click();
        } catch (ElementClickInterceptedException e){
            mainPage.spanBotonCierre.click();
        }


        WebElement selectElement = driver.findElement(By.id("dia-borrar"));
        WebElement selectElement2 = driver.findElement(By.id("select-borrar"));


        Select select = new Select(selectElement);
        Select select2 = new Select(selectElement2);

        select.selectByIndex(1);
        select.selectByIndex(2);
        select.selectByIndex(3);
        select.selectByIndex(4);
        select.selectByIndex(5);
        select.selectByIndex(6);
        select.selectByIndex(7);
        select.selectByIndex(2);

        select2.selectByIndex(1);

        mainPage.divBotonBorrar.click();

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

        driver.navigate().refresh();

        try {
            mainPage.spanBotonCierre.click();
        } catch (ElementClickInterceptedException e){
            mainPage.spanBotonCierre.click();
        }

        selectElement = driver.findElement(By.id("dia-borrar"));
        selectElement2 = driver.findElement(By.id("select-borrar"));


        select = new Select(selectElement);
        select2 = new Select(selectElement2);

        select.selectByIndex(1);
        select.selectByIndex(2);
        select.selectByIndex(3);
        select.selectByIndex(4);
        select.selectByIndex(5);
        select.selectByIndex(6);
        select.selectByIndex(7);
        select.selectByIndex(2);

        try {
            select2.selectByIndex(1);

            WebElement selectedOption = select2.getFirstSelectedOption();
            String visibleText = selectedOption.getText();

            assertNotEquals(visibleText, "00:01-01:00");
        }
        catch (NoSuchElementException e){

        }
    }

    // R10 Cambiar aforo máximo
    @Test
    public void cambiarAforo(){
        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        mainPage.spanBotonAforo.click();

        int cantidadAIngresar = 300;

        String cantidad = mainPage.divPersonas.getText();

        Integer aforoMax = Integer.parseInt(cantidad.substring(cantidad.length() - 12,cantidad.length() - 9));

        System.out.println(aforoMax);

        if(aforoMax != 300){
            mainPage.inputCantidad.sendKeys("300");
            cantidadAIngresar = 300;
        }
        else{
            mainPage.inputCantidad.sendKeys("250");
            cantidadAIngresar = 250;
        }

        mainPage.buttonGuardar.click();

        driver.navigate().refresh();

        By locator = By.id("personas-container");

        String aforoEnPaginaStr = mainPage.divPersonas.getText().substring(0,3);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Wait for a maximum of 10 seconds

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(mainPage.divPersonas, "0 / 280 Personas")));

        cantidad = mainPage.divPersonas.getText();
        aforoMax = Integer.parseInt(cantidad.substring(cantidad.length() - 12,cantidad.length() - 9));

        assertEquals(cantidadAIngresar, aforoMax);

    }

    // R11 Estimaciones próximas
    @Test
    public void estimacionesProximas(){
        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        int horaAct = (LocalTime.now().getHour() - 1 == -1)? 23 : LocalTime.now().getHour() - 1;
        int hourplus1 = (horaAct + 1 == 24) ? 0 : horaAct + 1;
        int hourplus2 = (horaAct + 2 == 24) ? 0 : horaAct + 2;
        int hourplus3 = (horaAct + 3 == 24) ? 0 : horaAct + 3;

        int horaest1 = Integer.parseInt(mainPage.spanHoraSig.getText().substring(0,2));
        int horaest2 = Integer.parseInt(mainPage.spanHoraSig2.getText().substring(0,2));
        int horaest3 = Integer.parseInt(mainPage.spanHoraSig3.getText().substring(0,2));


        assertEquals(hourplus1, horaest1);
        assertEquals(hourplus2, horaest2);
        assertEquals(hourplus3, horaest3);
    }

    // R13, R14 Despliegue de gráficas
    @Test
    public void graficasMainEstaSemana(){
        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        mainPage.divEstaSemanaBoton.click();

        isElementPresent(By.id("MyChart"));

    }

    @Test
    public void graficasMainHistorial(){
        login.inputMatricula.sendKeys("L00000000");
        login.buttonBotonIntro.click();

        mainPage.divHistorialBoton.click();

        isElementPresent(By.id("MyChart"));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
