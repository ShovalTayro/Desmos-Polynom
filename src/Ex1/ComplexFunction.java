package Ex1;

public class ComplexFunction implements complex_function {

	function left;
	function right;
	Operation opera;

	public ComplexFunction() {
		// left side never null author orders
		this.left = new Polynom("0");
		this.right = null;
		this.opera = null;
	}

	public ComplexFunction(function left, function right, Operation opera){
		this.left = left;
		this.right = right;
		this.opera = opera;

	}

	@Override
	public void plus(function f1) {
		if(this.right!= null) {
			ComplexFunction  cf = new ComplexFunction(this.left, this.right, this.opera);
			this.left = cf;
			this.right = f1;
			this.opera= Operation.Plus;	
		}
		else {
			this.right= f1;
			this.opera= Operation.Plus;
		}
	}

	@Override
	public void mul(function f1) {
		if(this.right!= null) {
			ComplexFunction  cf = new ComplexFunction(this.left, this.right, this.opera);
			this.left = cf;
			this.right = f1;
			this.opera= Operation.Times;	
		}
		else {
			this.right= f1;
			this.opera= Operation.Times;
		}

	}

	@Override
	public void div(function f1) {
		if(this.right!= null) {
			ComplexFunction  cf = new ComplexFunction(this.left, this.right, this.opera);
			this.left = cf;
			this.right = f1;
			this.opera= Operation.Divid;	
		}
		else {
			this.right= f1;
			this.opera= Operation.Divid;
		}

	}

	@Override
	public void max(function f1) {
		if(this.right!= null) {
			ComplexFunction  cf = new ComplexFunction(this.left, this.right, this.opera);
			this.left = cf;
			this.right = f1;
			this.opera= Operation.Max;	
		}
		else {
			this.right= f1;
			this.opera= Operation.Max;
		}

	}

	@Override
	public void min(function f1) {
		if(this.right!= null) {
			ComplexFunction  cf = new ComplexFunction(this.left, this.right, this.opera);
			this.left = cf;
			this.right = f1;
			this.opera= Operation.Min;	
		}
		else {
			this.right= f1;
			this.opera= Operation.Min;
		}

	}

	@Override
	public void comp(function f1) {
		if(this.right!= null) {
			ComplexFunction  cf = new ComplexFunction(this.left, this.right, this.opera);
			this.left = cf;
			this.right = f1;
			this.opera= Operation.Comp;	
		}
		else {
			this.right= f1;
			this.opera= Operation.Comp;
		}		
	}

	@Override
	public function left() {
		return this.left;
	}

	@Override
	public function right() {
		if(this.right!= null)return this.right;
		else return null;
	}

	@Override
	public Operation getOp() {
		return this.opera;
	}

	@Override
	public double f(double x) {
		Operation op = this.opera;
		switch(op) { 
		case Plus:
			return this.left.f(x) + this.right.f(x);

		case Divid:
			return this.left.f(x) / this.right.f(x);

		case Max:
			if (this.left.f(x) > this.right.f(x)) return this.left.f(x);
			else {
				return this.right.f(x);
			}

		case Min:
			if (this.right.f(x) > this.left.f(x)) return this.right.f(x);
			else {
				return this.right.f(x);
			}

		case Comp:
			return this.left.f(this.right.f(x));

		case Times:
			return this.left.f(x) * this.right.f(x);

		case None :
			return this.left.f(x);

			//Error case
		default:
			return -1;
		}
	}

	@Override
	public function initFromString(String s) {
		if(s.indexOf("(")== -1 && s.indexOf(")")== -1) {
			this.left= new Polynom(s);
			this.right=null;
			this.opera= Operation.None;
			return this;
		}
		else {
			// there are '('
			
		}
		return null;
	}

	@Override
	public function copy() {
		function ans = new ComplexFunction(this.left, this.right, this.opera);
		return ans;

	}

}
