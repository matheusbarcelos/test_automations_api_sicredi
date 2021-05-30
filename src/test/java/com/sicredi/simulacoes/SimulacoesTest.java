package com.sicredi.simulacoes;

import com.sicredi.constants.Constants;
import com.sicredi.suport.Url;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SimulacoesTest {
    Constants constants = new Constants();
    @Before
    public void setup() {
        Url conection = new Url();
        conection.baseApi();
    }

    @Test
    public void testDadoUmCadastroInsereUmaSimulacaoEntaoRetornaStatusCode201() {

        given()
                    .body("{\n" +
                        "  \"nome\": \"+constants\",\n" +
                        "  \"cpf\": 12024991635,\n" +
                        "  \"email\": \"shaka@gmail.com\",\n" +
                        "  \"valor\": 1200,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}")
                    .contentType(ContentType.JSON)
        .when()
                    .post("/v1/simulacoes")
        .then()
                    .body("cpf", containsString("12024991635"))
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
                        "}")
                .contentType(ContentType.JSON)
       .when()
                .post("/v1/simulacoes")
       .then()
                .assertThat()
                .statusCode(400)
                .log().all();
    }

    @Test
    public void testDadoUmCadastroComAtributosIncorretosAoInserirUmaSimulacaoRetornaStatusCode400(){
        given()
                .body("{\n" +
                        "  \"nome\": \"Pedro\",\n" +
                        "  \"cpf\": 84746766657,\n" +
                        "  \"email\": \"pedro@gmail.com\",\n" +
                        "  \"valor\": 900,\n" +
                        "  \"parcelas\": 1,\n" +
                        "  \"seguro\": true\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/v1/simulacoes")
                .then()
                .assertThat()
                .statusCode(400);
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
                        "  \"seguro\": false\n" +
                        "}")
                .contentType(ContentType.JSON)
        .when()
                .put("/v1/simulacoes/"+constants.getCpf_Alterar_Simulacao())
        .then()
                .body("nome", containsString("Fulano"))
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testDadoUmCPFInexistenteEfetuarAlteracaoEntaoRetornaStatusCode404() {
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
                .put("/v1/simulacoes/"+constants.getCpf_Inexistente_Simulacao())
        .then()
                .assertThat()
                .statusCode(404);
    }

    @Test
    public void testRetornarSimulacoesCadastradas() {
        given()
        .when()
                .get("/v1/simulacoes/")
        .then()
                .assertThat()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testDadoUmCPFConsultaSuaSituacaoEntaoRetornaStatusCode200(){
        given()
        .when()
                .get("/v1/simulacoes/"+constants.getCpf_Consultar_Simulacao())
        .then()
                .body("cpf", containsString(constants.getCpf_Consultar_Simulacao()))
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testDadoUmCPFSemSituacaoEntaoRetornaStatusCode404(){
        given()
        .when()
                .get("/v1/simulacoes/"+constants.getCpf_Invalido_Simulacao())
        .then()
                .assertThat()
                .statusCode(404);
    }

    @Test
    public void testDadoUmIDRemoveSuaSituacaoEntaoRetornaStatusCode200(){
        given()
        .when()
                .delete("/v1/simulacoes/12")
        .then()
                .assertThat()
                .statusCode(200);
    }
}




