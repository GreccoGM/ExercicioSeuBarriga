package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import base.BaseTestes;

public class TesteLogin extends BaseTestes {

    @Before
    public void config(){
        login.realizarLogin();
        setDsl();
    }

    @Test
    public void loginComSucesso(){
        login.realizarLogin();

        String elementoHome = dsl.pegarTextoPorLink("Home");
        Assert.assertEquals("Home",elementoHome);
    }
}
