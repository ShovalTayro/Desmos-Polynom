package Ex1;

import java.util.Comparator;

import Ex1.Monom;
import Ex1.Monom_Comperator;
import Ex1.function;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
/**
 * this constructor get two variables a , b and set the Monom with them.
 * @param a : set a to be the coefficient of the Monom.
 * @param b : set b to b the power of the Monom.
 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * copy constructor
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	public double get_coefficient() {
		return this._coefficient;
	}

	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative of monom ax^b.
	 * @return b*ax^b-1
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	/** 
	 * this methot returns the value of the Monom in x
	 */
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
/** 
 * check if the Monom coefficient is 0 
 * @return true if the coefficient is 0 , else return false
 */
	public boolean isZero() {return this.get_coefficient() == 0;}
	
	// ***************** add your code below **********************
/**
 * this constructor check if a given string is a Monom of the shape "+ax^b" or "-ax^b".
 * if the given string is in this shape the constructor will build a new Monom
 * else throw error "the String that given is not monom"
 * @param s
 */
	public Monom(String s) {
		s=	s.toLowerCase();
		if(isMonom(s) == true) {
			int i = 0;
			String our_coefficient = "";
			String our_power = "";
			if(s.charAt(0) == 'x') our_coefficient += 1;
			while(i < s.length() && s.charAt(i) != 'x') {
				our_coefficient += s.charAt(i);
				i++;
			}
			if (s.charAt(s.length()-1) == 'x') set_power(1);
			else if (!s.contains("x")) set_power(0);
			else {
				i += 2;
				while(i < s.length()) {
					our_power += s.charAt(i);
					i++;
				}
				set_power(Integer.parseInt(our_power));
			}

			if(our_coefficient.equals("-")) set_coefficient(-1);
			else if(our_coefficient.equals("+")) set_coefficient(1);
			else{ set_coefficient(Double.parseDouble(our_coefficient));}
		}
		else{
			throw new RuntimeException("the string that given is not monom");
		}
	}
/**
 *  This method add between two Monoms(this Monom & Monom m) if they have the same power,
 * else throw "Error- the powers of the monoms are different and can't be added "
 * @param m :  Monom of the shape ax^b
 */
	public void add(Monom m) {
		if(this.get_power() != m.get_power()) throw new RuntimeException("Error- the powers of the monoms are different and can't be added ");
		else {
			this.set_coefficient(this.get_coefficient() + m.get_coefficient());
		}
	}
	/** 
	 * This method multiply between two Monoms(this Monom & Monom m) in the folowing way :
	 * multiply between the two Monoms coeffiecient , add the Monoms powers
	 * @param m :  Monom of the shape ax^b
	 */
	public void multipy(Monom d) {
		this.set_coefficient(this.get_coefficient() * d.get_coefficient());
		this.set_power(this.get_power() + d.get_power());
	}
/** 
 * method that returns the Monom as a String "ax^b"
 */
	public String toString() {
		String ans = "" +this._coefficient;
		if(this._power == 0 || this._coefficient == 0) return ans;
		else if (this._power == 1) return (ans += "x");
		else{
			return ans += "x^" + this._power;
		}
	}
	/** 
	 * private method that checked if a given String is a Monom.
	 * @param s : the given String to check
	 * @return true if the String is Monom , else return false.
	 */
	private boolean isMonom(String s) {
		s = s.toLowerCase();
		int index = 0;
		int countD = 0, countP = 0;
		if(s.charAt(0) == '+' ||  s.charAt(0) == '-') {
			index++;
			if(index >= s.length() || !isNumber(s.charAt(index))&& s.charAt(index)!= 'x') return false;
		}
		if(!s.contains("x")){
			while(index < s.length()) {
				if(!isNumber(s.charAt(index)) && s.charAt(index) != '^' && s.charAt(index) != '.') return false; 
				if(s.charAt(index) == '.') { 
					countD++;
					if(countD > 1) return false;
					if(index-1 < 0 || index+1 > s.length() 
							|| !isNumber(s.charAt(index-1)) 
							|| !isNumber(s.charAt(index+1))) {
						return false;
					}
				}
				if(s.charAt(index) == '^') { 
					countP++;
					if(countP > 1) return false;
					if(index-1 < 0 || index+1 > s.length() 
							|| !isNumber(s.charAt(index-1)) 
							|| !isNumber(s.charAt(index+1))) {
						return false;
					}
				}
				index++;
			}
		}
		else {
			//before x
			while(index < s.length() && s.charAt(index) != 'x') {
				if(!isNumber(s.charAt(index)) && s.charAt(index) != '.') return false;
				if(s.charAt(index) == '.') { 
					countD++;
					if(countD > 1) return false;
					if(index-1 < 0 || index+1 > s.length() 
							|| !isNumber(s.charAt(index-1)) 
							|| !isNumber(s.charAt(index+1))) {
						return false;
					}
				}
				index++;
			}

			//after x
			if(s.charAt(index)== 'x') {
				index++;
				if(index < s.length() && s.charAt(index) != '^') return false;
				else { 
					index++;
				}
			}
			while(index < s.length()) {
				if(index >= s.length() || !isNumber(s.charAt(index))) return false;
				index++;
			}
		}
		return true;
	}

	private boolean isNumber(char index) {
		if (index <= '9' && index >= '0') {
			return true;
		}
		else {
			return false;
		}
	}
	/** 
	 * This method substract between two Monoms(this Monom & Monom m) if they have the same power,
	 * else throw "Error- the powers of the monoms are different and can't be added "
	 * @param m :  Monom of the shape ax^b
	 */
	public void substarct(Monom m1) {
		if(this.get_power() != m1.get_power()) throw new RuntimeException("Error- the powers of the monoms are different and can't be added ");
		else {
			this.set_coefficient(this.get_coefficient() - m1.get_coefficient());
		}
	}
/**
 *  This method check if two given monoms are equals.
 * The criterions are :1) their power sould be the same , 2) the difference between the coefficients can't be more then epsilon. 
 * @param m : Monom from the shape ax^b
 * @return true if the monoms are equalse , else return false.
 */

	public boolean equals(Monom m) {
		if(this._coefficient == 0 && m._coefficient == 0) return true;
		double differ = Math.abs((this._coefficient-m._coefficient));
		if(differ > EPSILON|| this.get_power() != m._power) return false; 
		return true;
	}
	//****************** Private Methods and Data *****************


	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power  = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}


}