package com.toolstest;

public class App {
  private int q = 15; // VIOLATION - Field
  public static void main( String as[] ) {  // VIOLATION - Formal
    int r = 20 + q; // VIOLATION - Local
    for (int i = 0; i < 10; i++) { // Not a Violation (inside FOR)
      r += q;
    }
    while(true)
    	System.out.println("bla");
  }
}
