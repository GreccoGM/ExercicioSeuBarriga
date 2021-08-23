package dados;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import dsl.Dsl;

public class Conta extends Dsl {

    private static WebDriver driver;

    public Conta(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clicarMenuContas(String nomeClasse, String valor){
        clicarBtnClasse(nomeClasse);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        clicarMenuLink(valor);
    }

    public void salvarConta(String idCampo, String nomeConta){
        enviarDadoId(idCampo,nomeConta);
        driver.findElement(By.id(idCampo)).sendKeys(nomeConta);
        clicarBtnClasse("btn-group");
    }
}
