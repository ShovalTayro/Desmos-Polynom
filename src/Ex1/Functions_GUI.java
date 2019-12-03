package Ex1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.processing.FilerException;
import com.google.gson.Gson;


public class Functions_GUI implements functions {
	ArrayList<function> fun = new ArrayList<function>();
	ArrayList<Color> myCol = new ArrayList<Color>();
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};
	@Override
	public boolean add(function arg0) {
		boolean ans = fun.add(arg0);
		return ans;
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		return fun.containsAll(arg0);
	}

	@Override
	public void clear() {
		fun.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		boolean ans = fun.contains(arg0);
		return ans;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return fun.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		boolean ans = fun.isEmpty();
		return ans;
	}

	@Override
	public Iterator<function> iterator() {
		return fun.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		boolean ans = fun.remove(arg0);
		return ans;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return fun.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return fun.retainAll(arg0);
	}

	@Override
	public int size() {
		return fun.size();
	}

	@Override
	public Object[] toArray() {
		Object[] functions = fun.toArray();
		return functions;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		T[] functions = fun.toArray(arg0);
		return functions;
	}

	public function get(int i) {
		return fun.get(i);
	}
	//reading cf from string in file
	@Override
	public void initFromFile(String file) throws IOException {
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(file));
			sCurrentLine = br.readLine();
			while (sCurrentLine != null) {
				int f= sCurrentLine.indexOf("f");
				String Color = sCurrentLine.substring(3,(f-2));
				Color col = java.awt.Color.getColor(Color);
				myCol.add(col);
				String cf = sCurrentLine.substring(f+6);
				function ourF= new ComplexFunction();
				ourF = ourF.initFromString(cf);
				fun.add(ourF);
				sCurrentLine = br.readLine();
			
			}
			br.close();
			
		}
		catch (IOException e)
		{
			System.out.println("File I/O error!");
		}
		
	}

	@Override
	public void saveToFile(String file) throws IOException {
		try (FileWriter fileWriter = new FileWriter("outFile.txt");
				 BufferedWriter bufW = new BufferedWriter(fileWriter)) {
				bufW.write(file);
			} 
		catch (IOException e) {
				System.err.format("IOException: " + e);
			}
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		int res = resolution;
		int size = fun.size();
		double[] x = new double[res+1];
		double[][] yy = new double[size][res+1];
		double x_step = (rx.get_max()-rx.get_min())/res;
		double x0 = rx.get_min();
		for (int i = 0; i <= res; i++) {
			x[i] = x0;
			for(int j = 0; j < size; j++) {
				yy[j][i] = fun.get(j).f(x[i]);
			}
			x0+=x_step;
		}
				
		for(int j = 0; j < size ;j++) {
			int colors = j%Colors.length;
			StdDraw.setPenColor(Colors[colors]);
		
			System.out.println(j+") "+Colors[j]+"  f(x)= "+fun.get(j));
			for (int i = 0; i < res; i++) {
				StdDraw.line(x[i], yy[j][i], x[i+1], yy[j][i+1]);
			}
		}	
	}

	@Override
	public void drawFunctions(String json_file) {
		//json here

	}

}
