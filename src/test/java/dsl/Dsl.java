package DSL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Dsl {

    private static WebDriver driver;

    public Dsl(WebDriver driver) {
        this.driver = driver;
    }

    public void clicarMenuLink(String valor){
        driver.findElement(By.linkText(valor)).click();
    }

    public String pegarTextoPorCSS(String css){
        return driver.findElement(By.cssSelector(css)).getText();
    }

    public void clicarBtnClasse(String classeBtn){
        driver.findElement(By.className(classeBtn)).click();
    }

    public void clicarBtnCSS(String botao){
        driver.findElement(By.cssSelector(botao)).click();
    }

    public void clicarBtnId(String botao){
        driver.findElement(By.id(botao)).click();
    }

    public void clicarCombo(String id, String valorLista) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valorLista);
    }


    public void enviarDadoId(String idCampo, String nomeConta){
        driver.findElement(By.id(idCampo)).sendKeys(nomeConta);
    }

    public String pegarTextoPorLink(String texto){
        return driver.findElement(By.linkText(texto)).getText();
    }



}
