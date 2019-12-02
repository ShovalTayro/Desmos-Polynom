package Ex1Testing;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;
import Ex1.function;

class PolynomTest {
	
	@Test
	void testBadPolynomString() {
		String[] bad_Polynoms = {"-5-8k" ,"-x^6+t","x^2-@a0!", "0.x", ".h-23x3" ,"--x", "-" , "++2x", "5x^-3",  "2x^2.5", "2..5", "3x^3+2x^2+1.5xx", "3x^^2"};
		for(int i=0;i<bad_Polynoms.length;i++) {
			try {
				Polynom p = new Polynom(bad_Polynoms[i]);
			}
			catch(Exception e) {
				System.out.println("The given String is not Polynom");
			}
		}
	}

	@Test
	void testGoodPolynomString() {
		String[] good_polynoms = {"2x^7+3x+6","-x^3-4x^1-0.5","x^2-6+x^4", "0.9x^2+0.6x+4", "-5x^6+4x^3-x^1-x" , "0.0043x^6"};
		Polynom p1= new Polynom("2x^7+3x+6");
		Polynom p2= new Polynom("-x^3-4x^1-0.5");
		Polynom p3 = new Polynom("x^2-6+x^4");
		Polynom p4 = new Polynom("0.9x^2+0.6x+4");
		Polynom p5 = new Polynom("-5x^6+4x^3-x^1-x");
		Polynom p6 = new Polynom("0.0043x^6");
		Polynom expected [] = {p1, p2, p3, p4, p5, p6};
		for (int i = 0; i < expected.length; i++) {
			Polynom p = new Polynom(good_polynoms[i]);
			assertEquals(expected[i] , p);
		}
	}

	@Test
	void testF() {
		int j = 0;
		Polynom p1 = new Polynom("x^2+5");
		Polynom p2 = new Polynom("-x^3-3");
		Polynom p3 = new Polynom("x^4-5x^3+3x^2-x+1");
		Polynom p4 = new Polynom("5");
		double[] myF= {0, 1,-5, 0.25};
		double[] expected = {5, -3, 1, 5, 6, -4, -1, 5, 30, 122, 1331,5,5.0625, -3.015625, 0.86328125 , 5 };
		for (int i = 0; i < myF.length; i++) {
			assertEquals(expected[j], p1.f(myF[i]));
			assertEquals(expected[j+1], p2.f(myF[i]));
			assertEquals(expected[j+2], p3.f(myF[i]));
			assertEquals(expected[j+3], p4.f(myF[i]));
			j= j+4;
		}
	}

	@Test
	void testAddPolynom_able() {
		int j = 0;
		String[] Polynoms = {"2", "4x","-5x^3+2x^2", "-4x^4+1","-x^2+x^7-5x^3","3x^2-x^3+2x","2x^2+1","-2x^2-1"}; 
		String[] expected = {"4x+2","-4x^4-5x^3+2x^2+1","x^7-6x^3+2x^2+2x","0"};
		for (int i = 0; i < Polynoms.length; i=i+2) {
			Polynom p1= new Polynom(Polynoms[i]);
			Polynom p2= new Polynom(Polynoms[i+1]);
			Polynom p3= new Polynom(expected[j]);
			p1.add(p2);
			assertEquals(p3,p1);
			j++;
		}
	}

	@Test
	void testAddMonom() {
		int j =0;
		String[] Polynoms = { "4x^2","-5.2x^3+2x","3x^2-x^3+2x","-2x^2-1"}; 
		Monom m1 =new Monom(1,2);
		Monom m2= new Monom(5,0);
		Monom m3= new Monom(-3,3);

		String[] expected = {"5x^2", "4x^2+5", "-3x^3+4x^2",
				"-5.2x^3+x^2+2x", "-5.2x^3+2x+5", "-8.2x^3+2x",
				"-x^3+4x^2+2x", "-x^3+3x^2+2x+5", "-4x^3+3x^2+2x",
				"-x^2-1", "-2x^2+4","-3x^3-2x^2-1"};
		for (int i = 0; i <Polynoms.length; i++) {
			Polynom p1 =  new Polynom(Polynoms[i]);
			Polynom p2 =  new Polynom(Polynoms[i]);
			Polynom p3 =  new Polynom(Polynoms[i]);
			Polynom e =  new Polynom(expected[j]);
			Polynom e2 =  new Polynom(expected[j+1]);
			Polynom e3 =  new Polynom(expected[j+2]);
			p1.add(m1);
			p2.add(m2);
			p3.add(m3);
			assertEquals(e,p1);
			assertEquals(e2,p2);
			assertEquals(e3,p3);
			j = j+3;
		}
	}

	@Test
	void testSubstract() {
		int j = 0;
		String[] Polynoms = {"6", "-2x","-4x^3+3x^2", "-2x^4+1","-x^2+x^7-5x^3","-3x^2-2x^3+5x","3x^2+1","3x^2+1"}; 
		String[] expected = {"2x+6","2x^4-4x^3+3x^2-1","2x^2+x^7-3x^3-5x","0"};
		for (int i = 0; i < Polynoms.length; i=i+2) {
			Polynom p1= new Polynom(Polynoms[i]);
			Polynom p2= new Polynom(Polynoms[i+1]);
			Polynom p3= new Polynom(expected[j]);
			p1.substract(p2);
			assertEquals(p3,p1);
			j++;
		}
	}

	@Test
	void testMultiplyPolynom_able() {
		String[] polynom1 = {"2x^2+5x+6" , "7x^5-6-3x^2" , "x^2-0.5x+6","0x^5", "4x^8+4x+4"};
		String[] polynom2 = {"5x^3+1" , "x^2-0.5x^3" , "2x+3x^2-x^3" , "x^2", "0"};
		String[] expected = {"10x^5+25x^4+30x^3+2x^2+5x+6", "-3.5X^8+7x^7+1.5x^5-3x^4+3x^3-6x^2", "-x^5+3.5x^4-5.5x^3+17x^2+12x", "0", "0" };
		for (int i = 0; i < polynom1.length; i++) {
			Polynom p = new Polynom(polynom1[i]);
			Polynom p2 = new Polynom(polynom2[i]);
			Polynom e = new Polynom(expected[i]);
			p.multiply(p2);
			assertEquals(e,p);
		}
	}


	@Test
	void testIsZero() {
		String[] polynoms = {"x^2","0" ,"1.5x^2-6x+3", "0x^4", "0x^2+0x"};
		boolean[] expected = {false, true, false, true, true};
		for (int i = 0; i < polynoms.length; i++) {
			Polynom p = new Polynom(polynoms[i]);
			assertEquals(expected[i], p.isZero());
		}
	}

	@Test
	void testRoot() {
		String[] polynoms = {"x^3","-x^2+3" ,"1.5x^2-6x+3", "-x^6+2x^3-x^1+12" , "0.0043x^6" , "3x"};
		double[] expected = {-0.25, 1.71875, 0.59375, 1.63525390625, -1, -0.015625};
		for (int i = 0; i < polynoms.length; i++) {
			Polynom p = new Polynom(polynoms[i]);
			assertEquals(expected[i], p.root(-1, 2, 0.05));
		}
		String[] polynom = {"x+15",  "-x^4-2x-1" , "-0.5x^8", "-0.6x+12"};
		for (int i = 0; i < polynoms.length; i++) {
			Polynom p = new Polynom(polynoms[i]);
			try {
				p.root(-4, 0, 0.05);
			}
			catch(Exception e) {
				System.out.println("There is no root between this two x's that given");
			}
		}
	}

	@Test
	void testDerivative() {
		String[] polynoms = {"2x^7+3x^2+6","-x^3-4x^1-0.5","x^2-6+x^4", "0.9x^2+0.6x", "-5x^6+4x^3-x^1-x" , "0.0043x^6" , "3x" , "5"};
		String[] expected = {"14x^6+6x", "-3x^2-4", "2x+4x^3", "1.8x+0.6", "-30x^5+12x^2-2", "0.0258x^5","3", "0"};
		for (int i = 0; i < polynoms.length; i++) {
			Polynom p = new Polynom(polynoms[i]);
			Polynom e = new Polynom(expected[i]);
			Polynom_able p_d = p.derivative();
			assertEquals(e,p_d);
		}
	}

	@Test
	void testArea() {
		String []polynoms = {"3x^2-6x^3+9x-2","6x-5", "4x^6-5x^5+1"};
		double[] expected = { 0 , 0 , 2.4052119455953886};
		for (int i = 0; i < polynoms.length; i++) {
			Polynom p= new Polynom(polynoms[i]);
			double area = p.area(-1, 0, 0.0001);
			assertEquals(expected[i], area);
		}
	}


	@Test
	void testMultiplyMonom() {
		int j=0;
		String[] Polynoms = {"x^3+2x^2+3x", "-x^5+3x^4-6x^2+8","-x^7-2x-12"};
		Monom m1 = new Monom(5,0);
		Monom m2 = new Monom(2,2);
		Monom m3 = new Monom (-3,1);
		String[] expected = {"5x^3+10x^2+15x" , "2x^5+4x^4+6x^3","-3x^4-6x^3-9x^2" ,
				"-5x^5+15x^4-30x^2+40" ,"-2x^7+6x^6-12x^4+16x^2","3x^6-9x^5+18x^3-24x",
				"-5x^7-10x-60", "-2x^9-4x^3-24x^2","3x^8+6x^2+36x"};

		for (int i = 0; i < Polynoms.length; i++) {
			Polynom p1 = new Polynom(Polynoms[i]);
			Polynom p2 = new Polynom(Polynoms[i]);
			Polynom p3 = new Polynom(Polynoms[i]);
			Polynom e1 = new Polynom(expected[j]);
			Polynom e2 = new Polynom(expected[j+1]);
			Polynom e3 = new Polynom(expected[j+2]);
			p1.multiply(m1);
			p2.multiply(m2);
			p3.multiply(m3);
			assertEquals(e1,p1);
			assertEquals(e2,p2);
			assertEquals(e3,p3);
			j=j+3;
		}
	}

	@Test
	void testinitFromString() {
		String [] Polynoms = {"-x^4-x^3-5x^2-2" , "13x^2+1.2x+23" , "0.5x^5-3x^2+2x-5"};
		for (int i = 0; i <Polynoms.length; i++) {
			function p = new Polynom();			
			p=p.initFromString(Polynoms[i]);
			Polynom expected = new Polynom(Polynoms[i]);
			assertEquals(expected, p);
		}
	}
}

