/**
 * @author Dmitry Bryzhatov
 * @since 2018-12-17
 */
public interface Cache<K, V> {
    V get(K element);

    void cache(K key, V value);

    int getSize();

    int getCapacity();

    void clear();

    void clear(int count);
}
