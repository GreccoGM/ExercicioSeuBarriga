package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Movimentacao {

    private static WebDriver driver;

    public Movimentacao(WebDriver driver) {
        this.driver = driver;
    }

    public void salvarSemCamposObrigatorios(){
        menuMovimentacao();
        salvarMovimentacao();
    }

    public void salvarCampoValorInvalido() {
        menuMovimentacao();

        driver.findElement(By.id("data_transacao")).sendKeys("20/05/2015");
        driver.findElement(By.id("data_pagamento")).sendKeys("20/05/2017");
        driver.findElement(By.id("interessado")).sendKeys("Teste");
        driver.findElement(By.id("descricao")).sendKeys("Teste Descrição");

        driver.findElement(By.id("valor")).sendKeys("testeValor");

        clicarCombo("tipo", "Receita");
        clicarCombo("conta", "Conta 3Conta AlteradaConta Alterada");

        salvarMovimentacao();
    }

    public void salvarMovimentacaoValida() {
        menuMovimentacao();

        driver.findElement(By.id("data_transacao")).sendKeys("19/08/2021");
        driver.findElement(By.id("data_pagamento")).sendKeys("21/08/2021");
        driver.findElement(By.id("interessado")).sendKeys("TesteValido");
        driver.findElement(By.id("descricao")).sendKeys("TesteDescricaoValido");

        driver.findElement(By.id("valor")).sendKeys("120");

        clicarCombo("tipo", "Receita");
        clicarCombo("conta", "Conta 3Conta AlteradaConta Alterada");

        salvarMovimentacao();
    }

    private void menuMovimentacao(){
        driver.findElement(By.linkText("Criar Movimentação")).click();
    }
    private void salvarMovimentacao(){
        driver.findElement(By.className("btn-group")).click();

    }
    private void clicarCombo(String id, String valorLista) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valorLista);
    }
}
