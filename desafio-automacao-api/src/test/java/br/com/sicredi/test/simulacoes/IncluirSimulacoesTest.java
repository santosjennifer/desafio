package br.com.sicredi.test.simulacoes;

import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.given;

import br.com.sicredi.core.BaseAPI;
import io.restassured.http.ContentType;

public class IncluirSimulacoesTest extends BaseAPI {	
	
	@Test
	@DisplayName("Valida se o CPF foi informado ao incluir uma simulação")
	public void validaSeCPFFoiInformadoTest() {		
		given()			
			.contentType(ContentType.JSON)
	       	.body(" {\n"  
	       			+ "  \"nome\": \"Pessoa sem CPF\",\r\n" 	       			
	       			+ "  \"email\": \"desafio@mail.com\",\r\n"
	       			+ "  \"valor\": 32000,\r\n"
	       			+ "  \"parcelas\": 24,\r\n"
	       			+ "  \"seguro\": true\r\n"
	       			+ "}")
	   .when()
            .post("/simulacoes")
       .then()           
       		.statusCode(400)
       		.body("erros.cpf", equalTo("CPF não pode ser vazio"));		
	}
	
	@Test
	@DisplayName("Valida se o nome foi informado ao incluir uma simulação")
	public void validaSeNomeFoiInformadoTest() {		
		given()			
			.contentType(ContentType.JSON)
	       	.body(" {\n"  
	       			+ "  \"cpf\": \"123456789\",\r\n" 	       			
	       			+ "  \"email\": \"desafio@mail.com\",\r\n"
	       			+ "  \"valor\": 32000,\r\n"
	       			+ "  \"parcelas\": 24,\r\n"
	       			+ "  \"seguro\": false\r\n"
	       			+ "}")
	   .when()
            .post("/simulacoes")
       .then()           
       		.statusCode(400)
       		.body("erros.nome", equalTo("Nome não pode ser vazio"));		
	}
	
	@Test
	@DisplayName("Valida se o email foi informado ao incluir uma simulação")
	public void validaSeEmailFoiInformadoTest() {		
		given()			
			.contentType(ContentType.JSON)
	       	.body(" {\n"  	       		       			
	       			+ "  \"nome\": \"Pessoa sem e-mail\",\r\n"
	       			+ "  \"cpf\": \"123456789\",\r\n" 	
	       			+ "  \"valor\": 980,\r\n"
	       			+ "  \"parcelas\": 1,\r\n"
	       			+ "  \"seguro\": false\r\n"
	       			+ "}")
	   .when()
            .post("/simulacoes")
       .then()           
       		.statusCode(400)
       		.body("erros.email", equalTo("E-mail não deve ser vazio"));		
	}
	
	@Test
	@DisplayName("Valida se o valor foi informado ao incluir uma simulação")
	public void validaSeValorFoiInformadoTest() {		
		given()			
			.contentType(ContentType.JSON)
	       	.body(" {\n"  	       		       			
	       			+ "  \"nome\": \"Pessoa\",\r\n"
	       			+ "  \"cpf\": \"123456789\",\r\n" 	
	       			+ "  \"email\": \"desafio@mail.com.br\",\r\n"
	       			+ "  \"parcelas\": 2,\r\n"
	       			+ "  \"seguro\": false\r\n"
	       			+ "}")
	   .when()
            .post("/simulacoes")
       .then()           
       		.statusCode(400)
       		.body("erros.valor", equalTo("Valor não pode ser vazio"));		
	}
	
	@Test
	@DisplayName("Valida se a parcela foi informada ao incluir uma simulação")
	public void validaSeParcelaFoiInformadaTest() {		
		given()			
			.contentType(ContentType.JSON)
	       	.body(" {\n"  	       		       			
	       			+ "  \"nome\": \"Pessoa\",\r\n"
	       			+ "  \"cpf\": \"37996798044\",\r\n" 	
	       			+ "  \"email\": \"desafio@mail.com\",\r\n"
	       			+ "  \"valor\": 1000,\r\n"
	       			+ "  \"seguro\": true\r\n"
	       			+ "}")
	   .when()
            .post("/simulacoes")
       .then()           
       		.statusCode(400)
       		.body("erros.parcelas", equalTo("Parcelas não pode ser vazio"));		
	}
	
	@Test
	@DisplayName("Valida se o seguro foi informado ao incluir uma simulação")
	public void validaSeSeguroFoiInformadoTest() {				
			 
		// Esse teste está quebrando, pois a API não está validando corretamente se o seguro foi informado			
		
		given()			
			.contentType(ContentType.JSON)
	       	.body(" {\n"  	       		       			
	       			+ "  \"nome\": \"Pessoa\",\r\n"
	       			+ "  \"cpf\": \"3799679804437996798044\",\r\n" 	
	       			+ "  \"email\": \"desafio@mail.com\",\r\n"
	       			+ "  \"valor\": 1600,\r\n"
	       			+ "  \"parcelas\": 5\r\n"
	       			+ "}")
	   .when()
            .post("/simulacoes")
       .then()           
       		.statusCode(400)
       		.body("erros.seguro", equalTo("O seguro deve ser informado"));		
	}	
	
	@Test
	@DisplayName("Valida se foi informado um CPF válido")
	public void validaSeCPFInformadoValidoTest() {				
			 
		/*
		 * A API obriga informar um CPF, porém não existe nenhuma validação em cima dele. 
		 * Permite incluir letras, caracteres especiais e combinações de números que não são válidos para um CPF,
		 * inclusive aceita um dado "vazio".
		 */
		
		given()			
			.contentType(ContentType.JSON)
	       	.body(" {\n"  	       		       			
	       			+ "  \"nome\": \"Pessoa\",\r\n"
	       			+ "  \"cpf\": \"999.999.999-99\",\r\n" 	
	       			+ "  \"email\": \"desafio@mail.com\",\r\n"
	       			+ "  \"valor\": 1600,\r\n"
	       			+ "  \"parcelas\": 5,\r\n"
	       			+ "  \"seguro\": true\r\n"
	       			+ "}")
	   .when()
            .post("/simulacoes")
       .then()           
       		.statusCode(400)
       		.body("erros.cpf", equalTo("O CPF informado é inválido"));		
	}
		
	
	@Test
	@DisplayName("Valida se o CPF informado já foi utilizado em outra simulação")
	public void validaCPFInformadoJaFoiUtilizadoTest() {
		
		/* Deveria retornar HTTP Status 409 e a mensagem "CPF já existente", 
		 * porém está retornando HTTP Status 400 e a mensagem "CPF duplicado"
		 */

		given()			
			.contentType(ContentType.JSON)
	       	.body(" {\n"  	       		       			
	       			+ "  \"nome\": \"Ciclanos\",\r\n"
	       			+ "  \"cpf\": \"17822386034\",\r\n" 	
	       			+ "  \"email\": \"cicla@mail.com\",\r\n"
	       			+ "  \"valor\": 6000,\r\n"
	       			+ "  \"parcelas\": 2,\r\n"
	       			+ "  \"seguro\": true\r\n"
	       			+ "}")
	   .when()
            .post("/simulacoes")
       .then()           
       		.statusCode(409)
       		.body("mensagem", equalTo("CPF já existente"));		
	}	
	
	@Test
	@DisplayName("Valida quantidade de caracteres no campo Nome")
	public void validaQuantidadeCaracteresNoCampoNomeTest() {			
		
		//* O campo "Nome" tem limite para 255 caracteres no banco de dados, porém não há validação na API, 
		//* retornando assim uma exceção no log da aplicação e status code 200 OK
		
		given()			
			.contentType(ContentType.JSON)
	       	.body(" {\n"  	       		       			
	       			+ "  \"nome\": \"Teste de quantidade de caracteres Teste 213 de quantidade de caracteres Teste 213 de quantidade de"
	       			+ " caracteres Teste 213 de quantidade de caracteres Teste 213 de quantidade de caracteres Teste 213 de quantidade" 
	       			+ " de caracteres Teste 213 de quantidade de caracteres\",\r\n"
	       			+ "  \"cpf\": \"999.999.999-99\",\r\n" 	
	       			+ "  \"email\": \"desafio@mail.com\",\r\n"
	       			+ "  \"valor\": 1600,\r\n"
	       			+ "  \"parcelas\": 5,\r\n"
	       			+ "  \"seguro\": true\r\n"
	       			+ "}")
	   .when()
            .post("/simulacoes")
       .then()           
       		.statusCode(400)
       		.body("erros.nome", equalTo("O nome deve ter até 255 caracteres"));		
	}
	
	@Test
	@DisplayName("Valida se o e-mail informado é válido")
	public void validaSeEmailInformadoValidoTest() {		
		
		/* Parece que existe mais de uma mensagem de validação para o verificar se o e-mail é válido, 
		*  pois as vezes retorna a mensagem "E-mail deve ser um e-mail válido" e em outras "não é um endereço de e-mail"
		*/
		
		given()			
			.contentType(ContentType.JSON)
	       	.body(" {\n"  	       		       			
	       			+ "  \"nome\": \"Pessoa\",\r\n"
	       			+ "  \"cpf\": \"37996798044\",\r\n" 	
	       			+ "  \"email\": \"desafio@algo\",\r\n"
	       			+ "  \"valor\": 1600,\r\n"
	       			+ "  \"parcelas\": 10,\r\n"
	       			+ "  \"seguro\": false\r\n"
	       			+ "}")
	   .when()
            .post("/simulacoes")
       .then()           
       		.statusCode(400)
       		.body("erros.email", equalTo("E-mail deve ser um e-mail válido"));		
	}
	
	@Test
	@DisplayName("Valida se o valor informado é menor que R$1.000 na simulação")
	public void validaValorMenorQue1000Test() {		
		
		// Deveria aceitar apenas valores maiores ou iguais a R$1000,00, porém a aplicação está aceitando valores menores.		
		
		given()			
			.contentType(ContentType.JSON)
	       	.body(" {\n"  	       		       			
	       			+ "  \"nome\": \"Pessoa com valor baixo\",\r\n"
	       			+ "  \"cpf\": \"53664602048\",\r\n" 	
	       			+ "  \"email\": \"desafio@algo.com\",\r\n"
	       			+ "  \"valor\": 75.00,\r\n"
	       			+ "  \"parcelas\": 6,\r\n"
	       			+ "  \"seguro\": true\r\n"
	       			+ "}")
	   .when()
            .post("/simulacoes")
       .then()           
       		.statusCode(400)
       		.body("erros.valor", equalTo("O valor mínimo aceito para simulação é de R$ 1000,00"));		
	}
	
	@Test
	@DisplayName("Valida se o valor informado é maior que R$40.000 na simulação")
	public void validaValorMaiorQue40000Test() {		
		given()			
			.contentType(ContentType.JSON)
	       	.body(" {\n"  	       		       			
	       			+ "  \"nome\": \"Pessoa com valor alto\",\r\n"
	       			+ "  \"cpf\": \"14913205080\",\r\n" 	
	       			+ "  \"email\": \"desafio@algo.com\",\r\n"
	       			+ "  \"valor\": 45000,\r\n"
	       			+ "  \"parcelas\": 2,\r\n"
	       			+ "  \"seguro\": true\r\n"
	       			+ "}")
	   .when()
            .post("/simulacoes")
       .then()           
       		.statusCode(400)
       		.body("erros.valor", equalTo("Valor deve ser menor ou igual a R$ 40.000"));		
	}
	
	@Test
	@DisplayName("Valida se o mínimo de parcelas é 2 ao fazer uma simulação")
	public void validaNumeroMinimoParcelasTest() {		
		given()			
			.contentType(ContentType.JSON)
	       	.body(" {\n"  	       		       			
	       			+ "  \"nome\": \"Pessoa Legal\",\r\n"
	       			+ "  \"cpf\": \"89557338016\",\r\n" 	
	       			+ "  \"email\": \"desafio@gmail.com\",\r\n"
	       			+ "  \"valor\": 5098,\r\n"
	       			+ "  \"parcelas\": 1,\r\n"
	       			+ "  \"seguro\": false\r\n"
	       			+ "}")
	   .when()
            .post("/simulacoes")
       .then()           
       		.statusCode(400)
       		.body("erros.parcelas", equalTo("Parcelas deve ser igual ou maior que 2"));		
	}
	
	@Test
	@DisplayName("Valida se máximo de parcelas é 48 ao fazer uma simulação")
	public void validaNumeroMaximoParcelasTest() {		
		
		// Deveria aceitar no máximo 48 parcelas, porém está aceitando simular com mais.
		
		given()			
			.contentType(ContentType.JSON)
	       	.body(" {\n"  	       		       			
	       			+ "  \"nome\": \"Pessoa Legal\",\r\n"
	       			+ "  \"cpf\": \"23532872048\",\r\n" 	
	       			+ "  \"email\": \"desafio@gmail.com\",\r\n"
	       			+ "  \"valor\": 5000,\r\n"
	       			+ "  \"parcelas\": 49,\r\n"
	       			+ "  \"seguro\": false\r\n"
	       			+ "}")
	   .when()
            .post("/simulacoes")
       .then()           
       		.statusCode(400)
       		.body("erros.parcelas", equalTo("Parcelas deve ser igual ou menor que 48"));		
	}
}
