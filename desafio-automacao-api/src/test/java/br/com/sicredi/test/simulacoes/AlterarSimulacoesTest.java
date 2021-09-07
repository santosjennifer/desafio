package br.com.sicredi.test.simulacoes;

import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import br.com.sicredi.core.BaseAPI;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class AlterarSimulacoesTest extends BaseAPI {
	
	@Test
	@DisplayName("Valida se existe uma simulação com o CPF informado para alterar os dados")
	public void validaSeExiseSimulacaoComCPFInformadoTest() {
		String cpfNaoUtilizado = "90043811043";
		
		given()			
		.contentType(ContentType.JSON)
		.pathParam("cpf", cpfNaoUtilizado)
       	.body(" {\n"  
       			+ "  \"nome\": \"Cliente não existe na base\",\r\n" 
       			+ "  \"cpf\": \"90043811043\",\r\n" 
       			+ "  \"email\": \"desafio@mail.com\",\r\n"
       			+ "  \"valor\": 2000,\r\n"
       			+ "  \"parcelas\": 4,\r\n"
       			+ "  \"seguro\": true\r\n"
       			+ "}")
	   .when()	
	   		.put("/simulacoes/{cpf}")
	   .then()
	   		.statusCode(404)
			.body("mensagem", equalTo("CPF " + cpfNaoUtilizado + " não encontrado"));	   
	}
	
	@Test
	@DisplayName("Valida se o id não pode ser alterado")
	public void validaSeIDNaoPodeSerAlteradoTest() {
		
		/* A API gera uma exceção caso tente alterar o ID do registro
		 * Sugestão: Criar uma validação para não permitir a alteração do ID
		 */
		
		String cpf = "17822386034";
		
		given()			
			.contentType(ContentType.JSON)
			.pathParam("cpf", cpf)
	       	.body(" {\n"  
	       			+ "  \"id\": 99,\r\n"
	       			+ "  \"nome\": \"Ciclano\",\r\n" 
	       			+ "  \"cpf\": \"17822386034\",\r\n" 
	       			+ "  \"email\": \"desafio@mail.com\",\r\n"
	       			+ "  \"valor\": 20000.00,\r\n"
	       			+ "  \"parcelas\": 4,\r\n"
	       			+ "  \"seguro\": false\r\n"
	       			+ "}")
		.when()	
			.put("/simulacoes/{cpf}")
		.then()
		   	.statusCode(404)
			.body("mensagem", equalTo("O ID não pode ser alterado"));	   
	}
	
	@Test 
	@DisplayName("Valida a alteração de todos os campos de uma simulação")
	public void validaAlteracaoTodosCamposTest() {
		
		// Teste está falhando, pois a API não está alterando o campo "valor" 
		
		String cpf = "66414919004";
		
		given()			
		.contentType(ContentType.JSON)
		.pathParam("cpf", cpf)
       	.body(" {\n"         			
       			+ "  \"nome\": \"Ciclano RS\",\r\n" 
       			+ "  \"cpf\": \"66414919004\",\r\n" 
       			+ "  \"email\": \"rsciclano@mail.com\",\r\n"
       			+ "  \"valor\": 15000.00,\r\n"
       			+ "  \"parcelas\": 8,\r\n"
       			+ "  \"seguro\": false\r\n"
       			+ "}")
	   .when()	
	   		.put("/simulacoes/{cpf}")
	   .then()
	   		.statusCode(200)
			.body("nome", equalTo("Ciclano RS"),
				  "cpf", equalTo("66414919004"),
				  "email", equalTo("rsciclano@mail.com"),
				  "valor", equalTo(15000.00),
				  "parcelas", equalTo(8),
				  "seguro", equalTo(false));	   
	}
}
