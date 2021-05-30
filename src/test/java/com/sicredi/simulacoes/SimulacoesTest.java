package com.sicredi.simulacoes;

import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SimulacoesTest {
    @Before
    public void setup() {
        // Configurar o caminho comum de acesso a API REST
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api";
    }

    @Test
    public void testDadoUmCadastroInsereUmaSimulacaoEntaoRetornaStatusCode201() {
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
    public void testDadoUmCadastroIncompletoAoInserirUmaSimulacaoEntaoRetornaStatusCode400() {
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
                .statusCode(409);
    }

    @Test
    public void testDadoUmCadastroExistenteAoInserirUmaSimulacaoEntaoRetornaStatusCode409() {
        given()
                .body("{\n" +
                        "  \"nome\": \"Matheus\",\n" +
                        "  \"cpf\": 12024991637,\n" +
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
                .statusCode(409);
    }


    @Test
    public void testRetornarSimulacoesCadastradas() {
        given()
                .when()
                .get("/v1/simulacoes/")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testDadoUmCPFAltereUmaSimulacaoExistenteEntaoRetornaStatusCode200() {
        given()
                .body("{\n" +
                        "  \"nome\": \"Fulano\",\n" +
                        "  \"cpf\": 66414919004,\n" +
                        "  \"email\": \"teste@gmail.com\",\n" +
                        "  \"valor\": 5000,\n" +
                        "  \"parcelas\": 5,\n" +
                        "  \"seguro\": true\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .put("/v1/simulacoes/66414919004")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testDadoUmCPFInexistenteEntaoRetornaStatusCode404() {
        given()
                .body("{\n" +
                        "  \"nome\": \"Fulano\",\n" +
                        "  \"cpf\": 12024991636,\n" +
                        "  \"email\": \"teste@gmail.com\",\n" +
                        "  \"valor\": 5000,\n" +
                        "  \"parcelas\": 5,\n" +
                        "  \"seguro\": true\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .put("/v1/simulacoes/12024991636")
                .then()
                .assertThat()
                .statusCode(404);
    }

    @Test
    public void testDadoUmCPFConsultaSuaSituacaoEntaoRetornaStatusCode200(){
        given()
                .when()
                .get("/v1/simulacoes/66414919004")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testDadoUmCPFSemSituacaoEntaoRetornaStatusCode404(){
        given()
                .when()
                .get("/v1/simulacoes/66414919000")
                .then()
                .log().all()
                .assertThat()
                .statusCode(404);
    }

    @Test
    public void testDadoUmIDRemoveSuaSituacaoEntaoRetornaStatusCode200(){
        given()
                .when()
                .delete("/v1/simulacoes/12")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testDadoUmIDInexistenteTentarDeletarOMesmoEntaoRetornaStatusCode404(){
        given()
                .when()
                .delete("/v1/simulacoes/-1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(404);
    }

}




