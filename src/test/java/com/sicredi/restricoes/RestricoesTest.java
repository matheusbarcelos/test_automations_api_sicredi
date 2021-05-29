package com.sicredi.restricoes;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestricoesTest {
    @Test
    public void testDadoUmCPFComRestricaoEntaoRetornaStatusCode200(){
      // Configurar o caminho comum de acesso a API REST
      baseURI = "http://localhost";
      port = 8080;
      basePath = "/api";




    }

}
