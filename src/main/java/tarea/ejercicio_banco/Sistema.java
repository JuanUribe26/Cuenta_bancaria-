package tarea.ejercicio_banco;

//Author: Jhon Díaz
//Abstract: Ejercicio banco

import java.util.Scanner;
import java.util.InputMismatchException;

public class Sistema {//Se crea la clase principal del sistema que veran los usuarios
    private static Servicio servicio = new Servicio();//Se crea la insstancia de "servicio"
    private static Scanner scanner = new Scanner(System.in);//Se usa scanner para tomar inputs de la oonsola por los usuarios

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {//Bucle para que el programa se siga ejecutando hasta que el usuario decida
            try {
                //Menu
                System.out.println("\n----- m e n u -----");
                System.out.println("|1. Crear cuenta  |");
                System.out.println("|2. Deposito      |");
                System.out.println("|3. Retiro        |");
                System.out.println("|4. Transferencia |");
                System.out.println("|5. Revisar saldo |");
                System.out.println("|6. Salir         |");
                System.out.println("\n-------------------");
                System.out.print("En que podemos ayudarlo hoy?");
                int choice = scanner.nextInt();
                
                switch (choice) {//Se implementa un switch para que llame a los diferentes metodos
                    case 1 -> crearCuenta();
                    case 2 -> deposito();
                    case 3 -> retiro();
                    case 4 -> transferencia();
                    case 5 -> revisarSaldo();
                    case 6 -> exit = true;
                    default -> System.out.println("Opcion no existente, ingrese otra vez");//Se prevee un error del usuario entonces se crea esto para informarle de su error
                }
            } catch (InputMismatchException e) {
                System.out.println("Porfavor, solo números");//En caso que el usuario ingrese algo distinto a un numero
                scanner.next();
            }
        }

        System.out.println("Gracias por escogernos!");//Mensaje de salida
    }

    private static void crearCuenta() {// Se crea el metodo para crear una cuenta y asignarle un saldo
        System.out.print("Ingrese un numero de cuenta: ");
        String noCuenta = scanner.next();
        System.out.print("Ingrese su valor inicial: $");
        double saldoInicial = scanner.nextDouble();

        if (saldoInicial < 0) {
            System.out.println("Porfavor solo numeros positivos");//Para prevenir errores solo se permiten numeros positivos
            return;
        }

        servicio.crearCuenta(noCuenta, saldoInicial);
    }

    private static void deposito() {// Metodo para depositar dinero
        System.out.print("Ingrese un numero de cuenta: ");
        String noCuenta = scanner.next();
        System.out.print("Ingrese la cantidad a depositar: $");
        double cantidad = scanner.nextDouble();

        if (cantidad <= 0) {
            System.out.println("Porfavor solo numeros positivos.");//Para prevenir errores solo se permiten numeros positivos
            return;
        }

        servicio.deposito(noCuenta, cantidad);
    }

    private static void retiro() {// Metodo para retirar dinero
        System.out.print("Ingrese un numero de cuenta: ");
        String noCuenta = scanner.next();
        System.out.print("Ingrese la cantidad a retirar: $");
        double cantidad = scanner.nextDouble();

        if (cantidad <= 0) {
            System.out.println("Porfavor solo numeros positivos.");//Para prevenir errores solo se permiten numeros positivos
            return;
        }

        servicio.retiro(noCuenta, cantidad);
    }

    private static void transferencia() {// Metodo para transferir dinero
        System.out.print("Ingrese el numero de cuenta del remitente: ");
        String enviaNoCuenta = scanner.next();
        System.out.print("Ingrese el numero de cuneta del destinatario: ");
        String recibeNoCuenta = scanner.next();
        System.out.print("Ingrese la cantidad a transferir: $");
        double cantidad = scanner.nextDouble();

        if (cantidad <= 0) {
            System.out.println("Porfavor solo numeros positivos.");//Para prevenir errores solo se permiten numeros positivos
            return;
        }

        servicio.transferencia(enviaNoCuenta, recibeNoCuenta, cantidad);
    }

    private static void revisarSaldo() {// Metodo para revisar el saldo
        System.out.print("Ingrese numero de cuenta a consultar: ");
        String noCuenta = scanner.next();

        servicio.revisarSaldo(noCuenta);
    }
}