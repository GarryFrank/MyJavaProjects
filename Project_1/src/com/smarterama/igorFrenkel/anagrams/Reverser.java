package com.smarterama.igorFrenkel.anagrams;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reverser {

	public static void main(String[] args) {

		Reverser reverser = new Reverser();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your text:");
		String text = scanner.nextLine();
		scanner.close();
		System.out.println(reverser.reverse(text));
	}

	public String reverse(String originalText) {

		String resultText = new String();
		for (String word : originalText.split(" ")) {
			char[] letters = word.toCharArray();
			List<Character> backOrderLetters = new ArrayList<Character>();
			for (int j = letters.length - 1; j >= 0; j--) {
				if (Character.isLetter(letters[j])) {
					backOrderLetters.add(letters[j]);
				}
			}
			for (int j = 0, k = 0; j < letters.length; j++) {
				if (Character.isLetter(letters[j])) {
					letters[j] = backOrderLetters.get(k);
					k++;
				}
			}
			resultText = resultText + String.valueOf(letters) + " ";
		}
		return resultText.trim();
	}
}