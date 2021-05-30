package com.sicredi.simulacoes;

import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SimulacoesTest {
    @Before
    public void setup(){
            // Configurar o caminho comum de acesso a API REST
            baseURI = "http://localhost";
            port = 8080;
            basePath = "/api";
        }
    @Test
    public void testDadoUmCadastroInsereUmaSimulacaoEntaoRetornaStatusCode201(){
        given()
                .body("{\n" +
                        "  \"nome\": \"Shaka\",\n" +
                        "  \"cpf\": 97093236014,\n" +
                        "  \"email\": \"shaka@gmail.com\",\n" +
                        "  \"valor\": 1200,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                    .post("/v1/simulacoes")
                    .then()
                    .assertThat()
                    .statusCode(201);
    }

    @Test
    public void testRetornarSimulacoesExistentes(){
        given()
                .when()
                .get("/v1/simulacoes/")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }


    }
