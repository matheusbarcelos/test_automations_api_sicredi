package com.sicredi.restricoes;

import com.sicredi.ApiSicrediTest;
import com.sicredi.constants.Constants;
import com.sicredi.suport.Url;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestricoesTest {
    Constants constants = new Constants();

    @Before
    public void setup() {
        Url conection = new Url();
        conection.baseApi();
    }
    @Test
    public void testDadoUmCPFComRestricaoEntaoRetornaStatusCode200(){

         given()
        .when()
                 .get("/v1/restricoes/"+constants.getCpf_Restricao())
        .then()
                 .body("mensagem", containsString("problema"))
                 .assertThat()
                 .statusCode(200);
    }
    @Test
    public void testDadoUmCPFSemRestricaoEntaoRetornaStatusCode204(){

        given()
        .when()
                .get("/v1/restricoes/"+constants.getCpf_Sem_Restricao())
        .then()
                .assertThat()
                .statusCode(204);
    }
}
