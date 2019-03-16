import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class HashCache<K, V> implements Cache<K, V> {
    private final double clearFactor;
    private final Map<K, V> cache;
    private final int capacity;

    public HashCache(int capacity, double clearFactor) {
        checkDigitParameter(clearFactor);
        checkDigitParameter(capacity);
        this.cache = new LinkedHashMap<>(capacity);
        this.clearFactor = clearFactor;
        this.capacity = capacity;
    }

    @Override
    public void cache(K key, V value) {
        checkObjectParameter(key, value);

        cache.put(key, value);

        if (cache.size() > capacity) {
            int countDeleteElement = (int) Math.ceil(cache.size() * clearFactor);
            clear(countDeleteElement);
        }
    }

    @Override
    public V get(K key) {
        checkObjectParameter(key);

        V repeat = cache.get(key);

        if (repeat != null) {
            cache.remove(key);
            return cache.put(key, repeat);
        }
        return null;
    }

    @Override
    public int getSize() {
        return cache.size();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public void clear(int count) {
        Iterator<Map.Entry<K, V>> removeIterator = cache.entrySet().iterator();

        for (int i = 0; i < count; i++) {
            removeIterator.next();
            removeIterator.remove();
        }
    }

    private void checkObjectParameter(Object... params) {
        for (Object param : params) {
            if (param == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void checkDigitParameter(long... params) {
        for (long param : params) {
            if (param <= 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void checkDigitParameter(double... params) {
        for (double param : params) {
            if (param <= 0) {
                throw new IllegalArgumentException();
            }
        }
    }
}
