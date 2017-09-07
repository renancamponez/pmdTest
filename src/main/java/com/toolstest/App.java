package com.toolstest;

import java.util.ArrayList;
import java.util.List;
import java.util.List;


public class App {

	private String blabla; 
	public static void main(String[] args) {
		int r = 20 ;
		// TODO Auto-generated method stub
		testIfZcCanBeShipped();
		testIfZcCannotBeShipped();
	}

	public static void testIfZcCanBeShipped() {
		ZipCodeRestriction zcRestriction = new ZipCodeRestriction();
		List<ZipCodeRange> ranges = createZcRange();
		int[] zipCodes = new int[] { 94199, 94300, 65532 };
		for (int i = 0; i < zipCodes.length; i++) {
			boolean result = zcRestriction.canBeShipped(ranges, zipCodes[i]);
			System.out.println("ZipCode " + zipCodes[i] + (result ? " can" : " cannot") + " be shipped");
		}
	}

	public static void testIfZcCannotBeShipped() {
		ZipCodeRestriction zcRestriction = new ZipCodeRestriction();
		List<ZipCodeRange> ranges = createZcRange();
		int[] zipCodes = new int[] { 94133, 94650, 94230, 94600, 94299 };
		for (int i = 0; i < zipCodes.length; i++) {
			boolean result = zcRestriction.canBeShipped(ranges, zipCodes[i]);
			System.out.println("ZipCode " + zipCodes[i] + (result ? " can" : " cannot") + " be shipped");
		}
	}

	public static List<ZipCodeRange> createZcRange() {
		ZipCodeRange r1 = new ZipCodeRange(94133, 94133);
		ZipCodeRange range2 = new ZipCodeRange(94200, 94299);
		ZipCodeRange range3 = new ZipCodeRange(94600, 94699);
		List<ZipCodeRange> ranges = new ArrayList<ZipCodeRange>();
		ranges.add(r1);
		ranges.add(range2);
		ranges.add(range3);
		return ranges;
	}

}
