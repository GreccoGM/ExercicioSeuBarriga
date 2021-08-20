package Dados;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DadosLogin {

    private static WebDriver driver;

    public DadosLogin(WebDriver driver) {
        this.driver = driver;
    }

    public void realizarLogin(){
        driver.findElement(By.id("email")).sendKeys("testeemail@email.com");
        driver.findElement(By.id("senha")).sendKeys("123456");
        driver.findElement(By.cssSelector("body > div.jumbotron.col-lg-4 > form > button")).click();
    }
}
