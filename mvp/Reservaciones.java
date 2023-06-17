package com.example.mvp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = http://localhost:4200/

public class Reservaciones {
    @FindBy(css = "div[id='tarjeta-deporte0']")
    public WebElement divTarjetaDeporte;

    @FindBy(css = "span[id='tarjeta-espacio0']")
    public WebElement spanTarjetaEspacio;

    @FindBy(css = "a[id$='Reservaciones']")
    public WebElement linkSubMisReservaciones;

    @FindBy(css = "div[id='reserva-0']")
    public WebElement divReserva;

    @FindBy(css = "div[id='hora0']")
    public WebElement divHora;

    @FindBy(css = "button[id='id-reservar']")
    public WebElement buttonReservar;

    @FindBy(css = "div[id='boton-cancelar-0']")
    public WebElement divBotonCancelar;

    @FindBy(css = "div[class^='no-reservations'] div")
    public WebElement divReservaciones;

    @FindBy(css = "div[id='id-bloqueos']")
    public WebElement divBloqueos;

    @FindBy(css = "input[id='id-input-1']")
    public WebElement inputDateInner;

    @FindBy(css = "input[id='id-input-2']")
    public WebElement inputDateInner2;



    @FindBy(css = "input[id='hora-inicio-bloqueo']")
    public WebElement inputHoraInicioBloqueo;

    @FindBy(css = "input[id='hora-fin-bloqueo']")
    public WebElement inputHoraFinBloqueo;

    @FindBy(css = "div[id='boton-liberar-0']")
    public WebElement divBotonLiberar;

    @FindBy(id = "id-confirmar-0")
    public WebElement divConfirmar;

    @FindBy(id = "id-cancelar-alumno-0")
    public WebElement divCancelarAlumno;

    @FindBy(css = "div[id='boton-bloqueo']")
    public WebElement divBotonBloqueo;

    @FindBy(css = "div[id='cerrar-tarjeta']")
    public WebElement divCerrarTarjeta;

    @FindBy(css = "div[id='hora-liberar-0']")
    public WebElement divHoraLiberar;


    public Reservaciones(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
