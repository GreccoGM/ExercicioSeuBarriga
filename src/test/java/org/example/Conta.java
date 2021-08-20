package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.util.concurrent.TimeUnit;

public class Conta {

    private static WebDriver driver;

    public Conta(WebDriver driver) {
        this.driver = driver;
    }

    public void clicarMenuContas(String nomeClasse, String valorLista){
        driver.findElement(By.className(nomeClasse)).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.linkText(valorLista)).click();
    }

    public void salvarConta(String idCampo, String nomeConta){
        driver.findElement(By.id(idCampo)).sendKeys(nomeConta);
//        clicarBotaoCSS("body > div.col-lg-10 > form > div.btn-group > button");
        salvar();
    }

    public void clicarBotaoCSS(String css){
        driver.findElement(By.cssSelector(css)).click();
    }

    public String pegarTextoPorCSS(String css){
        return driver.findElement(By.cssSelector(css)).getText();
    }

    public void salvar(){
        driver.findElement(By.className("btn-group")).click();
    }

    public void clicarMenuLink(String textoLink){
        driver.findElement(By.linkText(textoLink)).click();
    }

    public String pegarTextoPorClasse(String classe){
        return driver.findElement(By.className(classe)).getText();
    }
}
