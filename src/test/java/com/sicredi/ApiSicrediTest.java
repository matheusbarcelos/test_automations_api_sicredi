package com.sicredi;
import com.sicredi.restricoes.RestricoesTest;
import com.sicredi.simulacoes.SimulacoesTest;
import com.sicredi.suport.Url;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static io.restassured.RestAssured.*;


@RunWith(Suite.class)
@Suite.SuiteClasses(
        {RestricoesTest.class,
                SimulacoesTest.class}
)
public class ApiSicrediTest {

}

