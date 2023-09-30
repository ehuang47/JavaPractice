package d4_collections;

public class GHashMap<K,V> {
    private Node<K,V>[] buckets;

    GHashMap() {
        this.buckets = new Node[16];
    }

    boolean put(K key, V value) {
        Node<K,V> node = new Node<K,V>(key, value);
        int hash = key.hashCode() % 16;

        Node<K,V> bucket = this.buckets[hash];

        if (bucket != null) {
            /*
            if (bucket.key.equals(node.key)) { // head matches, overwrite the bucket head
                node.next = bucket.next;
                this.buckets[hash] = node;
                return true;
            }
            d4_collections.Node<K,V> prev = bucket;
            bucket = bucket.next;
            while (bucket != null) {
                if (bucket.key.equals(node.key)) { // handle collisions
                    node.next = bucket.next;
                    prev.next = node;
                    return true;
                }
                bucket = bucket.next;
                prev = prev.next;
            };
            prev.next = node; // new node key, add to the end
            */

            // ALTERNATIVELY
            while (bucket.next != null){
                if (bucket.key.equals(node.key)) {
                    bucket.value = value;
                    return true;
                }
                bucket = bucket.next;
            }
            // now bucket's the last element, check this before adding node to the end
            if (bucket.key.equals(node.key)) {
                bucket.value = value;
            } else {
                bucket.next = node;
            }
            return true;

        } else {
            this.buckets[hash] = node;
        }

        return true;
    }

    V get(K key) {
        int hash = key.hashCode() % 16;

        Node<K,V> bucket = this.buckets[hash];

        while (bucket != null) {
            if (bucket.key.equals(key)) {
                return bucket.value;
            }
            bucket = bucket.next;
        }
        return null;
    }
    public static void main(String[] args) {
        GHashMap<String, Integer> map = new GHashMap<String, Integer>();
        System.out.println(map.put("Hello", 2));
        System.out.println(map.get("Hello"));
        System.out.println(map.put("Hello", 1));
        System.out.println(map.get("Hello"));
    }
}

class Node<K,V> {
    K key;
    V value;
    Node<K,V> next;

    Node(K key, V value){
        this.key = key;
        this.value = value;
    }

    Node(K key, V value, Node next){
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
