package com.example.mvp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = http://localhost:4200/
public class Estadisticas {
    @FindBy(css = "a[id='nav2'] span[class*='ng-trigger']")
    public WebElement spanEstadisticas;

    @FindBy(css = "canvas[id='afluencia-semanal']")
    public WebElement canvasAfluenciaSemanal;

    @FindBy(css = "canvas[id='estancia-promedio']")
    public WebElement canvasEstanciaPromedio;

    @FindBy(css = "canvas[id='historial-aforo']")
    public WebElement canvasHistorialAforo;

    

    public Estadisticas(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
