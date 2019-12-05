package Ex1;

public class ComplexFunction implements complex_function {
	public static final double EPSILON = 0.0000001;
	function left;
	function right;
	Operation opera;

	public ComplexFunction() {
		// left side never null author orders
		this.left = new Polynom("0");
		this.right = null;
		this.opera = null;
	}

	public ComplexFunction(Operation opera, function left, function right){
		this.left = left;
		this.right = right;
		this.opera = opera;

	}
	public ComplexFunction(String opera,function left, function right){
		this.left = left;
		this.right = right;
		this.opera = this.findOp(opera);

	}

	public ComplexFunction(function p3) {
		this.left = p3;
		this.right = null;
		this.opera = Operation.None;
	}

	@Override
	public void plus(function f1) {
		if(this.right!= null) {
			ComplexFunction  cf = new ComplexFunction(this.opera, this.left, this.right);
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
			ComplexFunction  cf = new ComplexFunction(this.opera,this.left, this.right);
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
			ComplexFunction  cf = new ComplexFunction(this.opera,this.left, this.right);
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
			ComplexFunction  cf = new ComplexFunction(this.opera,this.left, this.right);
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
			ComplexFunction  cf = new ComplexFunction(this.opera,this.left, this.right);
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
			ComplexFunction  cf = new ComplexFunction(this.opera,this.left, this.right);
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
			if (this.right.f(x) > this.left.f(x)) return this.left.f(x);
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
			throw new RuntimeException("the operation that given is not ok");
		}
	}

	@Override
	public function initFromString(String s) {
		s = s.toLowerCase();
		s = s.replaceAll("\\s+", "");
		if(s.indexOf("(")== -1 && s.indexOf(")")== -1) {
			this.left= new Polynom(s);
			this.right=null;
			this.opera= Operation.None;
			return this.left.initFromString(s);
		}
		else {
			int indexOpen = s.indexOf("(");
			int indexC = indexCo(s, indexOpen);
			String op = s.substring(0, indexOpen);
			Operation opera = findOp(op);
			function fLeft = initFromString(s.substring(indexOpen+1,indexC));
			function fRight = initFromString(s.substring(indexC+1,s.length()-1));
			function ans = new ComplexFunction(opera,fLeft,fRight);
			return ans;
		}
	}

	public int indexCo(String s,int indexOpen){
		int countC=0;
		int countO=1;
		int index=indexOpen+1;
		while(index < s.length() && countO != countC){
			if(s.charAt(index) == '('){
				countO++;
			}
			if(s.charAt(index) == ','){
				countC++;
			}
			index++;
		}
		return index-1;
	}

	private Operation findOp(String op) {
		op = op.toLowerCase();
		switch(op){
		case "plus":
			return Operation.Plus;
		case "div":
			return Operation.Divid;
		case "mul":
			return Operation.Times;
		case "max":
			return Operation.Max;
		case "min":
			return Operation.Min;
		case "comp":
			return Operation.Comp;
		case "none":
			return Operation.None;
		default:
			throw new RuntimeException("the operation that given is not ok");
		}
	}

	@Override
	public function copy() {
		ComplexFunction  ans = new ComplexFunction(this.opera,this.left, this.right);
		return ans;
	}

	public String toString() {
		StringBuilder ans = new StringBuilder();
		ans.append(opToString()+"("+this.left.toString()+" , "+this.right.toString()+")");
		return ans.toString();
	}

	public String opToString() {
		switch(this.opera){
		case Plus:
			return "plus";
		case Divid:
			return "div";
		case Times:
			return "mul";
		case Max:
			return "max";
		case Min:
			return "min";
		case Comp:
			return "comp";
		default:
			return "error";
		}
	}

	public boolean equals(Object obj) {
		if(obj instanceof ComplexFunction)
		{
			ComplexFunction fun = (ComplexFunction) obj;
			for(double i = -10; i<10;i=i +Math.random()) {
				if (Math.abs((this.f(i)-fun.f(i)))>EPSILON) return false;

			}
			return true;
		}
		else if(obj instanceof function) 
		{
			ComplexFunction fun = new ComplexFunction("none",(function)obj, null);
			//ComplexFunction fun = (ComplexFunction) obj;
			for(double i = -10; i<10;i=i +Math.random()) {
				if (Math.abs((this.f(i)-fun.f(i)))>EPSILON) return false;

			}
			return true;
		}
		else {
			return false;
		}
	}
}