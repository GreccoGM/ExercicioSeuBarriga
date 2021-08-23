package dados;

import org.openqa.selenium.WebDriver;

import dsl.Dsl;

import java.util.concurrent.TimeUnit;

public class Movimentacao extends Dsl {
    private static WebDriver driver;

    public Movimentacao(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void salvarSemCamposObrigatorios(){
        clicarMenuLink("Criar Movimentação");
        clicarBtnClasse("btn-group");
    }

    public void salvarCampoValorInvalido() {
        clicarMenuLink("Criar Movimentação");

        enviarDadoId("data_transacao","20/05/2015");
        enviarDadoId("data_pagamento","20/05/2017");
        enviarDadoId("interessado","Teste");
        enviarDadoId("descricao","Teste Descrição");

        enviarDadoId("valor","Mil e quinhentos");

        clicarCombo("tipo", "Receita");
        clicarCombo("conta", "Conta2108");

        clicarBtnId("status_pendente");

        clicarBtnClasse("btn-group");
    }

    public void salvarMovimentacaoValida() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        clicarMenuLink("Criar Movimentação");

        enviarDadoId("data_transacao","20/05/2015");
        enviarDadoId("data_pagamento","20/05/2017");
        enviarDadoId("interessado","Teste");
        enviarDadoId("descricao","Teste Descrição");

        enviarDadoId("valor","1500");

        clicarCombo("tipo", "Receita");
        clicarCombo("conta", "Conta2108");

        clicarBtnId("status_pago");

        clicarBtnClasse("btn-group");
    }

}
