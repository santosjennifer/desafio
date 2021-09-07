package br.com.sicredi.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.sicredi.test.restricoes.ConsultarRestricoesCPFTest;

@RunWith(Suite.class)
@SuiteClasses({ ConsultarRestricoesCPFTest.class })

public class RestricoesSuiteTest {

}
