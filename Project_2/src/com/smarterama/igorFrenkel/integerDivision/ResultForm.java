package com.smarterama.igorFrenkel.integerDivision;

import java.util.ArrayList;
import java.util.List;

public class ResultForm {
	
	private static final int DIVIDEND_POSITION = 0;
	private static final int DIVIDER_POSITION = 1;
	private static final int DEDUCTION_POSITION = 2;
	private static final int MINUEND_POSITION = 3;
	
	public String formatResult(List<Long> deductions,List<Integer> numbersShifts) {

		String result = "";
		result += formatResultHeader(deductions, numbersShifts);
		result += formatResultBody(deductions, numbersShifts);

		return result;
	}
	
	public String formatResultHeader(List<Long> deductions, List<Integer> numbersShifts){
		
		String resultHeader = new String();
		String space = " "; 
		String line = "_";
		List<Integer> numbersLength = new ArrayList<Integer>();
		countNumberLength(deductions, numbersLength);
		
		resultHeader  = " " + deductions.get(DIVIDEND_POSITION) + "|" + deductions.get(DIVIDER_POSITION) + "\n";
		resultHeader += "-" + obtainDelimiter(space, numbersLength.get(DIVIDEND_POSITION)) + "|";
		resultHeader += obtainDelimiter(line, numbersLength.get(numbersLength.size()-1)) + "\n";
		resultHeader += " " + obtainDelimiter(space, numbersLength.get(DEDUCTION_POSITION) - numbersLength.get(MINUEND_POSITION));
		resultHeader += deductions.get(MINUEND_POSITION);
		resultHeader += obtainDelimiter(space, numbersLength.get(DIVIDEND_POSITION) - numbersLength.get(DEDUCTION_POSITION));
		resultHeader += "|" + deductions.get(DIVIDEND_POSITION)/deductions.get(DIVIDER_POSITION) + "\n";
		resultHeader += " " + obtainDelimiter(space, numbersLength.get(DEDUCTION_POSITION) -  numbersLength.get(MINUEND_POSITION));
		resultHeader += obtainDelimiter(line, numbersLength.get(MINUEND_POSITION)) + "\n";
		
		return resultHeader;
	}
	
	public String formatResultBody(List<Long> deductions, List<Integer> numbersShifts){
		
		String resultBody = new String();
		String space = " "; 
		String line = "_";
		int spaceAmount = 1;
		List<Integer> numbersLength = new ArrayList<Integer>();
		countNumberLength(deductions, numbersLength);
		
        for (int j = 4, d = 0; j < deductions.size(); j = j + 2, d++) {
			spaceAmount = numbersShifts.get(d) + spaceAmount;
			resultBody += obtainDelimiter(space, spaceAmount) + deductions.get(j) + "\n";
			if ( j + 2 >= deductions.size()){
				return resultBody;
			}
			int nextSpaceAmount = numbersLength.get(j) - numbersLength.get(j + 1);
			resultBody += obtainDelimiter(space, spaceAmount - 1) + "-" + "\n";
			resultBody += obtainDelimiter(space, nextSpaceAmount + spaceAmount) + deductions.get(j + 1) + "\n";
			resultBody += obtainDelimiter(space, nextSpaceAmount + spaceAmount);
			resultBody += obtainDelimiter(line, numbersLength.get(j + 1)) + "\n";
		}
		return resultBody;
	}
	
    public void countNumberLength(List<Long> deductions, List<Integer> numbersLength){
		
		Division division = new Division();
		for (Long number : deductions) {
			numbersLength.add(division.findLengthCounter(number)); 	
		}
		numbersLength.add(division.findLengthCounter(deductions.get(DIVIDEND_POSITION)/deductions.get(DIVIDER_POSITION)));
	}
	
	public String obtainDelimiter(String delimiter, long delimiterLength) {

		String resultDelimiter = new String();
		for (int k = 0; k < delimiterLength; k++) {
			resultDelimiter += delimiter ;
		}
		return resultDelimiter;
	}
}
