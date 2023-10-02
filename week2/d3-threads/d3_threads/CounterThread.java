package d3_threads;

public class CounterThread {
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CounterThread(){}

    public void increment() {
        for(int i = 0; i < 10000; i++){
            count++;
        }
    }

    public void decrement() {
        for(int i = 0; i < 10000; i++){
            count--;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        CounterThread counter = new CounterThread();
        Thread t1 = new Thread(counter::increment);
        Thread t2 = new Thread(counter::decrement);
        t1.start();
        t2.start();

        /* wait for both to finish before seeing the results. we expect 0, but... -1404, -916, -195, -29
         * The different threads access the same instance and interleave operations, sometimes reading the same value
         * count and incrementing and decrementing simultaneously.
         */
        t1.join();
        t2.join();
        System.out.println(counter.getCount());

        // We can prevent race conditions by starting t2 after t1.join finishes, but we lose the parallelism benefits.
        // Therefore, we can try using synchronized to lock access to the count operations.

//        CounterThread counter = new CounterThread();
//        Thread t1 = new Thread(() -> {
//            synchronized (counter) {
//                counter.increment();
//            }
//        });
//        Thread t2 = new Thread(() -> {
//            synchronized (counter) {
//                counter.decrement();
//            }
//        });
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();
//        System.out.println(counter.getCount());
    }
}
