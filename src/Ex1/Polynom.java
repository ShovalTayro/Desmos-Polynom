package Ex1;

import java.util.LinkedList;
import java.util.Iterator;
import Ex1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	LinkedList<Monom> poly = new LinkedList<Monom>(); 
	Monom_Comperator cmpByPow = new Monom_Comperator();
	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		;
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */

	public Polynom(String s) {
		s = s.toLowerCase();
		int index = 0;
		String temp = "";
		if(s.charAt(0)== '+' ||s.charAt(0)=='-') {
			temp += s.charAt(0);
			index = 1;
		}
		while(index < s.length()) {
			if(s.charAt(index) == '+' || s.charAt(index) == '-') {
				Monom m = new Monom(temp);
				temp = "";
				temp+= s.charAt(index);
				boolean flag = false;
				for (int i = 0; i < poly.size(); i++) {
					if(poly.get(i).get_power() == m.get_power()){
						poly.get(i).add(m);
						flag = true;
					}
				}
				if(!flag) {
				poly.addFirst(m);
				}
			}
			else {
				temp+= s.charAt(index);
			}
			index++;
		}
		Monom m = new Monom(temp);
		boolean add= false;
		for (int i = 0; i < poly.size(); i++) {
			if(poly.get(i).get_power()==m.get_power()) 
			{
				poly.get(i).add(m);
				add= true;
			}
		}
		if(!add) {
			poly.addFirst(m);
		}
		poly.sort(cmpByPow);
	}

	@Override
	public double f(double x) {
		double ans=0;
		for (int i = 0; i < poly.size(); i++) {
			double p = poly.get(i).get_power();
			ans += poly.get(i).get_coefficient()*Math.pow(x, p);
		}
		return ans;
	}

	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();

		while(it.hasNext()) {
			Monom m = it.next();
			add(m);
		}

	}

	@Override
	public void add(Monom m1) {
		int monPower = m1.get_power();
		boolean add = false;
		for (int i = 0; i < poly.size(); i++) {
			int p = poly.get(i).get_power();
			if(p == monPower) { 
				poly.get(i).add(m1);
				add = true;
				if(poly.get(i).get_coefficient() ==0 && poly.size() > 1) poly.remove(i);
			}
		}
		if(!add) {
			poly.addFirst(m1);
			poly.sort(cmpByPow);
		}
	}

	@Override
	public void substract(Polynom_able p1) {
		Monom m = new Monom("-1");
		Polynom temp = new Polynom(p1.toString());
		temp.multiply(m);
		this.add(temp);

		//option2

		//		Iterator<Monom> it = p1.iteretor();
		//		Monom m = new Monom(0,0);
		//		while(it.hasNext()) {
		//			m = it.next();
		//			//System.out.println(it.hasNext());
		//			substract(m);
		//		}

	}

	private void substract(Monom m) {
		int monPower = m.get_power();
		boolean sub = false;
		for (int i = 0; i < poly.size(); i++) {
			int p = poly.get(i).get_power();
			if(p == monPower) {
				poly.get(i).substarct(m);
				sub = true;
				if(poly.get(i).get_coefficient() == 0 && poly.size() > 0) poly.remove(i);
			}
		}
		if(!sub) {
			Monom m1 = new Monom(-1*m.get_coefficient(), m.get_power());
			poly.addFirst(m1);
			poly.sort(cmpByPow);
		}
	}

	@Override
	public void multiply(Polynom_able p1) {
		LinkedList<Monom> temp = new LinkedList<Monom>();
		Polynom p = new Polynom();
		Iterator<Monom> it = p1.iteretor();
		
		while (it.hasNext()) {
			Monom m = it.next();
			for (int i = 0; i < poly.size(); i++) {
				Monom m1 = new Monom(m);
				m1.multipy(poly.get(i));
				if(m1.get_coefficient() == 0 && poly.size() >1) { 
					//when we remove we don't want the index to move on because we changed poly size
					poly.remove(i);
					i=-1;
				}
				else {
					temp.add(m1);
				}
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			p.add(temp.get(i));
		}
		this.poly = p.poly;
	}

	@Override
	public boolean equals(Object p1) {
		if(p1 instanceof Polynom) {
			Polynom p2 = (Polynom)p1;

			Iterator<Monom> it = p2.iteretor();
			Iterator<Monom> it2 = poly.iterator();
			int count1 = 0;
			int count2 = 0;
			while(it.hasNext()) {
				count1++;
				it.next();
			}
			while(it2.hasNext()) {
				count2++;
				it2.next();
			}
			if(count1 == count2 ) {
				Iterator<Monom> it3 = p2.iteretor();
				Iterator<Monom> it4 = poly.iterator();
				while(it3.hasNext() ) {
					Monom m = it3.next();
					Monom m1 = it4.next();
					if(!m1.equals(m)) return false;
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isZero() {

		for (int i = 0; i < poly.size(); i++) {
			if(!poly.get(i).isZero()) return false;
		}
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		if(Math.abs(f(x0)) <= eps) return x0;
		if(Math.abs(f(x1)) <= eps) return x1;

		if(f(x0)*f(x1)> 0 ) {
			throw new  RuntimeException("there is no root between this two x's that given");
		}
		double x2 = ((x0+x1)/2);
		if(Math.abs(f(x2)) <= eps) return x2;

		else if(f(x2)*f(x1) > 0) { 
			x1=x2;
			return root(x0, x1, eps);
		}
		else { 
			x0=x2;
			return root(x0, x1, eps);	
		}
	}

	@Override
	public Polynom_able copy() {
		if(!poly.isEmpty()) {
			Polynom newPoly = new Polynom(); 
			for (int i = 0; i < poly.size(); i++) {
				newPoly.add(new Monom(poly.get(i)));
			}
			return newPoly;
		}
		return null;
	}

	@Override
	public Polynom_able derivative() {
		Polynom p = new Polynom();
		if(!isZero()) {
			LinkedList<Monom> newP = new LinkedList<Monom>();	
			for (int i = 0; i < poly.size(); i++) {
				Monom m = new Monom(poly.get(i).derivative());
				if(m.get_coefficient() == 0 && poly.size() > 1) i++;
				else {
					p.add(m);
				}
			}
		}
		return p;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		if(x0 > x1) {
			return 0;
		}
		double ans=0;
		for(double i = x0; i+eps < x1 ; i += eps) {
			if(this.f(i) >= 0){
				double y = f(i);
				ans+=(y * eps);
			}
		}
		return ans;
	}

	@Override
	public Iterator<Monom> iteretor() {
		return poly.iterator();
	}

	@Override
	public void multiply(Monom m1) {
		for (int i = 0; i < poly.size(); i++) {
			poly.get(i).multipy(m1);
		}
	}

	public String toString() {
		String ans = "";
		for (int i = 0; i < poly.size(); i++) {
			if(poly.get(i).get_coefficient() > 0 && i >= 1) {
				ans+= "+";
			}
			if(poly.get(i).get_coefficient() != 0) {
				ans+= poly.get(i).toString();
			}
		}
		if(ans == "") return "0";
		
		else { 
			return ans;
		}
	}
	@Override
	public function initFromString(String s) {
		Polynom p = new Polynom(s);
		return p;
	}
}