package dev.susana.cuentabancaria.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CuentaCorrienteTest {

    private CuentaCorriente cuentaCorriente;

    @BeforeEach
    void setUp() {
        cuentaCorriente = new CuentaCorriente(5000,10);
    }

    @Test
    void testRetirarDentroDelSaldo() {
        cuentaCorriente.retirar(3000);
        assertThat(cuentaCorriente.saldo, is(2000.0f));
        assertThat(cuentaCorriente.getSobregiro(), is(0.0f));
        assertThat(cuentaCorriente.numeroRetiros, is(1));
    }

    @Test
    void testRetirarConSobregiro() {
        cuentaCorriente.retirar(6000);
        assertThat(cuentaCorriente.saldo, is(0.0f));
        assertThat(cuentaCorriente.getSobregiro(), is(1000.0f));
        assertThat(cuentaCorriente.numeroRetiros, is(1));
    }


    @Test
    void testConsignarReduciendoSobregiroParcial() {
        cuentaCorriente.retirar(6000);
        cuentaCorriente.consignar(500);
        assertThat(cuentaCorriente.saldo, is(0.0f));
        assertThat(cuentaCorriente.getSobregiro(), is(500.0f));
    }

    @Test
    void testConsignarEliminandoSobregiro() {
        cuentaCorriente.retirar(6000);
        cuentaCorriente.consignar(1500);
        assertThat(cuentaCorriente.saldo, is(500.0f));
        assertThat(cuentaCorriente.getSobregiro(), is(0.0f));
    }

    @Test
    void testConsignarCuandoNoHaySobregiro() {
        cuentaCorriente.consignar(2000);
        assertThat(cuentaCorriente.saldo, is(7000.0f));
        assertThat(cuentaCorriente.getSobregiro(), is(0.0f));
        assertThat(cuentaCorriente.numeroConsignaciones, is(1));
    }

    @Test
    void testExtractoMensualSinSobregiro() {
        cuentaCorriente.extractoMensual();
        float tasaMensual = 10.0f / 12.0f / 100.0f;
        float saldoEsperado = 5000 + (5000 * tasaMensual);
        assertThat(cuentaCorriente.saldo, is(saldoEsperado));
        assertThat(cuentaCorriente.comisionMensual, is(0.0f));
        assertThat(cuentaCorriente.getSobregiro(), is(0.0f));
    }

    @Test
    void testExtractoMensualConSobregiro() {
        cuentaCorriente.retirar(6000);
        cuentaCorriente.extractoMensual();
        assertThat(cuentaCorriente.saldo, is(0.0f));
        assertThat(cuentaCorriente.getSobregiro(), is(1000.0f));
        assertThat(cuentaCorriente.comisionMensual, is(0.0f));
    }

    @Test
    void testImprimirSinSobregiro() {
        String resultado = cuentaCorriente.imprimir();
        assertThat(resultado, containsString("Cuenta corriente:"));
        assertThat(resultado, containsString("Número de transacciones: 0"));
        assertThat(resultado, containsString("Valor de sobregiro: 0.0"));
        assertThat(resultado, containsString("Saldo: 5000.0"));
    }

    @Test
    void testImprimirConSobregiro() {
        cuentaCorriente.retirar(6000);
        String resultado = cuentaCorriente.imprimir();
        assertThat(resultado, containsString("Cuenta corriente:"));
        assertThat(resultado, containsString("Número de transacciones: 1"));
        assertThat(resultado, containsString("Valor de sobregiro: 1000.0"));
        assertThat(resultado, containsString("Saldo: 0.0"));
    }
}
