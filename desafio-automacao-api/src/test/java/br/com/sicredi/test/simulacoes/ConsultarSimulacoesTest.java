package br.com.sicredi.test.simulacoes;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.hamcrest.Matchers.equalTo;

import br.com.sicredi.core.BaseAPI;

import static io.restassured.RestAssured.given;

public class ConsultarSimulacoesTest extends BaseAPI {
	
	@Test
	@DisplayName("Consulta todas as simulações da base")
	public void consultaTodasimulacoesTest() {	
		given()	 	
		.when()
 			.get("/simulacoes")				
 		.then()
 			.statusCode(200);	
	}
	
	@Test
	@DisplayName("Consulta dados de uma simulação especifica")
	public void consultaSimulacaoEspecificaTest() {	
		String cpf = "66414919004";
		
		given()
			.pathParam("cpf", cpf)
		.when()
 			.get("/simulacoes/{cpf}")				
 		.then()
 			.statusCode(200)
			.body("id", equalTo(11));
	}
	
	@Test
	@DisplayName("Consulta dados com um CPF inexistente")
	public void consultaSimulacaoComCPFInexistenteTest() {	
		String cpfInexistente = "63820560068";
		
		given()
			.pathParam("cpf", cpfInexistente)
		.when()
 			.get("/simulacoes/{cpf}")				
 		.then()
 			.statusCode(404)
			.body("mensagem", equalTo("CPF " + cpfInexistente + " não encontrado"));
	}
	

}
