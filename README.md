# Ex1_oop
Additionally, to EX_0 in this project we extend the mathematical functions and operation you can use.
Except of Monom and Polynom and their functions(for more info you can read in this link:  https://github.com/ShovalTayro/Ex0/blob/master/README.md ) this project give you the option to do mathematical operations  on complex function as like : drawing functions on graph using StdDraw from Princeton ("https://introcs.cs.princeton.edu/java/stdlib/StdDraw.java.html") , express complex function and mathematical operation between a few complex functions as like divide between complex functions  , multiply,  add etc.
Complex Function is a mathematical expression that may contains more than one function and operation between the functions. Complex function is from the form of "operation"(f1 , f2) when f1 and f2 can be Monom Polynom or a ComplexFunction.
This project give you the option to check If the  mathematical forms you entered or given are Monom , Polynomic or function. after the definition you can use our methods on them( the definition for them, and the methods in our wiki " https://github.com/ShovalTayro/Ex1_oop/wiki"),
The class Functions_GUI give you the option to have a few complex function in array-list and make the methods on all functions in the list as like drawing them all computing the max/min between the functions in a given value. You can also multiply or divide complex function.
This project also contains a package of Junit tests of Monom , Polynom and Complex-function.
Methods summary:
void plus(function f1) - Add to this complex_function the f1 complex_function.
void mul(function f1)-Multiply this complex_function with the f1 complex_function.
void div(function f1)- Divides this complex_function with the f1 complex_function. 
void max(function f1) - Computes the maximum over this complex_function and the f1 complex_function in a specific value x.
void min(function f1) - Computes the minimum over this complex_function and the f1 complex_function in a specific value x 
void comp(function f1) -This method wrap the f1 complex_function with this function: this.f(f1(x))	
function left()- returns the left side of the complex function - this side should always exists (should NOT be null)
function right()- returns the right side of the complex function - this side might not exists.
Operation getOp()- returns The complex_function operation as like: plus, mul, div, max etc;
double f(double x) – computes the complex function in a given 'x' in the following way : compute the left side , right side and than do the operation between them.
function initFromString(String s) – returns a complex functionfrom the fporm "operation (f1,f2)" from a given string . 
function copy()- deep copy of complex function.
function initFromFile(String file) – Init a new collection of functionsand their colors for drawing,  from a given file.
void saveToFile(String file) – save a collection of complex function to"filename.txt".
void drawFunctions(int width, int height, Range rx, Range ry, int resolution)- draw a function in a canvas that his size is the given width,height , in x&y range – max and min.using the  given resolution to draw points and put a line between them using stdDraw class.
void drawFunctions(String json_file) – read from the given file the width , height , range x&y , resolution , and draw the function using the method above. 
String toString() – returns the complex_function in a String.
Boolean equals(Object obj) – check if our complex_function and the given object are equals in a  few random points. For more info about how and why click here("wiki")
All  Monom & Polynom methods with a few changes: 
•	Equals in Monom&Polynom is now between our Polynom/Monom to Object.
•	initFromString(Monom&Polynom) – return a Monom/ Polynom from a given String assuming a correct form of Monom or Polynom.
