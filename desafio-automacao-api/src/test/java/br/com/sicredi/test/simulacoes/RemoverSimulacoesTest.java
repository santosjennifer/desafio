package br.com.sicredi.test.simulacoes;

import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import br.com.sicredi.core.BaseAPI;

import static io.restassured.RestAssured.given;

public class RemoverSimulacoesTest extends BaseAPI{
	
	@Test
	@DisplayName("Remover uma simulação com ID que não existe na base")
	public void removerSimulacaoQueNaoExisteTest() {
		/*
		 * Deveria retornar HTTP Status 404 e com a mensagem "Simulação não encontrada"
		 * porém está retornando Status 200 e nenhuma mensagem
		 */
		
		given()				
		.when()
	 		.delete("/simulacoes/" + 89000)				
	 	.then()		
	 		.statusCode(404)
	 		.body("mensagem", equalTo("Simulação não encontrada"));

	}

}
