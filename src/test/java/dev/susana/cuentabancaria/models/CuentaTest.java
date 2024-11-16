package dev.susana.cuentabancaria.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CuentaTest {

    private Cuenta cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new Cuenta(5000, 12);
    }

   @Test
    void testConsignarCantidadPositiva() {
        cuenta.consignar(1000);
        assertThat(cuenta.saldo, is(6000.0f));
        assertThat(cuenta.numeroConsignaciones, is(1));
    }

    @Test
    void testConsignarCantidadNegativa() {
        cuenta.consignar(-1000);
        assertThat(cuenta.saldo, is(5000.0f));
        assertThat(cuenta.numeroConsignaciones, is(0));
    }

    
    @Test
    void testRetirarCantidadValida() {
        cuenta.retirar(1000);
        assertThat(cuenta.saldo, is(4000.0f)); 
        assertThat(cuenta.numeroRetiros, is(1)); 
    }
    
    @Test
    void testRetirarCantidadExcedeSaldo() {
        cuenta.retirar(6000);
        assertThat(cuenta.saldo, is(5000.0f)); 
        assertThat(cuenta.numeroRetiros, is(0)); 
    }

    @Test
    void testRetirarCantidadNegativa() {
        cuenta.retirar(-500);
        assertThat(cuenta.saldo, is(5000.0f));
        assertThat(cuenta.numeroRetiros, is(0));
    }
    @Test
    void testCalcularInteresMensual() {
        cuenta.calcularInteresMensual();
        float expectedSaldo = 5000 + (5000 * (12 / 12 / 100.0f));
        assertThat(cuenta.saldo, is(expectedSaldo));
    }

    @Test
    void testExtractoMensualSinComision() {
        cuenta.extractoMensual();
        float expectedSaldo = 5000 + (5000 * (12 / 12 / 100.0f));
        assertThat(cuenta.saldo, is(expectedSaldo));
    }

    @Test
    void testExtractoMensualConComision() {
        cuenta.comisionMensual = 500; 
        cuenta.extractoMensual();
        float expectedSaldo = (5000 - 500) + ((5000 - 500) * (12 / 12 / 100.0f));
        assertThat(cuenta.saldo, is(expectedSaldo));
    }



    @Test
    void testImprimir() {

        String resultado = cuenta.imprimir();
        assertThat(resultado, containsString("Saldo: 5000.0"));
        assertThat(resultado, containsString("Número de consignaciones: 0"));
        assertThat(resultado, containsString("Número de retiros: 0"));
        assertThat(resultado, containsString("Tasa Anual: 12.0"));
        assertThat(resultado, containsString("Comisión Mensual: 0.0"));
        
    }

}
