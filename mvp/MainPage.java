package com.example.mvp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = http://localhost:4200/

public class MainPage {
    @FindBy(css = "p[id='fecha']")
    public WebElement pFecha;

    @FindBy(css = "p[id='hora']")
    public WebElement pHora;

    

    @FindBy(css = "a[id='nav1']")
    public WebElement linkRes;

    @FindBy(css = "a[id='nav0']")
    public WebElement linkHome;

    @FindBy(css = "div[id='boton-cierre']")
    public WebElement spanBotonCierre;

    @FindBy(css = "span[id='botonAforo']")
    public WebElement spanBotonAforo;

    @FindBy(css = "input[id='cantidad']")
    public WebElement inputCantidad;

    @FindBy(css = "div[class='personas-container']")
    public WebElement divPersonas;

    @FindBy(css = "button[id='guardar']")
    public WebElement buttonGuardar;

    @FindBy(css = "div[id='hora-sig1']")
    public WebElement spanHoraSig;

    @FindBy(css = "#hora-sig2")
    public WebElement spanHoraSig2;

    @FindBy(css = "div[id='hora-sig3']")
    public WebElement spanHoraSig3;

    @FindBy(css = "input[id='hora-cambio']")
    public WebElement inputHoraCambio;

    @FindBy(css = "div[id^='cerrar']")
    public WebElement divCerrarManual;

    

    @FindBy(css = "div[class^='texto-estado']")
    public WebElement divAbierto;

    @FindBy(css = "div[id='Warning-manual']")
    public WebElement divWarningManual;

    @FindBy(css = "div[id='abrir_manual']")
    public WebElement divAbrirManual;

    @FindBy(css = "input[id='hora-cierre']")
    public WebElement inputHoraCierre;

    @FindBy(css = "input[id='hora-ap']")
    public WebElement inputHora;

    @FindBy(css = "select[id='dia-programar']")
    public WebElement selectDiaProgramar;

    @FindBy(css = "div[id='boton-programar']")
    public WebElement divBotonProgramar;

    @FindBy(css = "div[id='hora-de-cambio']")
    public WebElement divHoraCambio;

    @FindBy(css = "input[id='cierre-borrar']")
    public WebElement inputCierreBorrar;

    @FindBy(css = "div[id='boton-borrar']")
    public WebElement divBotonBorrar;

    @FindBy(css = "select[id='dia-borrar']")
    public WebElement selectDiaBorrar;

    @FindBy(css = "p[id^='saludo']")
    public WebElement pSaludo;

    @FindBy(css = "input[id='sele-lunes']")
    public WebElement inputSeleLunes;

    @FindBy(css = "div[id='esta-semana-boton']")
    public WebElement divEstaSemanaBoton;

    @FindBy(css = "div[id='historial-boton']")
    public WebElement divHistorialBoton;





    
    

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
