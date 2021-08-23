package dados;

import dsl.Dsl;
import org.openqa.selenium.WebDriver;

public class Login extends Dsl{

    private static WebDriver driver;

    public Login(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void realizarLogin(){
        enviarDadoId("email","testeemail@email.com");
        enviarDadoId("senha","123456");
        clicarBtnCSS("body > div.jumbotron.col-lg-4 > form > button");
    }
}
