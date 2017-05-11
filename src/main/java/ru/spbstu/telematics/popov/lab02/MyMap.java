package ru.spbstu.telematics.popov.lab02;

import java.util.*;

/**
 * Подобие HashMap, наследуется от intarface Map
 *
 * @param <K> - ключ, не должен повторяться
 * @param <V> - значение
 * @see Map
 */
public class MyMap<K, V> implements Map<K, V> {
    List<K> keys;
    List<V> values;
    List<Integer> links;

    public MyMap() {
        this.keys = new LinkedList<>();
        this.values = new LinkedList<>();
        this.links = new LinkedList<>();
    }

    @Override
    public int size() {
        return this.keys.size();
    }

    @Override
    public boolean isEmpty() {
        return this.links.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return this.keys.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.values.contains(value);
    }

    @Override
    public V get(Object key) {
        return values.get(getValueIndex(key));
    }

    private int getValueIndex(Object key) {
        int i = keys.indexOf(key);
        for (int l = 0; l < links.size(); l += 2) {
            if (links.get(l) == i) {
                return links.get(l + 1);
            }
        }
        return -1;
    }

    private int getLinkIndex(Object key) {
        int i = keys.indexOf(key);
        for (int l = 0; l < links.size(); l += 2) {
            if (links.get(l) == i) {
                return l;
            }
        }
        return -1;
    }

    @Override
    public V put(K key, V value) {
        int i = keys.indexOf(key);
        int j = values.indexOf(value);
        int l = getLinkIndex(key);
        if (l != -1 && values.get(links.get(l + 1)) == value) {
            return value;
        }
        if (i == -1 && j == -1) {
            keys.add(key);
            values.add(value);
            links.add(keys.size() - 1);
            links.add(values.size() - 1);
        } else if (i == -1) {
            keys.add(key);
            links.add(keys.size() - 1);
            links.add(j);
        } else if (j == -1) {
            int index = links.get(l + 1);
            if (!isAnotherLinkValue(values.get(index), key)) {
                values.remove(index);
                rebaseLinks(index);
            }
            values.add(value);
            links.add(l + 1, values.size() - 1);
            links.remove(l + 2);
        } else {
            links.add(l + 1, j);
            links.remove(l + 1);
        }
        return value;
    }

    private void rebaseLinks(Integer indexRebase) {
        for (int l = 1; l < links.size(); l += 2) {
            if (links.get(l) > indexRebase) {
                links.add(l, links.get(l) - 1);
                links.remove(l + 1);
            }
        }
    }

    private boolean isAnotherLinkValue(Object value, Object key) {
        int i = values.indexOf(value);
        int j = keys.indexOf(key);
        for (int l = 0; l < links.size(); l += 2) {
            if (links.get(l + 1) == i && links.get(l) != j) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V remove(Object key) {
        int l = getLinkIndex(key);
        if (l != -1) {
            V v = values.get(getValueIndex(key));
            int i = links.get(l + 1);
            if (!isAnotherLinkValue(values.get(i), key)) {
                values.remove(i);
                rebaseLinks(i);
            }
            i = links.get(l);
            keys.remove(i);
            links.remove(l + 1);
            links.remove(l);
            return v;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {
        values.clear();
        keys.clear();
        links.clear();
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (K key: keys ) {
            set.add(key);
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> s = new HashSet<>();
        for (int k = 0; k < links.size(); k+=2) {
            final int i = k;
            s.add(new Entry<K, V>() {
                @Override
                public K getKey() {
                    return keys.get(links.get(i));
                }

                @Override
                public V getValue() {
                    return values.get(links.get(i+1));
                }

                @Override
                public V setValue(V value) {
                    return null;
                }
            });
        }
        return s;
    }
}
