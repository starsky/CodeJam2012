package code.jam;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public final class ProblemA extends Base {

	private static final int ALPHABET_COUNT = 26;
	private static final Map<Character, Character> MAPPING = new HashMap<Character, Character>(
			ALPHABET_COUNT, 1.0f);

	@Override
	protected String solveCase(String input) {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			output.append(MAPPING.get(input.charAt(i)));
		}
		return output.toString();
	}

	private void infereMapping(Map<Character, Character> mapping)
			throws IOException {
		/* Mappings given in task description */
		mapping.put('y', 'a');
		mapping.put('e', 'o');
		mapping.put('z', 'q');
		//I have found this mapping thanks to Assert cause I have found that,
		//there is one more missing mapping
		mapping.put('q', 'z');		
		mapping.put(' ', ' ');
		List<String> googlereseList = loadCaseFile(getFile(ProblemCase.SAMPLE,
				true));
		List<String> englishList = loadResultFile(
				getFile(ProblemCase.SAMPLE, false), googlereseList.size());
		for (int i = 0; i < googlereseList.size(); i++) {
			String g = googlereseList.get(i);
			String e = englishList.get(i);
			for (int j = 0; j < g.length(); j++) {
				mapping.put(g.charAt(j), e.charAt(j));
				if (mapping.size() == ALPHABET_COUNT + 1)
					return;
			}
		}
		Assert.assertEquals(ALPHABET_COUNT + 1, mapping.size());
	}

	@Override
	@Before
	public void setUp() throws Exception {
		infereMapping(MAPPING);
	}

	@Override
	@After
	public void tearDown() throws Exception {
	}

	@Override
	@Test
	public void sampleTest() throws IOException {
		List<String> result = solve(ProblemCase.SAMPLE);
		Assert.assertEquals(
				loadResultFile(getFile(ProblemCase.SAMPLE, false),
						result.size()), result);
	}

	@Test
	@Override
	public void smallTest() throws IOException {
		List<String> result = solve(ProblemCase.SMALL);
		writeResults(getFile(ProblemCase.SMALL, false), result);
	}

	@Override
	public void bigTest() {

	}

	@Override
	protected String getStage() {
		return "qualy";
	}

}
