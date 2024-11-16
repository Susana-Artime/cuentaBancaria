package dev.susana.cuentabancaria.models;

public class CuentaAhorro extends Cuenta{

	private boolean activa;

    public CuentaAhorro(float saldoInicial, float tasaAnual) {

        super(saldoInicial, tasaAnual);
        this.activa = saldoInicial >= 10000;
    }

    @Override
    public void consignar(float cantidad) {
        if (activa) {
            super.consignar(cantidad);
        } else {
            System.out.println("La cuenta está inactiva. No se puede consignar dinero.");
        }
        verificarEstadoCuenta();
    }

    @Override
    public void retirar(float cantidad) {
        if (activa) {
            super.retirar(cantidad);
        } else {
            System.out.println("La cuenta está inactiva. No se puede retirar dinero.");
        }
        verificarEstadoCuenta();
    }

    @Override
    public void extractoMensual() {
        if (numeroRetiros > 4) {
            comisionMensual += (numeroRetiros - 4) * 1000;
        }
        super.extractoMensual();
        verificarEstadoCuenta();
    }

    private void verificarEstadoCuenta() {
        activa = saldo >= 10000;
    }

    @Override
    public String imprimir() {
        return "Cuenta de ahorro:" +  
                "\nNúmero de transacciones: " + (numeroConsignaciones + numeroRetiros) +
				"\nEstado de la cuenta: " + (activa ? "Activa" : "Inactiva") +
                super.imprimir();
    }
}

