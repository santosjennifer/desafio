package br.com.sicredi.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.sicredi.test.simulacoes.AlterarSimulacoesTest;
import br.com.sicredi.test.simulacoes.ConsultarSimulacoesTest;
import br.com.sicredi.test.simulacoes.IncluirSimulacoesTest;
import br.com.sicredi.test.simulacoes.RemoverSimulacoesTest;

@RunWith(Suite.class)
@SuiteClasses({ AlterarSimulacoesTest.class, 
				ConsultarSimulacoesTest.class, 
				IncluirSimulacoesTest.class,
				RemoverSimulacoesTest.class })

public class SimulacoesSuiteTest {

}
