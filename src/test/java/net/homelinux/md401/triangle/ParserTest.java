package net.homelinux.md401.triangle;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class ParserTest {
	static final ImmutableList<ImmutableList<Integer>> SMALL_TRIANGLE = il(il(7), il(6, 3), il(3, 8, 5), il(11, 2, 10, 9));

	private static <T> ImmutableList<T> il(T... args) {
		return ImmutableList.copyOf(args);
	}

	@Test
	public void parsesSmallTriangle() throws Exception {
		Parser parser = new Parser(new FileInputStream(new File("src/test/resources/smalltriangle.txt"))) {

			@Override
			protected void handleError(String error) {
				Assert.fail();
			}
		};
		assertThat(parser.parse()).isEqualTo(SMALL_TRIANGLE);
	}

	private static class ExpectedErrorException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}
	
	@Test(expected = ExpectedErrorException.class)
	public void parseSmallTriangleWithExtraEntry() throws Exception {
		new Parser(new FileInputStream(new File("src/test/resources/smalltriangleextraentry.txt"))) {

			@Override
			protected void handleError(String error) {
				assertThat(error).isEqualTo("Wrong number of entries in line 3, should be 3 but was 4");
				throw new ExpectedErrorException();
			}
		}.parse();
	}
	
	@Test(expected = ExpectedErrorException.class)
	public void parseSmallTriangleWithLetter() throws Exception {
		new Parser(new FileInputStream(new File("src/test/resources/smalltriangleletter.txt"))) {

			@Override
			protected void handleError(String error) {
				assertThat(error).startsWith("Entry 2 of line 3 could not be parsed as an integer: ");
				throw new ExpectedErrorException();
			}
		}.parse();
	}
	
	@Test(expected = ExpectedErrorException.class)
	public void parseEmptyFile() throws Exception {
		new Parser(new FileInputStream(new File("src/test/resources/emptyfile.txt"))) {

			@Override
			protected void handleError(String error) {
				assertThat(error).isEqualTo("No input lines");
				throw new ExpectedErrorException();
			}
		}.parse();
	}
}
