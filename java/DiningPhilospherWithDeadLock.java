package java;
class Philospher implements Runnable {
    Object lf, rf;

    Philospher(Object lf, Object rf) {
        this.lf = lf;
        this.rf = rf;
    }

    void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(1000);
    }

    public void run() {
        try {
            while (true) {
                doAction("  -- Thinking");
                synchronized (lf) {
                    doAction(" -- Picked up left fork");
                    synchronized (rf) {
                        doAction(" -- Picked up right fork -- eating");
                        doAction(" -- put down right fork");
                    }
                    doAction(" -- Put down left fork. Back to thinking");
                }
            }
        } catch (Exception e) {

        }
    }
}

public class DiningPhilospherWithDeadLock {
    public static void main(String args[]) {
        Philospher p[] = new Philospher[5];
        Object forks[] = new Object[p.length];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }
        for (int i = 0; i < p.length; i++) {
            Object lf = forks[i];
            Object rf = forks[(i + 1) % forks.length];
            p[i] = new Philospher(lf, rf);
            Thread t = new Thread(p[i], "Philospher " + (i + 1));
            t.start();
        }
    }
}
