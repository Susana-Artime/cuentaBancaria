package dev.susana.cuentabancaria.models;

public class Cuenta {

    protected float saldo;
    protected int numeroConsignaciones = 0;
    protected int numeroRetiros = 0;
    protected float tasaAnual;
    protected float comisionMensual = 0;
    

    public Cuenta(float saldoInicial, float tasaAnual) {
        this.saldo = saldoInicial;
        this.tasaAnual = tasaAnual;
    }


    public void consignar(float cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            numeroConsignaciones++;
            System.out.println("Ingreso realizado. Saldo actual: " + saldo);
        } else {
            System.out.println("La cantidad a ingresar debe ser positivo.");
        }
    }

    public void retirar(float cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            numeroRetiros++;
            System.out.println("Retiro realizado. Saldo actual: " + saldo);
        } else if (cantidad > saldo) {
            System.out.println("La cantidad a retirar excede el saldo disponible.");
        } else {
            System.out.println("La cantidad a retirar debe ser positivo.");
        }
    }

    public void calcularInteresMensual() {
        float tasaMensual = tasaAnual / 12 / 100;
        float interesMensual = saldo * tasaMensual;
        saldo += interesMensual;
        System.out.println("Interés mensual aplicado:" + interesMensual +". Saldo actual: " + saldo);
    }

    public void extractoMensual() {
        saldo -= comisionMensual;
        calcularInteresMensual();
        System.out.println("Extracto mensual procesado. Saldo actual: " + saldo);
    }

    public String imprimir() {
        return "\nSaldo: " + saldo +
               "\nNúmero de consignaciones: " + numeroConsignaciones +
               "\nNúmero de retiros: " + numeroRetiros +
               "\nTasa Anual: " + tasaAnual +
               "\nComisión Mensual: " + comisionMensual +
               "\n-------------------------------------";


    }


}
