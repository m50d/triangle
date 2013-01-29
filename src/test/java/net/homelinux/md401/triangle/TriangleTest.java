package net.homelinux.md401.triangle;

import org.junit.Test;
import static org.fest.assertions.Assertions.assertThat;

public class TriangleTest {
	@Test
	public void expectedLengthForSmallExampleTriangle() {
		assertThat(Triangle.solve(ParserTest.SMALL_TRIANGLE).costFromHereOn()).isEqualTo(18);
	}
}
