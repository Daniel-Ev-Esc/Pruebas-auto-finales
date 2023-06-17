package com.example.mvp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = http://localhost:4200/
public class Login {
    
    @FindBy(id = "matricula")
    public WebElement inputMatricula;

    @FindBy(id = "boton-intro")
    public WebElement buttonBotonIntro;

    public Login(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
