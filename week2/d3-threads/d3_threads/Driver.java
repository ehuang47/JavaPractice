package d3_threads;

public class Driver {
    public static void main(String[] args){
        Object key1 = new Object();
        Object key2 = new Object();
        Thread t8 = new Thread( () -> {
            synchronized (key1) {
                System.out.println("t8 has key 1.");

                try {
                    Thread.sleep(5000); // simulate 5s of work
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

                // waits for key2, but t9 holds onto it
                synchronized (key2) {
                    System.out.println("t8 has key 2");
                }
            }
        });
        Thread t9 = new Thread( () -> {
            synchronized (key2) {
                System.out.println("t9 has key 2.");

                // waits for key1, but t8 holds onto it
                synchronized (key1) {
                    System.out.println("t9 has key 1");
                }
            }
        });
        t8.start();
        t9.start();

        /* There's no way to explicitly give up the lock in synchronized blocks.
        S1: if t9 is changed to wait for key1 first, then it blocks until t8 finishes its 5s of work
        */
    }
}
