# LRU-Cache
 public get(Key key); // get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.

 public put(Key key, Value, val); // Set a new value of the key if that key already exists or insert a new pair of <key, value> into cache if the key is not already present. When the cache reached its capacity, it should remove the least recently used key before inserting the new key. You use one key if you call either get() or put() method on that key.

 public Iterator<Key> keys(); // return an iterator over all keys, so that each key and its associated value can be iterated.
