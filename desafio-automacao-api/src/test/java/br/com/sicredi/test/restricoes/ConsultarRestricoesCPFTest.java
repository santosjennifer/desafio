package br.com.sicredi.test.restricoes;

import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import br.com.sicredi.core.BaseAPI;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class ConsultarRestricoesCPFTest extends BaseAPI {	
	
	@Test
	@DisplayName("Valida se o CPF informado tem restrição")
	public void validaCPFComRestricaoTest() {		
		
		/* De acordo com as regras, caso o CPF tenha restrição deveria retornar a mensagem "O CPF XXXX possui restrição"
		 * porém a API está retornando "O CPF XXXX tem problema"
		 */
		
		String cpfRestricao = "26276298085";
		
		given()	
			.contentType(ContentType.JSON)
			.pathParam("cpf", cpfRestricao)
		.when()
		 		.get("/restricoes/{cpf}")				
		.then()			
			.statusCode(200)
			.body("mensagem", equalTo("O CPF " + cpfRestricao + " possui restrição"));
	}
	
	@Test
	@DisplayName("Valida se o CPF informado não tem restrição")	
	public void validaCPFSemRestricaoTest() {
		
		String cpfSemRestricao = "55932416009";
		
		given()			
			.contentType(ContentType.JSON)
			.pathParam("cpf", cpfSemRestricao)
		.when()
		 		.get("/restricoes/{cpf}")
		.then()			
			.statusCode(204);		
	}

}
