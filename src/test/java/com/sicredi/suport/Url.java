package com.sicredi.suport;

import static io.restassured.RestAssured.*;

public class Url {
    public void baseApi() {
        // Configurar o caminho comum de acesso a API REST
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api";
    }
}
