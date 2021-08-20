package Testes;

import Dados.DadosCadastrados;
import Dados.DadosLogin;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Conta;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteConta {

    private static WebDriver driver;
    private static Conta dsl;
    private static DadosLogin dadosLogin;
    private static DadosCadastrados dadosCadastrados;


    @Before
    public void inicializarDriver(){
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me");
        dsl = new Conta(driver);
        dadosLogin = new DadosLogin(driver);
        dadosCadastrados = new DadosCadastrados(driver);
    }

//    @After
//    public void finalizarDriver(){
//        driver.quit();
//    }

    @Test
    public void cadastrarContaSemNome(){
        dadosLogin.realizarLogin();

        dsl.clicarMenuContas("dropdown-toggle","Adicionar");
        dsl.salvar();
        //dsl.clicarBotaoCSS("body > div.col-lg-10 > form > div.btn-group > button");

        //String texto = driver.findElement(By.className("alert alert-danger")).getText();
        String texto = dsl.pegarTextoPorCSS("body > div.alert.alert-danger");

        //System.out.println("!!! -> "+texto);
        Assert.assertEquals("Informe o nome da conta", texto);
    }

    @Test
    public void cadastrarContaComSucesso(){
        dadosLogin.realizarLogin();
        dsl.clicarMenuContas("dropdown-toggle","Adicionar");

        dsl.salvarConta("nome", "Conta6");

        String texto = dsl.pegarTextoPorCSS("body > div.alert.alert-success");

        Assert.assertEquals("Conta adicionada com sucesso!", texto);
    }

    @Test
    public void listarContasCadastradas(){
        dadosLogin.realizarLogin();
        dsl.clicarMenuContas("dropdown-toggle","Listar");

        Assert.assertFalse(dadosCadastrados.listaComValor("tabelaContas"));
    }

    @Test
    public void sucessoAlteracaoConta(){
        dadosLogin.realizarLogin();
        dsl.clicarMenuContas("dropdown-toggle","Listar");

        Integer linha = dadosCadastrados.contarLinhas("tabelaContas");
        dsl.clicarBotaoCSS("#tabelaContas > tbody > tr:nth-child("+linha+") > td:nth-child(2) > a:nth-child(1)");
        dsl.salvarConta("nome","Conta Alterada");

        String texto = dsl.pegarTextoPorCSS("body > div.alert.alert-success");

        Assert.assertEquals("Conta alterada com sucesso!", texto);
    }

    @Test
    public void sucessoExclusaoConta(){
        dadosLogin.realizarLogin();
        dsl.clicarMenuContas("dropdown-toggle","Listar");

        Integer linha = dadosCadastrados.contarLinhas("tabelaContas");
        dsl.clicarBotaoCSS("#tabelaContas > tbody > tr:nth-child("+linha+") > td:nth-child(2) > a:nth-child(2)");

        String texto = dsl.pegarTextoPorCSS("body > div.alert.alert-success");

        Assert.assertEquals("Conta removida com sucesso!", texto);
    }

}
