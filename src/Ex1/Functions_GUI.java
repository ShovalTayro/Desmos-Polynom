package Ex1;

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
				String Color = sCurrentLine.substring(3,(f-2) );
				//string color add to array list of colors//
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
		// TODO Auto-generated method stub

	}

	@Override
	public void drawFunctions(String json_file) {
		//json here

	}

}
