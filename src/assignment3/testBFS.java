package assignment3;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class testBFS {
	
	private static Set<String> dict;
	private static ByteArrayOutputStream outContent;

	@BeforeClass
	public static void setUp() {
		Main.initialize();
		dict = Main.makeDictionary();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}

	private boolean verifyLadder(ArrayList<String> ladder) {
		String prev = null;
		if (ladder == null)
			return true;
		for (String word : ladder) {
			if (!dict.contains(word.toUpperCase()) && !dict.contains(word.toLowerCase())) {
				return false;
			}
			if (prev != null && !differByOne(prev, word))
				return false;
			prev = word;
		}
		return true;
	}

	private static boolean differByOne(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;

		int diff = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i) && diff++ > 1) {
				return false;
			}
		}

		return true;
	}
	
	@Test
	public void Test1() {
		ArrayList<String> ladder = Main.getWordLadderBFS("joint", "jaunt");
		assertTrue(verifyLadder(ladder));
		assertFalse(ladder == null || ladder.size() == 0 || ladder.size() == 2);
		Main.printLadder(ladder);
	}

	@Test
	public void Test2() {
		ArrayList<String> ladder = Main.getWordLadderBFS("stuff", "music");
		assertTrue(verifyLadder(ladder));
		assertFalse(ladder == null || ladder.size() == 0 || ladder.size() == 2);
		Main.printLadder(ladder);
	}
	
	@Test
	public void Test3() {
		ArrayList<String> ladder = Main.getWordLadderBFS("kings", "thing");
		assertTrue(verifyLadder(ladder));
		assertFalse(ladder == null || ladder.size() == 0 || ladder.size() == 2);
		Main.printLadder(ladder);
	}
	
	@Test
	public void Test4() {
		ArrayList<String> ladder = Main.getWordLadderBFS("agave", "funky");
		assertTrue(verifyLadder(ladder));
		assertFalse(ladder == null || ladder.size() == 0 || ladder.size() == 2);
		Main.printLadder(ladder);
	}
	
	@Test
	public void Test5() {
		ArrayList<String> ladder = Main.getWordLadderBFS("penis", "cocks");
		assertTrue(verifyLadder(ladder));
		assertFalse(ladder == null || ladder.size() == 0 || ladder.size() == 2);
		Main.printLadder(ladder);
	}

}
