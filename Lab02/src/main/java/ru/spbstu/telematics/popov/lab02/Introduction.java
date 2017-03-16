package ru.spbstu.telematics.popov.lab02;

public class Introduction {
    public static void main(String[] args) {
	System.out.println("Hello!");
    }
	
	void HhHhHhH() {
	for (;;) {  // Noncompliant; end condition omitted
		  // ...
	}
	int k=10;
	for(int i=0; i<10; i++){
		k++;
	}
	System.out.println(k);
	System.out.println("Hlksdf");
	}
	
	
	void test_divide() {
  int z = 0;
  if (unknown()) {
    // ..
    z = 3;
  } else {
    // ..
  }
  z = 1 / z; // Noncompliant, possible division by zero
}
}
