package concurrencia;

    class Contador {
        private int count = 0;

        public synchronized void incrementar() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
    }

    public class UsoThread {
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

