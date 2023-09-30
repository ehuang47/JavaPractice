package d4_collections;

import java.util.PriorityQueue;
import java.util.Comparator;
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
//        d4_collections.MaxPQ mpq = new d4_collections.MaxPQ();
//        mpq.offer(1);
//        mpq.offer(10);
//        mpq.offer(26);
//        mpq.offer(4);
//        System.out.println(mpq.poll());
//        System.out.println(mpq.poll());
//        System.out.println(mpq.poll());
//        System.out.println(mpq.poll());

        // Create a custom comparator to reverse the natural ordering, which is minimum by default
        Comparator<Integer> maxComparator = Comparator.reverseOrder();
        PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>(maxComparator);

        maxPriorityQueue.add(5);
        maxPriorityQueue.add(2);
        maxPriorityQueue.add(9);
        maxPriorityQueue.add(1);

        while (!maxPriorityQueue.isEmpty()) {
            System.out.println(maxPriorityQueue.poll());
        }
    }
}
