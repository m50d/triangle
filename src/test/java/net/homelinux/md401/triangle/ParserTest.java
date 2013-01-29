package net.homelinux.md401.triangle;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class ParserTest {
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
		assertThat(parser.parse()).isEqualTo(il(il(7), il(6,3), il(3,8,5),il(11,2,10,9)));
	}
}
