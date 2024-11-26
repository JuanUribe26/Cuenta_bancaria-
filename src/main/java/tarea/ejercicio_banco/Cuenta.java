package tarea.ejercicio_banco;

//Author: Jhon Díaz
//Abstract: Ejercicio banco

public class Cuenta {// Clase de la cuenta bancaria
    private String numeroCuenta;// Numero de cuenta
    private double saldo; // Saldo actual de la cuenta

    public Cuenta(String numeroCuenta, double saldoInicial) {//Constructor
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    public String getNumeroCuenta() {//Metodo getter para el numero de cuenta
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {//Metodo setter para el numero de cuenta
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {//Metodo getter para el saldo
        return saldo;
    }

    public void setSaldo(double saldo) {//Metodo setter para el saldo
        this.saldo = saldo;
    }

    // Métodos adicionales
    public void depositar(double cantidad) {
        if (cantidad > 0) {
            this.saldo += cantidad;
        }
    }

    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= this.saldo) {
            this.saldo -= cantidad;
            return true;
        }
        return false;
    }
}