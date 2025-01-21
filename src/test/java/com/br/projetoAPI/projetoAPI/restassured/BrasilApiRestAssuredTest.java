package com.br.projetoAPI.projetoAPI.restassured;

import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
//import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class BrasilApiRestAssuredTest {
    private static final String BASE_URL = "https://brasilapi.com.br/api/cep/v2/";

    @BeforeAll
    public static void setUp() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    @DisplayName("Testa a consulta de um CEP válido na API Brasil API")
    public void testConsultarCepValido() {
        String cep = "01001000";
        /* Código comentando para possível consulta no futuro*/
        //Response response = 
        given()
            .baseUri(BASE_URL)
            .pathParam("cep", cep)
        .when()
            .get("{cep}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("cep", equalTo(cep))
            .body("state", equalTo("SP"))
            .body("city", equalTo("São Paulo"))
            .body("neighborhood", equalTo("Sé"))
            .body("street", equalTo("Praça da Sé"))
            .body("service", equalTo("open-cep"))
            .body("location.type", equalTo("Point"))
            .body("location.coordinates.longitude", equalTo("-46.63428906534399"))
            .body("location.coordinates.latitude", equalTo("-23.550430309662772"));
            //.extract()
            //.response();
        //System.out.println(response.asString());
    }
    @Test
    @DisplayName("Testa a consulta de um CEP inválido com 8 caracteres")
    public void testConsultarCepInvalidoComOitoCaracteres() {
        String cep = "00000000";
        /* Código comentando para possível consulta no futuro*/
        //Response response = 
        given()
            .baseUri(BASE_URL)
            .pathParam("cep", cep)
        .when()
            .get("{cep}")
        .then()
            .statusCode(404)
            .contentType(ContentType.JSON)
            .body("message", equalTo("Todos os serviços de CEP retornaram erro."));
            //.extract()
            //.response();
        //System.out.println(response.asString());
    }
    @Test
    @DisplayName("Testa a consulta de um CEP inválido com 7 caracteres")
    public void testConsultarCepInvalidoComSeteCaracteres() {
        String cep = "0000000";
        /* Código comentando para possível consulta no futuro*/
        //Response response = 
        given()
            .baseUri(BASE_URL)
            .pathParam("cep", cep)
        .when()
            .get("{cep}")
        .then()
            .statusCode(400)
            .contentType(ContentType.JSON)
            .body("message", equalTo("CEP deve conter exatamente 8 caracteres."));
            //.extract()
            //.response();
        //System.out.println(response.asString());
    }
    @Test
    @DisplayName("Testa a consulta de um CEP inválido com 9 caracteres")
    public void testConsultarCepInvalidoComNoveCaracteres() {
        String cep = "000000000";
        /* Código comentando para possível consulta no futuro*/
        //Response response = 
        given()
            .baseUri(BASE_URL)
            .pathParam("cep", cep)
        .when()
            .get("{cep}")
        .then()
            .statusCode(400)
            .contentType(ContentType.JSON)
            .body("message", equalTo("CEP deve conter exatamente 8 caracteres."));
            //.extract()
            //.response();
        //System.out.println(response.asString());
    }
}
