package dev.susana.cuentabancaria.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CuentaAhorroTest {

    private CuentaAhorro cuentaAhorroActiva;
    private CuentaAhorro cuentaAhorroInactiva;

    @BeforeEach
    void setUp() {
        
        cuentaAhorroActiva= new CuentaAhorro(15000, 10);
        cuentaAhorroInactiva = new CuentaAhorro(5000, 10);
    }

    @Test
    void testCuentaInicialmenteActiva() {

        assertThat(cuentaAhorroActiva.imprimir(), containsString("Estado de la cuenta: Activa"));
    }


@Test
    void testConsignarCuandoCuentaActiva() {
        cuentaAhorroActiva.consignar(5000);
        assertThat(cuentaAhorroActiva.saldo, is(20000.0f));
        assertThat(cuentaAhorroActiva.numeroConsignaciones, is(1));
        assertThat(cuentaAhorroActiva.imprimir(), containsString("Estado de la cuenta: Activa"));
    }

    @Test
    void testConsignarCuandoCuentaInactiva() {
        
        cuentaAhorroInactiva.consignar(4000);
        assertThat(cuentaAhorroInactiva.saldo, is(5000.0f)); 
        assertThat(cuentaAhorroInactiva.numeroConsignaciones, is(0)); 
        assertThat(cuentaAhorroInactiva.imprimir(), containsString("Estado de la cuenta: Inactiva"));
    }

    @Test
    void testRetirarCuandoCuentaActiva() {
        cuentaAhorroActiva.retirar(5000);
        assertThat(cuentaAhorroActiva.saldo, is(10000.0f));
        assertThat(cuentaAhorroActiva.numeroRetiros, is(1));
        assertThat(cuentaAhorroActiva.imprimir(), containsString("Estado de la cuenta: Activa"));
    }

    @Test
    void testRetirarCuandoCuentaInactiva() {
        cuentaAhorroInactiva.retirar(2000);
        assertThat(cuentaAhorroInactiva.saldo, is(5000.0f)); // Sin cambios
        assertThat(cuentaAhorroInactiva.numeroRetiros, is(0)); // Sin transacciones
        assertThat(cuentaAhorroInactiva.imprimir(), containsString("Estado de la cuenta: Inactiva"));
    }

    @Test
    void testExtractoMensualSinRetirosExtras() {
        cuentaAhorroActiva.extractoMensual();
        float tasaMensual = 10.0f / 12.0f / 100.0f;
        float saldoEsperado = 15000 + (15000 * tasaMensual);
        assertThat(cuentaAhorroActiva.saldo, is(saldoEsperado));
        assertThat(cuentaAhorroActiva.comisionMensual, is(0.0f));
    }

    @Test
    void testExtractoMensualConRetirosExtras() {
        
        cuentaAhorroActiva.retirar(1000);
        cuentaAhorroActiva.retirar(1000);
        cuentaAhorroActiva.retirar(1000);
        cuentaAhorroActiva.retirar(1000);
        cuentaAhorroActiva.retirar(1000); 

        cuentaAhorroActiva.extractoMensual();
        float tasaMensual = 10.0f / 12.0f / 100.0f;
        float saldoEsperado = (15000 - 5000 - 1000) + ((15000 - 5000 - 1000) * tasaMensual);
        assertThat(cuentaAhorroActiva.saldo, is(saldoEsperado));
        assertThat(cuentaAhorroActiva.comisionMensual, is(1000.0f));
    }


    @Test
    void testImprimir() {
        String resultado = cuentaAhorroActiva.imprimir();
        assertThat(resultado, containsString("Cuenta de ahorro:"));
        assertThat(resultado, containsString("Número de transacciones: 0"));
        assertThat(resultado, containsString("Estado de la cuenta: Activa"));
        assertThat(resultado, containsString("Saldo: 15000.0"));
    }

}


