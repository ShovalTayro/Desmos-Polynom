package Ex1Testing;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;
/**
 * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
 * 0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
2) java.awt.Color[r=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)

 * @author boaz_benmoshe
 *
 */
class Functions_GUITest {
	public static void main(String[] a) {
		Functions_GUI data = FunctionsFactory();
		int w=1000, h=600, res=10;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
			data.drawFunctions(w,h,rx,ry,res);
			data.drawFunctions("test.txt");
		String file = "function_file.txt";
		String file2 = "function_file2.txt";
		try {
			data.saveToFile(file);
			Functions_GUI data2 = new Functions_GUI();
			data2.initFromFile(file);
			data.saveToFile(file2);
		}
		catch(Exception e) {e.printStackTrace();}

		String JSON_param_file = "GUI_params.txt";
		data.drawFunctions(JSON_param_file);

	}
	private Functions_GUI _data=null;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		_data = FunctionsFactory();
	}

	@Test
	void testInitFromFile() {
		try {
			Functions_GUI t = new Functions_GUI();
			t.initFromFile("file.txt");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	void testSaveToFile() {
		try {
			_data.saveToFile("our.txt");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Test
	void testequals() {
		Monom m = new Monom("x^2");
		ComplexFunction cf7 = new ComplexFunction("plus", new Polynom("x") , new Monom(1,0));
		ComplexFunction cf8 = new ComplexFunction("div", new Polynom("x^2-1") , new Polynom("x-1"));
		Polynom p = new Polynom("4x^5+6x-5.5");
		Polynom p1 = new Polynom("5x^6-9+6x");
		ComplexFunction cf2 = new ComplexFunction("mul", new Polynom("x"), new Polynom("x"));
		ComplexFunction cf5 = new ComplexFunction("plus", new Polynom("4x^5+6x"), new Polynom("-5.5"));
		ComplexFunction cf1 = new ComplexFunction(new Polynom("3x^2+3x"));
		ComplexFunction cf3 = new ComplexFunction("plus", new Polynom("x"), cf2);
		ComplexFunction cf4 = new ComplexFunction("mul", new Monom("3"), cf3);
		ComplexFunction cf6 = new ComplexFunction("none", new Polynom("5x^6-9+6x"), cf2);
		function[] functions= {m, p, p1, cf1, cf7};
		function[] expected= {cf2, cf5, cf6, cf4, cf8};
		for (int i = 0; i < expected.length; i++) {
			assertEquals(true, expected[i].equals(functions[i]));
		}
	}

	@Test
	void testF(){
		double[][] ans = new double[3][10];
		double[][] expected = {{-862.9, 2.0317460317460316, 1.5356825057839474, -561.9, -301 ,2.0317460317460316, -862.9, 25, -5, 4},
				{8.1 ,  2.0416666666666665, 2.6129032258064515, 3.1, 5 , 8.1, 2.0416666666666665 ,0, 0, 9},
				{-30.900000000000002,  1.3333333333333335,  0.5488454706927176, -56.300000000000004, 25.400000000000002 , 25.400000000000002,-56.300000000000004, 9, 3, 36}};

		for (int i = 0; i < _data.size(); i++) {
			ans[0][i] = _data.get(i).f(-5);
			ans[1][i] = _data.get(i).f(0);
			ans[2][i] = _data.get(i).f(3);

		}	
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans[0].length; j++) {
				boolean flag = false;
				if(expected[i][j] == ans[i][j]) flag = true;
				assertEquals(true, flag);
			}
		}
	}

	@Test
	void testCopy(){
		ComplexFunction p1 = new ComplexFunction("div" , new Polynom("4.7x^2-1.0x+6.0"),new Polynom("x^2+1.5") );
		function p2 = p1.copy();
		if (!p1.equals(p2)) {
			System.out.println("fail copy");
		}
		function p3 = new Polynom("x");
		p2 = p3;
		// check if p1 changes to or not
		if ( p1.equals(p2)) {
			System.out.println("fail deep copy");
		}	}

	@Test
	void testDrawFunctionsIntIntRangeRangeInt() {
		Functions_GUI data = FunctionsFactory();
		int w=1000, h=600, res=500;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
	data.drawFunctions(w,h,rx,ry,res);

		w=500;
		h=400;
		res=450;
		rx = new Range(-5,5);
		ry = new Range(-3, 6);
		data.drawFunctions(w,h,rx,ry,res);

		w=600;
		h=600;
		res=250;
		rx = new Range(0,20);
		ry = new Range(0,20);
		data.drawFunctions(w,h,rx,ry,res);


		w=800;
		h=700;
		res=500;
		rx = new Range(-20, 0);
		ry = new Range(0, 20);
		data.drawFunctions(w,h,rx,ry,res);
	}

	public static Functions_GUI FunctionsFactory() {
		Functions_GUI ans = new Functions_GUI();
		String s1 = "3.1+2.4x^2-x^4";
		String s2 = "5+2x-3.3x+0.1x^5";
		String[] s3 = {"x+3","x-2", "x-4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}

		ComplexFunction cf = new ComplexFunction("plus", p1,p2);
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x+1"),cf3);
		cf4.plus(new Monom("2"));
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		ComplexFunction x2 = new ComplexFunction("mul", new Polynom("x") , new Polynom("x"));
		ComplexFunction x3 = new ComplexFunction(new Polynom("x"));
		ComplexFunction x4 = new ComplexFunction("comp", new Polynom("x^2"), new Polynom("x+3"));
		//cf.toString();
		//x3.toString();
		Iterator<function> iter = ans.iterator();
		function f = iter.next();
		ComplexFunction max = new ComplexFunction(f);
		ComplexFunction min = new ComplexFunction(f);
		while(iter.hasNext()) {
			f = iter.next();
			max.max(f);
			min.min(f);
		}

		ans.add(max);
		ans.add(min);
		ans.add(x2);
		ans.add(x3);
		ans.add(x4);
		return ans;
	}
}
