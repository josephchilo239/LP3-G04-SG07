package Actividades;
import java.util.Scanner;

abstract class Cuenta {
    protected int numeroCuenta;
    protected double saldo;

    public Cuenta(int numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    public void depositar(double monto) {
        saldo += monto;
        System.out.println("Depósito realizado. Saldo actual: " + saldo);
    }

    public boolean retirar(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            System.out.println("Retiro realizado. Saldo actual: " + saldo);
            return true;
        } else {
            System.out.println("Fondos insuficientes.");
            return false;
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public abstract void consultar();
}

class CuentaAhorro extends Cuenta {
    private double tasaInteres;
    private double saldoMinimo;

    public CuentaAhorro(int numeroCuenta, double saldoInicial, double tasaInteres) {
        super(numeroCuenta, saldoInicial);
        this.tasaInteres = tasaInteres;
        this.saldoMinimo = saldoInicial;
    }

    @Override
    public boolean retirar(double monto) {
        boolean exito = super.retirar(monto);
        if (exito && saldo < saldoMinimo) {
            saldoMinimo = saldo;
        }
        return exito;
    }

    @Override
    public void consultar() {
        double interes = saldoMinimo * tasaInteres / 100;
        depositar(interes);
        saldoMinimo = saldo; 
        System.out.println("Intereses capitalizados. Saldo actual: " + saldo);
    }
}

class CuentaCorriente extends Cuenta {
    private int retiros;
    private static final int RETIROS_GRATIS = 3;
    private static final double TARIFA = 3.0;

    public CuentaCorriente(int numeroCuenta, double saldoInicial) {
        super(numeroCuenta, saldoInicial);
        this.retiros = 0;
    }

    @Override
    public boolean retirar(double monto) {
        boolean exito = super.retirar(monto);
        if (exito) {
            retiros++;
            if (retiros > RETIROS_GRATIS) {
                saldo -= TARIFA;
                System.out.println("Se aplicó una tarifa de " + TARIFA + ". Saldo actual: " + saldo);
            }
        }
        return exito;
    }

    @Override
    public void consultar() {
        retiros = 0;
        System.out.println("Reiniciado el contador de retiros. Saldo actual: " + saldo);
    }
}

public class BancoApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Cuenta[] cuentas = new Cuenta[10];
        for (int i = 0; i < 5; i++) {
            cuentas[i] = new CuentaCorriente(1000 + i, 500);
        }
        for (int i = 5; i < 10; i++) {
            cuentas[i] = new CuentaAhorro(2000 + i, 1000, 2.0);
        }

        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== MENÚ BANCO ===");
            System.out.println("D) Depositar");
            System.out.println("R) Retirar");
            System.out.println("C) Consultar");
            System.out.println("S) Salir");
            System.out.print("Elija opción: ");
            char opcion = sc.next().toUpperCase().charAt(0);

            switch (opcion) {
                case 'D':
                    System.out.print("Ingrese número de cuenta: ");
                    int numDep = sc.nextInt();
                    System.out.print("Monto a depositar: ");
                    double montoDep = sc.nextDouble();
                    buscarCuenta(cuentas, numDep).depositar(montoDep);
                    break;
                case 'R':
                    System.out.print("Ingrese número de cuenta: ");
                    int numRet = sc.nextInt();
                    System.out.print("Monto a retirar: ");
                    double montoRet = sc.nextDouble();
                    buscarCuenta(cuentas, numRet).retirar(montoRet);
                    break;
                case 'C':
                    System.out.println("\n-- Consultando todas las cuentas --");
                    for (Cuenta c : cuentas) {
                        c.consultar();
                        System.out.println("Cuenta " + c.getNumeroCuenta() + ": Saldo=" + c.getSaldo());
                    }
                    break;
                case 'S':
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        sc.close();
    }

    public static Cuenta buscarCuenta(Cuenta[] cuentas, int numero) {
        for (Cuenta c : cuentas) {
            if (c.getNumeroCuenta() == numero) {
                return c;
            }
        }
        throw new IllegalArgumentException("No se encontró la cuenta " + numero);
    }
}
