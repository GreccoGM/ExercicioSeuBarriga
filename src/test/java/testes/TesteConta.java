package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import base.BaseTestes;

public class TesteConta extends BaseTestes {

    @Before
    public void config(){
        login.realizarLogin();
        setConta();
        setDsl();
        setDadosCadastrados();
    }

    @Test
    public void cadastrarContaSemNome(){
        conta.clicarMenuContas("dropdown-toggle","Adicionar");
        dsl.clicarBtnClasse("btn-group");

        String texto = dsl.pegarTextoPorCSS("body > div.alert.alert-danger");

        Assert.assertEquals("Informe o nome da conta", texto);
    }

    @Test
    public void cadastrarContaComSucesso(){
        conta.clicarMenuContas("dropdown-toggle","Adicionar");

        conta.salvarConta("nome", "Conta6");

        String texto = dsl.pegarTextoPorCSS("body > div.alert.alert-success");

        Assert.assertEquals("Conta adicionada com sucesso!", texto);
    }

    @Test
    public void listarContasCadastradas(){
        conta.clicarMenuContas("dropdown-toggle","Listar");

        Assert.assertFalse(dadosCadastrados.listaComValor("tabelaContas"));
    }

    @Test
    public void sucessoAlteracaoConta(){
        conta.clicarMenuContas("dropdown-toggle","Listar");

        Integer linha = dadosCadastrados.contarLinhas("tabelaContas");
        dsl.clicarBtnCSS("#tabelaContas > tbody > tr:nth-child("+linha+") > td:nth-child(2) > a:nth-child(1)");
        conta.salvarConta("nome","Conta Alterada");

        String texto = dsl.pegarTextoPorCSS("body > div.alert.alert-success");

        Assert.assertEquals("Conta alterada com sucesso!", texto);
    }

    @Test
    public void sucessoExclusaoConta(){
        conta.clicarMenuContas("dropdown-toggle","Listar");

        Integer linha = dadosCadastrados.contarLinhas("tabelaContas");
        dsl.clicarBtnCSS("#tabelaContas > tbody > tr:nth-child("+linha+") > td:nth-child(2) > a:nth-child(2)");

        String texto = dsl.pegarTextoPorCSS("body > div.alert.alert-success");

        Assert.assertEquals("Conta removida com sucesso!", texto);
    }
}
