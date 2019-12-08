package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Functions_GUI implements functions {
	ArrayList<function> fun = new ArrayList<function>();
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
//				int x = sCurrentLine.indexOf("[");
//				int y = sCurrentLine.indexOf("]");
				int f= sCurrentLine.indexOf("f");
//				String color = sCurrentLine.substring(x+1,y);
//				float r =  Float.parseFloat(color.substring(2, color.indexOf(",")));
//				float g =  Float.parseFloat(color.substring(color.indexOf(",")+3, color.lastIndexOf(",")));
//				float b =  Float.parseFloat(color.substring(color.lastIndexOf(",")+3));
//				Color col = null;
//				col = col.getHSBColor(r, g, b);
//				myCol.add(col);
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
		try {
			PrintWriter myWrite = new PrintWriter("ourFile.txt");
			for (int j = 0; j < fun.size(); j++) {
				myWrite.println(j + ") " + Colors[j%Colors.length] + "  f(X)= " + fun.get(j).toString());
				myWrite.flush();
			}
			myWrite.close();

			//			FileWriter fileWriter = new FileWriter("outFile.txt");
			//			BufferedWriter bufW = new BufferedWriter(fileWriter) ;
			//			for (int i = 0; i < fun.size(); i++) {
			//			bufW.write(fun.get(i).toString());
			//		}
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
		double x_step = (Math.abs(rx.get_max())+Math.abs(rx.get_min()))/res;
		double x0 = rx.get_min();

		StdDraw.setPenColor(Color.lightGray);
		//vertical lines
		for (double i = rx.get_min(); i <= Math.abs(rx.get_max())+Math.abs(rx.get_min()); i++) {
			StdDraw.line(rx.get_min()+i, ry.get_max(), rx.get_min()+i, ry.get_min());
		}
		// horizontal  lines
		for (double i = ry.get_min(); i <= Math.abs(ry.get_max())+Math.abs(ry.get_min()); i++) {
			StdDraw.line(rx.get_min(), ry.get_min()+i ,rx.get_max(), ry.get_min()+i);
		}

		//draw Xscale
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
		for (double i = rx.get_min(); i <= rx.get_max(); i++) {
			if(i!= 0) {
				StdDraw.text(i, 0 , Double.toString(i));
			}
		}

		//draw Yscale
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.text(x[res/2]-0.07, i+0.07, Double.toString(i));
		}

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

			System.out.println(j+") "+Colors[colors]+"  f(x)= "+fun.get(j));
			for (int i = 0; i < res; i++) {
				StdDraw.line(x[i], yy[j][i], x[i+1], yy[j][i+1]);
			}
		}	
	}

	@Override
	public void drawFunctions(String json_file) {
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(json_file));
			JSONObject jsonObject = (JSONObject) obj;
			long width = (long) jsonObject.get("Width");
			long height = (long) jsonObject.get("Height");
			JSONArray Jrx = (JSONArray) jsonObject.get("Range_X");
			JSONArray Jry = (JSONArray) jsonObject.get("Range_Y");
			String minX = Jrx.get(0).toString();
			String maxX = Jrx.get(1).toString();
			String minY = Jry.get(0).toString();
			String maxY = Jry.get(1).toString();
			Range rx = new Range(Integer.parseInt(minX), Integer.parseInt(maxX));
			Range ry = new Range(Integer.parseInt(minY), Integer.parseInt(maxY));
			long resolution = (long) jsonObject.get("Resolution");
			drawFunctions((int)width, (int)height, rx, ry, (int) resolution);

		}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
