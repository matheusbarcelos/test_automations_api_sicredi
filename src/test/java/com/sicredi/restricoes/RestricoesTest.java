package com.sicredi.restricoes;

import com.sicredi.ApiSicrediTest;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestricoesTest {
    @Before
    public void setup(){
        // Configurar o caminho comum de acesso a API REST
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api";
    }

    @Test
    public void testDadoUmCPFComRestricaoEntaoRetornaStatusCode200(){

     given()
             .when()
                    .get("/v1/restricoes/97093236014")
             .then()
                .assertThat()
                    .statusCode(200);
    }
    @Test
    public void testDadoUmCPFSemRestricaoEntaoRetornaStatusCode204(){

        given()
                .when()
                        .get("/v1/restricoes/41281418366")
                .then()
                    .assertThat()
                        .statusCode(204);

    }
}
