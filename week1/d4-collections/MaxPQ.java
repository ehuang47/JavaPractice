import java.util.PriorityQueue;

public class MaxPQ {
    PriorityQueue<Integer> q;
    public MaxPQ(){
        this.q = new PriorityQueue<Integer>((a,b) -> b-a);
    }

    boolean offer(int someInt) {
        return this.q.offer(someInt);
    }

    Integer poll() {
        return this.q.poll();
    }

    public static void main(String[] args) {
        MaxPQ mpq = new MaxPQ();
        mpq.offer(1);
        mpq.offer(10);
        mpq.offer(26);
        mpq.offer(4);
        System.out.println(mpq.poll());
        System.out.println(mpq.poll());
        System.out.println(mpq.poll());
        System.out.println(mpq.poll());
    }
}
