package Testes;

import Dados.DadosCadastrados;
import Dados.DadosLogin;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Conta;
import org.example.Movimentacao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class TesteMovimentacao {

    private static Conta dsl;
    private static Movimentacao movimentacao;
    private static DadosLogin dadosLogin;
    private static DadosCadastrados dadosCadastrados;

    @Before
    public void inicializarDriver() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me");
        driver.manage().window().maximize();
        dsl = new Conta(driver);
        movimentacao = new Movimentacao(driver);
        dadosLogin = new DadosLogin(driver);
        dadosCadastrados = new DadosCadastrados(driver);
    }

//    @After
//    public void finalizarDriver(){
//        driver.quit();
//    }

    @Test
    public void verificarCamposObrigatorios() {
        String msgs[] = {"Data da Movimentação é obrigatório", "Data do pagamento é obrigatório",
                "Descrição é obrigatório", "Interessado é obrigatório",
                "Valor é obrigatório", "Valor deve ser um número"};

        dadosLogin.realizarLogin();

        movimentacao.salvarSemCamposObrigatorios();

        ArrayList<String> teste = dadosCadastrados.pegarMsg();
        String mensagensRetornadas[] = teste.toArray(new String[0]);

        Assert.assertArrayEquals(msgs,mensagensRetornadas);
    }

    @Test
    public void verificarTipoCaracterCampoNumero() {
        dadosLogin.realizarLogin();

        movimentacao.salvarCampoValorInvalido();

        List<String> texto = dadosCadastrados.pegarMsg();
        String txt = texto.get(0);

        Assert.assertEquals("Valor deve ser um número", txt);
    }

    @Test
    public void cadastrarMovimentacaoComSucesso() {
        dadosLogin.realizarLogin();

        movimentacao.salvarMovimentacaoValida();

        String msgSucesso = dsl.pegarTextoPorCSS("body > div.alert.alert-success");
        Assert.assertEquals("Movimentação adicionada com sucesso!", msgSucesso);
    }

    @Test
    public void verificarResumoMensalMesAtual() {
        dadosLogin.realizarLogin();

        dsl.clicarMenuLink("Resumo Mensal");
        Assert.assertFalse(dadosCadastrados.listaComValor("tabelaExtrato"));
    }

    @Test
    public void verificarResumoCadastrado() {
        dadosLogin.realizarLogin();

        dsl.clicarMenuLink("Resumo Mensal");
        ArrayList<String> movimentacoes= dadosCadastrados.pegarContasCadastradas();
        System.out.println("Teste: " +movimentacoes);

        Assert.assertTrue(movimentacoes.contains("TesteDescricaoValido"));
    }

}
