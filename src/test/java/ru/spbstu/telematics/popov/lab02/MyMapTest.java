package ru.spbstu.telematics.popov.lab02;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MyMapTest {
    @Test
    public void size() {
        MyMap<Integer, String> m = new MyMap<>();
        Assert.assertEquals(m.size(), 0);
        m.put(1, "a");
        Assert.assertEquals(m.size(), 1);
        m.put(2, "b");
        Assert.assertEquals(m.size(), 2);
        m.put(2, "a");
        Assert.assertEquals(m.size(), 2);
    }

    @Test
    public void isEmpty() {
        MyMap<Integer, String> m = new MyMap<>();
        Assert.assertTrue(m.isEmpty());
        m.put(1, "a");
        Assert.assertFalse(m.isEmpty());
    }

    @Test
    public void containsKey() {
        MyMap<Integer, String> m = new MyMap<>();
        Assert.assertFalse(m.containsKey(1));
        m.put(1, "a");
        m.put(2, "b");
        Assert.assertTrue(m.containsKey(1));
        Assert.assertTrue(m.containsKey(2));
        Assert.assertFalse(m.containsKey(3));
    }

    @Test
    public void containsValue() {
        MyMap<Integer, String> m = new MyMap<>();
        Assert.assertFalse(m.containsValue("a"));
        m.put(1, "a");
        m.put(2, "b");
        Assert.assertTrue(m.containsValue("a"));
        Assert.assertTrue(m.containsValue("b"));
        Assert.assertFalse(m.containsValue("c"));
        m.put(2, "a");
        Assert.assertFalse(m.containsValue("b"));
    }

    @Test
    public void get() {
        MyMap<Integer, String> m = new MyMap<>();
        Assert.assertNull(m.get(1));
        m.put(1, "a");
        m.put(2, "b");
        Assert.assertEquals(m.get(1), "a");
        Assert.assertEquals(m.get(2), "b");
        Assert.assertNull(m.get(3));
    }

    @Test
    public void put() {
        MyMap<Integer, String> m = new MyMap<>();
        Assert.assertEquals(m.put(1, "a"), "a");
        Assert.assertEquals(m.get(1), "a");
        Assert.assertEquals(m.size(), 1);

    }

    @Test
    public void remove() {
        MyMap<Integer, String> m = new MyMap<>();
        m.put(1, "a");
        m.put(2, "b");
        Assert.assertEquals(m.get(1), "a");
        Assert.assertEquals(m.get(2), "b");
        Assert.assertEquals(m.size(), 2);
        Assert.assertEquals(m.remove(1), "a");
        Assert.assertEquals(m.size(), 1);
        Assert.assertNull(m.remove(1));
        Assert.assertEquals(m.remove(2), "b");
        Assert.assertEquals(m.size(), 0);
    }

    @Test
    public void clear() {
        MyMap<Integer, String> m = new MyMap<>();
        m.put(1, "a");
        m.put(2, "b");
        Assert.assertEquals(m.size(), 2);
        m.clear();
        Assert.assertEquals(m.size(), 0);
    }

    @Test
    public void keySet() {
        MyMap<Integer, String> m = new MyMap<>();
        m.put(1, "a");
        m.put(2, "b");
        Set<Integer> keys = new HashSet<>();
        keys.add(1);
        keys.add(2);
        Assert.assertEquals(m.keySet(), keys);
    }

    @Test
    public void values() {
        MyMap<Integer, String> m = new MyMap<>();
        m.put(1, "a");
        m.put(2, "b");
        m.put(3, "a");
        List<String> values = new LinkedList<>();
        values.add("a");
        values.add("b");
        Assert.assertEquals(m.values(), values);
    }

}