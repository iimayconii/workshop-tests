package br.com.crptecnologia.workshop.calculadora;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.crptecnologia.workshop.calculator.Calculadora;

public class CalculadoraTest {

    @Test
    public void testaSomaEntreInteiros(){
        Calculadora calculadora = new Calculadora();
        Assertions.assertThat(5)
                .isEqualTo(calculadora.somar(2, 3));
    }
}
