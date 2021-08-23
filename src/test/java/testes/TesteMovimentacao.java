package testes;

import base.BaseTestes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TesteMovimentacao extends BaseTestes {

    @Before
    public void config(){
        login.realizarLogin();
        setMovimentacao();
        setDadosCadastrados();
        setDsl();
    }

    @Test
    public void verificarCamposObrigatorios() {
        String msgs[] = {"Data da Movimentação é obrigatório", "Data do pagamento é obrigatório",
                        "Descrição é obrigatório", "Interessado é obrigatório",
                        "Valor é obrigatório", "Valor deve ser um número"};

        movimentacao.salvarSemCamposObrigatorios();

        ArrayList<String> teste = dadosCadastrados.pegarMsg();
        String mensagensRetornadas[] = teste.toArray(new String[0]);

        Assert.assertArrayEquals(msgs,mensagensRetornadas);
    }

    @Test
    public void verificarTipoCaracterCampoNumero() {
        movimentacao.salvarCampoValorInvalido();

        List<String> texto = dadosCadastrados.pegarMsg();
        String txt = texto.get(0);

        Assert.assertEquals("Valor deve ser um número", txt);
    }

    @Test
    public void cadastrarMovimentacaoComSucesso() {
        movimentacao.salvarMovimentacaoValida();

        String msgSucesso = dsl.pegarTextoPorCSS("body > div.alert.alert-success");
        Assert.assertEquals("Movimentação adicionada com sucesso!", msgSucesso);
    }

    @Test
    public void verificarResumoMensalMesAtual(){
        dsl.clicarMenuLink("Resumo Mensal");
        Assert.assertFalse(dadosCadastrados.listaComValor("tabelaExtrato"));
    }

    @Test
    public void verificarResumoCadastrado() {
        dsl.clicarMenuLink("Resumo Mensal");

        String movimentacao = dsl.pegarTextoPorCSS("#tabelaExtrato > tbody > tr:nth-child(1) > td:nth-child(1)");

        Assert.assertEquals("Teste", movimentacao);
    }

}
