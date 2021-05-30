package com.sicredi;
import com.sicredi.restricoes.RestricoesTest;
import com.sicredi.simulacoes.SimulacoesTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static io.restassured.RestAssured.*;


@RunWith(Suite.class)
@Suite.SuiteClasses(
        {RestricoesTest.class,
                SimulacoesTest.class}
)
public class ApiSicrediTest {
    @Before
    public void setup() {
        // Configurar o caminho comum de acesso a API REST
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api";
    }

}

