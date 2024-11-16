package dev.susana.cuentabancaria.models;

public class CuentaCorriente extends Cuenta{
	
	private float sobregiro = 0;

    public CuentaCorriente(float saldo, float tasaAnual) {
        super(saldo, tasaAnual);
      
        
    }

    @Override
    public void retirar(float cantidad) {
        if (cantidad <= saldo) {
            super.retirar(cantidad);
            
        } else {
            sobregiro = cantidad - saldo;
            numeroRetiros++;
            System.out.println("Retiro realizado. Saldo actual: -" + sobregiro);
            
        }
    }

    @Override
    public void consignar(float cantidad) {
        if (sobregiro > 0) {
            if (cantidad <= sobregiro) {
                sobregiro -= cantidad;
            } else {
                saldo = cantidad - sobregiro;
                sobregiro = 0;
                System.out.println("Ingreso realizado. Saldo actual: -" + saldo);
            }
        } else {
            super.consignar(cantidad);
        }
    }

    @Override
    public void extractoMensual() {
        super.extractoMensual();

        if (sobregiro > 0) {
            System.out.println("Nota: El sobregiro no genera intereses.");
        }
    }

    public String imprimir() {
        return "Cuenta corriente:" +
                "\nNúmero de transacciones: " + (numeroConsignaciones + numeroRetiros) +
                "\nValor de sobregiro: " + sobregiro +
                 super.imprimir();
              
    }
	
}
