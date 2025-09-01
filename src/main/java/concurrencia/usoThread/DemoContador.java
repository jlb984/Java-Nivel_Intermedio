package concurrencia.usoThread;

/* Demostración del uso de hilos (threads) en Java para incrementar un contador de manera concurrente.
    Cada hilo incrementa el contador 1000 veces. Al final, se espera que el valor del contador sea 10000.
    Se utiliza la palabra clave 'synchronized' para asegurar que las operaciones de incremento y lectura del contador
    sean atómicas y evitar condiciones de carrera.
    Ejecutar este código varias veces debería siempre producir el mismo resultado final (10000).
    Si no se usara 'synchronized', el resultado podría variar debido a condiciones de carrera.
 */

    class Contador {
        private int count = 0;

        public synchronized void incrementar() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
    }

    public class DemoContador {
        public static void main(String[] args) throws InterruptedException {
            Contador c = new Contador();

            // Creamos 10 hilos, cada uno incrementa 1000 veces
            Thread[] hilos = new Thread[10];
            for (int i = 0; i < 10; i++) {
                hilos[i] = new Thread(() -> {
                    for (int j = 0; j < 1000; j++) {
                        System.out.println("Incrementando: " + (j + 1) + " en " + Thread.currentThread().getName());
                        c.incrementar();
                    }
                });
                hilos[i].start();
            }

            // Esperamos a que todos terminen
            for (Thread t : hilos) t.join();

            System.out.println("Total esperado: 10000");
            System.out.println("Total real: " + c.getCount());
        }
    }

