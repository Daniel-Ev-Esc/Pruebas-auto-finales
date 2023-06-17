package com.example.mvp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = http://localhost:4200/
public class SistemaNotificaciones {
    @FindBy(css = "a[id='nav2']")
    public WebElement linkNav;

    @FindBy(css = "div[id='encabezado-0']")
    public WebElement divEncabezado;

    @FindBy(css = "div[id='mensaje-0']")
    public WebElement divMensaje;

    
    public SistemaNotificaciones(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
