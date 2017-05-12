package ru.spbstu.telematics.popov.lab02;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Подобие HashMap, наследуется от intarface Map
 *
 * @param <K> - ключ, не должен повторяться
 * @param <V> - значение
 * @see Map
 */
public class MyMap<K, V> implements Map<K, V> {
    private List<K> keys;
    private List<V> values;
    private List<Node> links;

    public MyMap() {
        this.keys = new LinkedList<>();
        this.values = new LinkedList<>();
        this.links = new LinkedList<>();
    }

    @Override
    public int size() {
        return this.links.size();
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
        for (Node link : links) {
            if (link.getIndexKey() == i) {
                return link.getIndexValue();
            }
        }
        return -1;
    }

    private int getLinkIndex(Object key) {
        int i = keys.indexOf(key);
        for (int l = 0; l < links.size(); l++) {
            if (links.get(l).getIndexKey() == i) {
                return l;
            }
        }
        return -1;
    }

    @Override
    public V put(K key, V value) {
        int iV = values.indexOf(value);
        int l = getLinkIndex(key);
        if (l != -1) {
            if (iV == -1) {
                values.add(value);
                links.get(l).setIndexValue(values.size() - 1);
            } else {
                V oldValue = values.get(links.get(l).getIndexValue());
                if (oldValue.equals(value)) {
                    return value;
                } else {
                    if (rebaseLinks(oldValue, key)) {
                        values.remove(oldValue);
                        if (iV > links.get(l).getIndexValue()) {
                            iV--;
                        }
                    }
                    links.get(l).setIndexValue(iV);
                }
            }
        } else {
            if (iV == -1) {
                keys.add(key);
                values.add(value);
                links.add(new Node(keys.size() - 1, values.size() - 1));
            } else {
                keys.add(key);
                links.add(new Node(keys.size() - 1, iV));
            }
        }
        return value;
    }

    private boolean rebaseLinks(Object oldValue, Object key) {
        int iOldV = values.indexOf(oldValue);
        int iK = keys.indexOf(key);
        for (Node link : links) {
            if (link.getIndexValue() == iOldV && link.getIndexKey() != iK) {
                return false;
            }
        }
        for (Node link : links) {
            int i = link.getIndexValue();
            if (i > iOldV) {
                link.setIndexValue(i - 1);
            }
        }
        return true;
    }

    @Override
    public V remove(Object key) {
        int l = getLinkIndex(key);
        if (l != -1) {
            V v = values.get(getValueIndex(key));
            if (rebaseLinks(v, key)) {
                values.remove(v);
            }
            int iK = keys.indexOf(key);
            for (Node link : links) {
                if (link.getIndexKey() > iK) {
                    link.setIndexKey(link.getIndexKey() - 1);
                }
            }
            links.remove(l);
            keys.remove(key);
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
        set.addAll(keys);
        return set;
    }

    @Override
    public Collection<V> values() {
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> s = new HashSet<>();
        for (Node link : links) {
            s.add(new Entry<K, V>() {
                @Override
                public K getKey() {
                    return keys.get(link.getIndexKey());
                }

                @Override
                public V getValue() {
                    return values.get(link.getIndexValue());
                }

                @Override
                public V setValue(V value) {
                    return null;
                }
            });
        }
        return s;
    }

    private class Node {
        private int indexKey;
        private int indexValue;

        Node(int indexKey, int indexValue) {
            this.indexKey = indexKey;
            this.indexValue = indexValue;

        }

        int getIndexKey() {
            return indexKey;
        }

        public void setIndexKey(int indexKey) {
            this.indexKey = indexKey;
        }

        int getIndexValue() {
            return indexValue;
        }

        void setIndexValue(int indexValue) {
            this.indexValue = indexValue;
        }
    }
}
