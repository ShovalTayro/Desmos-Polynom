package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.function;

class MonomTest {

	@Test
	void testGoodMonoms() {
		String[] good_monoms = {"-1","x","x^2", "0.5x^2", "-5x^7", "-x^3", "-0.2", "0.0043x^78"};
		Monom m1 = new Monom(-1,0);
		Monom m2 = new Monom(1,1);
		Monom m3 = new Monom(1,2);
		Monom m4 = new Monom(0.5,2);
		Monom m5 = new Monom(-5,7);
		Monom m6 = new Monom(-1,3);
		Monom m7 = new Monom(-0.2,0);
		Monom m8 = new Monom(0.0043,78);
		Monom expected [] = {m1, m2, m3, m4, m5, m6, m7, m8};
		for (int i = 0; i < expected.length; i++) {
			Monom m = new Monom(good_monoms[i]);
			assertEquals(expected[i] , m);
		}
	}
	@Test
	void testinitFromString() {
		String [] Monoms = {"x" , "-1.5x^2" , "0.03x^3" , "32x^8" , "-15.2"};
		Monom m1 =  new Monom(1,1);
		Monom m2 =  new Monom(-1.5,2);
		Monom m3 =  new Monom(0.03,3);
		Monom m4 =  new Monom(32,8);
		Monom m5 =  new Monom(-15.2,0);
		Monom[] expected = {m1, m2 , m3 , m4 , m5};
		for (int i = 0; i <Monoms.length; i++) {
	   function m = new Monom(0,0);			
	   m=	m.initFromString(Monoms[i]);
	    assertEquals(expected[i], m);
}
				
	}
	
	@Test
	void testBadMonoms() {
		String[] bad_monoms = {"-",".5x^2","-x5","9m","@#", "0.x)", "23x3", "sdghxs", "-.54x", "0.x"};
		for(int i=0;i<bad_monoms.length;i++) {
			try {
				Monom m2 = new Monom(bad_monoms[i]);
			}
			catch(Exception e) {
				System.out.println("The given String is not Monom");
			}
		}
	}

	@Test
	void testIsZero() {
		String[] Monoms = {"-12x^2", "0", "0.5x" , "0x^6"};
		boolean[] expected = {false, true, false, true};
		for (int i = 0; i < Monoms.length; i++) {
			Monom m = new Monom(Monoms[i]);
			assertEquals(expected[i], m.isZero());
		}
	}

	@Test
	void testAdd() {
		String[] Monoms = {"2x^3", "4.6x^3", "-2x", "-x", "0.3x^2","-13x^2"};
		String[] expected = {"6.6x^3", "-3x", "-12.7x^2"};
		int j = 0;
		for (int i = 0; i < Monoms.length; i = i+2) {
			Monom m = new Monom(Monoms[i]);
			Monom m2 = new Monom(Monoms[i+1]);
			m.add(m2);
			Monom e = new Monom(expected[j]);
			j++;
			assertEquals(e, m);
		}
	}

	@Test
	void testNotAdd() {
		String[] Monoms = {"2x^3", "4.2x^5", "-2", "-x", "0.3x^70","-13x^69"};
		for (int i = 0; i < Monoms.length; i = i+2) {
			Monom m = new Monom(Monoms[i]);
			Monom m2 = new Monom(Monoms[i+1]);
			try{
				m.add(m2);
			}
			catch(Exception e) {
				System.out.println("The Monoms have different powers and canno't be added");
			}
		}
	}

	@Test
	void testsub() {
		String[] Monoms = {"2x^2", "x^2" , "2.3x^5" , "7x^5" , "x^3" , "-6x^3", "-3x^8", "7x^8", "-6x" , "-0.03x", "0","-1"};
		String[] expected = {"x^2", "-4.7x^5", "7x^3" , "-10x^8", "-5.97x^1", "1"};
		int j = 0;
		for (int i = 0; i < Monoms.length; i = i+2) {
			Monom m = new Monom(Monoms[i]);
			Monom m2 = new Monom(Monoms[i+1]);
			m.substarct(m2);;
			Monom e = new Monom(expected[j]);
			j++;
			assertEquals(e, m);
		}
	}
	@Test
	void testNotSub() {
		String [] Monoms = {"2x^7", "3x^6", "-3x^4", "x^3", "2x^5", "3x^8" , "x^7" , "-5"};
		for (int i = 0; i < Monoms.length; i = i+2) {
			Monom m = new Monom(Monoms[i]);
			Monom m2 = new Monom(Monoms[i+1]);
			try{
				m.substarct(m2);
			}
			catch(Exception e) {
				System.out.println("The Monoms have different powers and canno't be substract");
			}
		}
	}

	@Test
	void testderivative() {
		String [] Monoms = {"-2x^5", "-x", "5", "87.3x^3"};
		String[] expected = {"-10x^4", "-1" , "0", "261.9x^2"};
		for (int i = 0; i < Monoms.length; i++) {
			Monom m = new Monom(Monoms[i]);
			Monom e = new Monom(expected[i]);
			Monom m2 =m.derivative();
			assertEquals(e, m2);
		}	
	}
	
	@Test
	void testF() {
		int j =0;
		Monom m1= new Monom(-2,2);
		Monom m2 = new Monom(7.3,3);
		double[] myF= {0, 1,-1, 3, 0.5};
		double[] expected = {-0, 0, -2, 7.3, -2, -7.3, -18, 197.1, -0.5, 0.9125};
		for (int i = 0; i < myF.length; i++) {
			System.out.println(i);
			assertEquals(expected[j], m1.f(myF[i]));
			assertEquals(expected[j+1], m2.f(myF[i]));
			j= j+2;

		}
	}
	
	@Test
    void testMul() {
		String [] Monoms = {"-1", "x^3", "5","x^8","-5","-2","3x^2","2x^2", "2.5x^4", "4x^6", "-6x", "-3x^2", "5x^7","-x"};
		String[] expected = {"-x^3", "5x^8" , "10", "6x^4", "10x^10", "18x^3", "-5x^8"};
		int j = 0;
		for (int i = 0; i < Monoms.length; i = i+2) {
			Monom m = new Monom(Monoms[i]);
			Monom m2 = new Monom(Monoms[i+1]);
			m.multipy(m2);
			Monom e = new Monom(expected[j]);
			j++;
			assertEquals(e, m);
		}
	}

}