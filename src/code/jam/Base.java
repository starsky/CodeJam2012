package code.jam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class Base {

	public static enum ProblemCase {
		SAMPLE, SMALL, BIG;
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
		
	}

	@Before
	public abstract void setUp() throws Exception;

	@After
	public abstract void tearDown() throws Exception;

	@Test
	public abstract void sampleTest() throws Exception;

	public abstract void smallTest() throws Exception;

	public abstract void bigTest() throws Exception;

	protected abstract String solveCase(String oneCase);
	
	protected List<String> solve(ProblemCase caseType) throws IOException {
		List<String> cases = loadCaseFile(getFile(caseType, true));
		List<String> result = new ArrayList<String>(cases.size());
		for (String oneCase : cases) {
			result.add(solveCase(oneCase));
		}
		return result;
	}

	protected List<String> loadCaseFile(File file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		List<String> list = new ArrayList<String>();
		int inputSize = Integer.parseInt(in.readLine());
		for (String line = in.readLine(); line != null
				&& list.size() < inputSize; line = in.readLine()) {
			list.add(line);
		}
		in.close();
		return list;
	}

	protected List<String> loadResultFile(File file, int inputSize)
			throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		List<String> list = new ArrayList<String>();
		for (String line = in.readLine(); line != null
				&& list.size() < inputSize; line = in.readLine()) {
			list.add(line.split("Case #[0-9]+:\\ ")[1]);
		}
		in.close();
		return list;
	}
	
	protected void writeResults(File file, List<String> results) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		int count = 1;
		for(String line : results) {
			out.write(String.format("Case #%d: %s\n",count++,line));
		}
		out.flush();
		out.close();
	}

	private String getProblemName() {
		return this.getClass().getSimpleName();
	}
	private String getPathToFiles() {
		return "files" + File.separator + getStage();
	}
	
	protected abstract String getStage();

	protected File getFile(ProblemCase probCase, boolean isInput) {
		String fileName = String.format("%s_%s.%s", getProblemName(), probCase.toString(),
				isInput ? "in" : "res");
		return new File(getPathToFiles(),fileName);
	}
}
