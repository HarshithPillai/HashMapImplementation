package custom.map.implementation;

import java.util.ArrayList;
import java.util.List;

public class HarshithMap<K, V> {
    private class MapNode<K, V> {
        K key;
        V value;
        MapNode<K, V> next;
        MapNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
    private List<MapNode<K, V>> buckets;
    private int size;
    private int bucketSize;
    private double getLoadFactor() {
        return (1.0 * this.size) / this.bucketSize;
    }

    public HarshithMap() {
        bucketSize = 10;
        buckets = new ArrayList<>();
        size = 0;
        for(int i=0; i<bucketSize; i++) {
            buckets.add(null);
        }
    }

    public void put(K key, V value) {
//        System.out.println("Inserting key: " + key + " value: " + value);
        int index = getIndex(key, bucketSize);
        boolean placed = false;
        MapNode<K, V> node = buckets.get(index);
        if(node != null) {
            while(node!=null) {
                if(node.getKey().equals(key)) {
                    node.setValue(value);
                    placed = true;
                    break;
                }
                node = node.next;
            }
        }
        if(!placed) {
            MapNode<K, V> temp = buckets.get(index);
            MapNode<K, V> curr = new MapNode(key, value);
            curr.next = temp;
            size++;
            buckets.set(index, curr);
        }
        if(getLoadFactor() > 0.7) {
            System.out.println("Load Factor before rehashing: " + getLoadFactor() + " Size: " + size + " BucketSize: " + bucketSize);
            rehash();
            System.out.println("Load Factor after rehashing: " + getLoadFactor() + " Size: " + size + " BucketSize: " + bucketSize);
        }
    }

    private void rehash() {
        System.out.println("Rehashing.....");
        this.size = 0;
        this.bucketSize = bucketSize<<1;
        List<MapNode<K, V>> temp = new ArrayList<>(buckets);
        this.buckets = new ArrayList<>();
        for(int i=0; i<bucketSize; i++) buckets.add(null);
        for(MapNode<K, V> node : temp) {
            while(node!=null) {
                put(node.getKey(), node.getValue());
                node = node.next;
            }
        }
    }

    private int getIndex(K key, int bucketSize) {
        long hash = Math.abs(key.hashCode());
        return (int)(hash%bucketSize);
    }

    public V get(K key) {
        int index = getIndex(key, bucketSize);
        MapNode<K, V> node = buckets.get(index);
        if(node != null) {
            while(node!=null) {
                if(node.getKey().equals(key)) {
                    return node.getValue();
                }
                node = node.next;
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = getIndex(key, bucketSize);
        MapNode<K, V> node = buckets.get(index);
        MapNode<K, V> prev = null;
        if(node != null) {
            while(node!=null) {
                if(node.getKey().equals(key)) {
                    if(prev != null) prev.next = node.next;
                    else buckets.set(index, node.next);
                    break;
                }
                prev = node;
                node = node.next;
            }
        }
    }

    public boolean containsKey(K key) {
        int index = getIndex(key, bucketSize);
        MapNode<K, V> node = buckets.get(index);
        if(node != null) {
            while(node!=null) {
                if(node.getKey().equals(key)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }
}
