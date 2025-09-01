package concurrencia.usoThread;

/* Simulación de una cuenta bancaria con operaciones concurrentes de retiro y depósito.
   Se utiliza la palabra clave 'synchronized' para asegurar que las operaciones de retiro y depósito
   sean atómicas y evitar condiciones de carrera.
   Dos hilos simulan clientes que realizan retiros y depósitos en la misma cuenta bancaria.
   Probar sin 'synchronized' puede llevar a inconsistencias en el saldo de la cuenta.
*/
class CuentaBancaria {
    private int saldo = 1000;

    //public synchronized void retirar(int monto) {
    public  void retirar(int monto) {
        if (saldo >= monto) {
            saldo -= monto;
            System.out.println(Thread.currentThread().getName() + " retiró " + monto + ". Saldo: " + saldo);
        } else {
            System.out.println(Thread.currentThread().getName() + " no tiene suficiente saldo para retirar " + monto + ". Saldo: " + saldo);
        }
    }

    public synchronized void depositar(int monto) {
        saldo += monto;
        System.out.println(Thread.currentThread().getName() + " depositó " + monto + ". Saldo: " + saldo);
    }
}

public class DemoBanco {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria();

        Runnable tarea = () -> {
            for (int i = 0; i < 33; i++) {
                cuenta.retirar(30);
                if(i%10 == 0) {
                    cuenta.depositar(100);
                }
            }
        };

        Thread t1 = new Thread(tarea, "Cliente 1");
        Thread t2 = new Thread(tarea, "Cliente 2");

        t1.start();
        t2.start();
    }
}
