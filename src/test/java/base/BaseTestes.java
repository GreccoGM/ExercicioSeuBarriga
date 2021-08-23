package base;

import dsl.Dsl;
import dados.Conta;
import dados.DadosCadastrados;
import dados.Login;
import dados.Movimentacao;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTestes {

    protected static WebDriver driver;
    protected static Login login;
    protected static DadosCadastrados dadosCadastrados;
    protected static Conta conta;
    protected static Dsl dsl;
    protected static Movimentacao movimentacao;

    @Before
    public void inicializarDriver(){
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me");
        login = new Login(driver);
    }

    protected void setDsl(){
        dsl = new Dsl(driver);
    }
    protected void setDadosCadastrados(){
        dadosCadastrados = new DadosCadastrados(driver);
    }
    protected void setConta(){
        conta = new Conta(driver);
    }
    protected void setMovimentacao(){
        movimentacao = new Movimentacao(driver);
    }

//        @After
//    public void finalizarDriver(){
//        driver.quit();
//    }
}
