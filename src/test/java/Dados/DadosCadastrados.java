package Dados;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DadosCadastrados {
    private static WebDriver driver;

    public DadosCadastrados(WebDriver driver) {
        this.driver = driver;
    }

    public Integer contarLinhas(String idTabela){
        WebElement tabela = driver.findElement(By.id(idTabela));
        List<WebElement> tableRows = tabela.findElements(By.cssSelector("#"+idTabela+" > tbody > tr"));

        Integer tamanho = tableRows.size();
        //System.out.println(tamanho);

        return tamanho;
    }

    public Boolean listaComValor(String idTabela){
        Boolean listaVazia = true;

        Integer tamanho = contarLinhas(idTabela);
        if (tamanho != 0){
            listaVazia = false;
        }

        return listaVazia;
    }

    public ArrayList<String> pegarMsg (){
        ArrayList<String> listaErros = new ArrayList<>();

        WebElement lista = driver.findElement(By.cssSelector("body > div.alert.alert-danger > ul"));
        List<WebElement> listaRows = lista.findElements(By.tagName("li"));

        for(WebElement li: listaRows) {
            listaErros.add(li.getText());
            //System.out.println(listaErros);
        }

        return listaErros;
    }

    public ArrayList<String> pegarContasCadastradas (){
        ArrayList<String> listaMovimentacao = new ArrayList<>();

        WebElement lista = driver.findElement(By.cssSelector("#tabelaExtrato > tbody > tr"));
        List<WebElement> listaRows = lista.findElements(By.cssSelector("td:nth-child(1)"));

        for(WebElement td: listaRows) {
            String texto = td.getText();
            listaMovimentacao.add(texto);
            System.out.println(listaMovimentacao);
        }

        return listaMovimentacao;
    }
}
/*#tabelaExtrato > tbody > tr:nth-child(1) > td:nth-child(1)
#tabelaExtrato > tbody > tr:nth-child(2) > td:nth-child(1)*/