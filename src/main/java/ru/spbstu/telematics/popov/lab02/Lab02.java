package ru.spbstu.telematics.popov.lab02;

public class Lab02 {
    public static void main(String[] args) {
        MyMap<String, String> myMap = new MyMap<>();

        myMap.put("1", "a");
        myMap.put("2", "b");
        myMap.put("3", "a");
        myMap.put("4", "d");

        myMap.remove("1");
        myMap.remove("2");
        myMap.put("1", "a");
        myMap.put("2", "b");
        myMap.remove("2","b");
    }
}
