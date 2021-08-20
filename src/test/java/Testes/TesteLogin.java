package Testes;

import Dados.DadosLogin;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteLogin {
    private static WebDriver driver;
    private static DadosLogin dadosLogin;

    @Before
    public void inicializarDriver(){
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me");
        dadosLogin = new DadosLogin(driver);
    }

    //    @After
//    public void finalizarDriver(){
//        driver.quit();
//    }
    @Test
    public void loginComSucesso(){
        dadosLogin.realizarLogin();

        String elementoHome = driver.findElement(By.linkText("Home")).getText();
        Assert.assertEquals("Home",elementoHome);
    }
}
