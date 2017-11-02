package com.smarterama.igorFrenkel.integerDivision;

import static org.junit.Assert.*;

import org.junit.Test;

public class DivisionTest {

	private long dividend, divider;

	@Test(expected = IllegalArgumentException.class)
	public void enteringDividend_IsZero_ExceptionThrown() {

		Division division = new Division();
		division.checkDivisionSense(0, 12);

	}

	@Test(expected = ArithmeticException.class)
	public void enteringDivider_IsZero_ExceptionThrown() {

		Division division = new Division();
		division.checkDivisionSense(12, 0);

	}

	@Test
	public void smokeTest_ProgrammeShouldWorkProperly() {
		Division division = new Division();
		assertTrue(division instanceof Division);
	}

	@Test(expected = IllegalArgumentException.class)
	public void dividerMoreThenDividend_ExceptionThrown() {

		Division division = new Division();
		division.checkDivisionSense(12, 15);

	}
	
	@Test
	public void division_UnexpectedValues() {

		Division division = new Division();

		dividend = 1;
		divider = 1;
		String programmeResult = division.divide(dividend, divider);

		String testResult =  
				 " 1|1" + "\n" +
				 "- |_" + "\n" +
				 " 1|1" + "\n" +
				 " _"   + "\n" +
				 " 0"   + "\n" +
				 "";
		
		assertEquals(testResult , programmeResult);	
			
	}
	
	@Test
	public void division_ZeroInsideDividend_ShouldBeCorrect() {

		Division division = new Division();

		dividend = 393939000013L;
		divider = 13;
		String programmeResult = division.divide(dividend, divider);

	String testResult = " 393939000013|13"          + "\n" +
				        "-            |___________" + "\n" +
				        " 39          |30303000001" + "\n" +
				        " __"                       + "\n" +
				        "   39"                     + "\n" +
				        "  -"                       + "\n" +
				        "   39"                     + "\n" +
				        "   __"                     + "\n" +
				        "     39"                   + "\n" +
				        "    -"                     + "\n" +
				        "     39"                   + "\n" +
				        "     __"                   + "\n" +
				        "           13"             + "\n" +
				        "          -"               + "\n" +
				        "           13"             + "\n" +
				        "           __"             + "\n" +
				        "            0"             + "\n" +
				        "";
		
	assertEquals(testResult , programmeResult);

	}

	@Test
	public void division_ManyZeroDividend_ShouldBeCorrect() {

		Division division = new Division();

		dividend = 100000;
		divider = 300;
		String programmeResult = division.divide(dividend, divider);
		
	String testResult = " 100000|300" + "\n" +
				        "-      |___" + "\n" +
				        "  900  |333" + "\n" +
				        "  ___"       + "\n" +
				        "  1000"      + "\n" +
				        " -"          + "\n" +
				        "   900"      + "\n" +
				        "   ___"      + "\n" +
				        "   1000"     + "\n" +
				        "  -"         + "\n" +
				        "    900"     + "\n" +
				        "    ___"     + "\n" +
				        "    100"     + "\n" +
				        "";
		
		assertEquals(testResult , programmeResult);
		
	}

	@Test
	public void division_LastZeroDividend_ShouldBeCorrect() {

		Division division = new Division();

		dividend = 120000;
		divider = 666;
		String programmeResult = division.divide(dividend, divider);

	String testResult =" 120000|666" + "\n" +
				       "-      |___" + "\n" +
				       "  666  |180" + "\n" +
				       "  ___"       + "\n" +
				       "  5340"      + "\n" +
				       " -"          + "\n" +
				       "  5328"      + "\n" +
				       "  ____"      + "\n" +
				       "    120"     + "\n" +
				       "";
		
		assertEquals(testResult , programmeResult);
		
	}

	@Test
	public void division_ShouldBeCorrect() {

		Division division = new Division();

		dividend = 1450725;
		divider = 725;
		String programmeResult = division.divide(dividend, divider);
		
	String testResult = " 1450725|725"  + "\n" +
				        "-       |____" + "\n" +
				        " 1450   |2001" + "\n" +
				        " ____"         + "\n" +
				        "     725"      + "\n" +
				        "    -"         + "\n" +
				        "     725"      + "\n" +
				        "     ___"      + "\n" +
				        "       0"      + "\n" +
				        ""; 
      
		assertEquals(testResult , programmeResult);
}
	}
