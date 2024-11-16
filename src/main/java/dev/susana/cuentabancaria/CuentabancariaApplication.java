package dev.susana.cuentabancaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.susana.cuentabancaria.models.CuentaAhorro;
import dev.susana.cuentabancaria.models.CuentaCorriente;

@SpringBootApplication
public class CuentabancariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuentabancariaApplication.class, args);

        CuentaAhorro ahorro = new CuentaAhorro(11000, 0.05f);
        ahorro.consignar(5000);
        ahorro.retirar(2000);
        ahorro.extractoMensual();
        System.out.println(ahorro.imprimir());

    
        CuentaCorriente corriente = new CuentaCorriente(2000, 0.03f);
        corriente.retirar(2500);
        corriente.consignar(200);
        corriente.extractoMensual();
        System.out.println(corriente.imprimir());
       
        
    }

}


