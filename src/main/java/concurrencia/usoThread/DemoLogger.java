package concurrencia.usoThread;

/* Demostración del uso de hilos (threads) en Java para registrar mensajes de log de manera concurrente.
   Se utiliza la palabra clave 'synchronized' para asegurar que las operaciones de log sean atómicas
   y evitar que los mensajes se mezclen cuando son escritos por diferentes hilos.
   Dos hilos simulan tareas que generan mensajes de log.
   Probar sin 'synchronized' puede llevar a que los mensajes se mezclen y sean difíciles de leer.
*/
class Logger {
    public synchronized void log(String msg) {
    //public void log(String msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }
}

public class DemoLogger {
    public static void main(String[] args) {
        Logger logger = new Logger();

        Runnable tarea = () -> {
            for (int i = 0; i < 5; i++) {
                logger.log("mensaje " + i);
            }
        };

        Thread t1 = new Thread(tarea, "Hilo-A");
        Thread t2 = new Thread(tarea, "Hilo-B");

        t1.start();
        t2.start();
    }
}
