package com.toolstest;

public class App {
  public static void main( String as[] ) {  // VIOLATION - Formal
  	int q = 15; // VIOLATION - Field
  	int r = 0;
    for (int i = 0; i < 10; i++) { // Not a Violation (inside FOR)
      r += q;
    }
    while(true)
    	System.out.println("bla");
  }

  private void testing_naming_conventions(){
  	System.out.println("ha ha ");
  }
}
