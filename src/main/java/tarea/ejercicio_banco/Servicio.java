package tarea.ejercicio_banco;

// Author: Miguel Ortiz.
// Abstract: Ejercicio banco

import java.util.HashMap; // Se importa Hash y Map para guardar datos en llaves para los datos del banco
import java.util.Map;

public class Servicio { // Se crea la clase servicio para el manejo de las cuentas
    private Map<String, Cuenta> cuentas = new HashMap<>(); // Guarda las cuentas con su numero como la llave
    private static final double GMF = 0.004; // Constante que representa el impuesto 4x1000 (GMF)

    public void crearCuenta(String noCuenta, double saldoInicial) { // Crear una nueva cuenta
        if (cuentas.containsKey(noCuenta)) {
            System.out.println("Esta cuenta ya existe."); // En el caso de que se repita el mismo numero
            return;
        }

        if (saldoInicial < 0) {
            System.out.println("Solo numeros positivos por favor."); // Para prevenir errores con negativos
            return;
        }

        cuentas.put(noCuenta, new Cuenta(noCuenta, saldoInicial));
        System.out.println("Cuenta creada con exito!");
    }

    public void deposito(String noCuenta, double cantidad) { // Depositar dinero
        Cuenta cuenta = cuentas.get(noCuenta);

        if (cuenta == null) {
            System.out.println("Cuenta no encontrada."); // En caso de que la cuenta no exista
            return;
        }

        if (cantidad <= 0) {
            System.out.println("Solo numeros positivos por favor."); // Para prevenir errores con negativos
            return;
        }
        
        double impuesto = cantidad * GMF;// Calcular el impuesto GMF (4x1000)
        double montoFinal = cantidad - impuesto; // Se deduce el impuesto del monto depositado

        cuenta.depositar(montoFinal);
        System.out.println("Deposito exitoso! Se aplico un impuesto de $" + impuesto);
        System.out.println("Saldo actual: $" + cuenta.getSaldo());
    }

    public void retiro(String noCuenta, double cantidad) { // Retirar dinero
        Cuenta cuenta = cuentas.get(noCuenta);

        if (cuenta == null) {
            System.out.println("Cuenta no encontrada."); // En caso de que la cuenta no exista
            return;
        }

        if (cantidad <= 0) {
            System.out.println("Solo numeros positivos por favor."); // Para prevenir errores con negativos
            return;
        }

        double impuesto = cantidad * GMF;// Calcular el monto total a retirar (cantidad + impuesto)
        double montoTotal = cantidad + impuesto;

        if (cuenta.retirar(montoTotal)) { // Verifica si hay saldo suficiente para cubrir el monto total
            System.out.println("Retiro exitoso! Se aplico un impuesto de $" + impuesto);
            System.out.println("Saldo actual: $" + cuenta.getSaldo());
        } else {
            System.out.println("Saldo insuficiente. Se requiere $" + montoTotal + " (incluyendo el impuesto).");
        }
    }

    public void transferencia(String enviaNoCuenta, String recibeNoCuenta, double cantidad) { // Transferir dinero
        Cuenta cuentaEmisor = cuentas.get(enviaNoCuenta);
        Cuenta cuentaReceptor = cuentas.get(recibeNoCuenta);

        if (cuentaEmisor == null) {
            System.out.println("La cuenta del remitente no fue encontrada."); // En caso de que la cuenta no exista
            return;
        }

        if (cuentaReceptor == null) {
            System.out.println("La cuenta del destinatario no fue encontrada."); // En caso de que la cuenta no exista
            return;
        }

        if (cantidad <= 0) {
            System.out.println("Solo numeros positivos por favor."); // Para prevenir errores con negativos
            return;
        }

        double impuesto = cantidad * GMF;// Calcular el monto total a deducir (cantidad + impuesto)
        double montoTotal = cantidad + impuesto;

        if (cuentaEmisor.retirar(montoTotal)) { // Verifica si hay saldo suficiente en el emisor
            cuentaReceptor.depositar(cantidad); // El receptor recibe el monto sin impuesto
            System.out.println("Transferencia exitosa! Se aplico un impuesto de $" + impuesto);
            System.out.println("Nuevo saldo del remitente: $" + cuentaEmisor.getSaldo());
            System.out.println("Nuevo saldo del destinatario: $" + cuentaReceptor.getSaldo());
        } else {
            System.out.println("Saldo insuficiente para realizar la transferencia. Se requiere $" + montoTotal + " (incluyendo el impuesto).");
        }
    }

    public void revisarSaldo(String noCuenta) { // Revisar saldo
        Cuenta cuenta = cuentas.get(noCuenta);

        if (cuenta == null) {
            System.out.println("Cuenta no encontrada."); // En caso de que la cuenta no exista
            return;
        }

        System.out.println("Saldo actual: $" + cuenta.getSaldo());
    }
}
