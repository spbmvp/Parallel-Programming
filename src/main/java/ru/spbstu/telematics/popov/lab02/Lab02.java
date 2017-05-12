package ru.spbstu.telematics.popov.lab02;

public class Lab02 {
    public static void main(String[] args) {
        MyMap<Integer, String> myMap = new MyMap<>();

        myMap.put(1, "a");
        myMap.put(2, "b");
        myMap.put(3, "d");
        myMap.put(1, "c");
        myMap.put(3, "a");


        myMap.remove(1);
        myMap.remove(1);
    }
}
