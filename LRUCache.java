/* Light collaboration with Daniel Ho */

import java.util.Iterator;

public class LRUCache<Key extends Comparable<Key>, Value> { 
    Node firstNode;
    Node lastNode;
    int capacity;
    int size;
    
    public class Node {
        Key key;
        Value value;
        Node next;
        Node prev;
        
        public Node(Key key, Value value) {
            this.value = value;
            this.key = key;
        }
    }   
    
    public LRUCache(int capacity){
        this.size = 0;
        this.capacity = capacity;
    }
    
    public void helperInsert(Key key, Value value) {
        Node node = new Node(key, value);
        node.next = this.firstNode;
        node.prev = null;
        this.firstNode.prev = node;
        this.firstNode = node;
        size++;
    }
    
    public void helperRemove(Key key) {
        Node node = firstNode;
        Node node2 = firstNode;
        while ( node != null && node.key != key) {
            node2 = node;
            node = node.next;
        }
        if (node != null) {
            if (node == this.firstNode) {
                this.firstNode = this.firstNode.next;
            }
            else {
                node2.next = node.next;
            }
        }
        size--;
    }
    
    public void returnLRU(){
        Node last = firstNode;
        Node prevNode = firstNode;
        while(last.next != null){
            prevNode = last;
            last = last.next;
        }
        prevNode.next = null;
        size--;
    }
    
    
    public Value get(Key key) {
        Node node = firstNode;
        while (node != null) {
            if (node.key == key) {
                this.helperRemove(node.key);
                this.helperInsert(node.key, node.value);
                return node.value;
            }
            node = node.next;
        }
        
        return (Value)(Integer)(-1);
    }
    
    
    public void put(Key key, Value value) {
        if (firstNode == null){
            this.firstNode = new Node(key, value);
            size++;
        } else if (get(key) != (Value)(Integer)(-1)) {
            Node node = firstNode;
            while (node != null) {
                if (node.key == key) {
                    node.value = value;
                }
                node = node.next;
            }
        }else {
            if (this.size > this.capacity) {
                this.returnLRU();
                this.helperInsert(key, value);
            } else{ 
                this.helperInsert(key, value);
            }
        }
    }
    
    public Iterator<Key> keys() {
        return new MapIterator();
    }
    private class MapIterator implements Iterator<Key> {
        private Node node = firstNode;
        public void remove() {
            node.prev = node.next;}
        public boolean hasNext() { 
            return node.next == null; }
        public Key next() { 
            return node.next.key;
        }
    }
    
    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 );
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); 
        cache.put(3, 3); 
        System.out.println(cache.get(2)); 
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3)); 
        System.out.println(cache.get(4)); 
    }
    
    
}


