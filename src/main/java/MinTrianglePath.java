import com.google.common.collect.ImmutableList;

import net.homelinux.md401.triangle.Parser;
import net.homelinux.md401.triangle.Triangle;

public class MinTrianglePath {
	public static void main(String[] args) {
		final ImmutableList<ImmutableList<Integer>> triangle = new Parser(System.in) {
			
			@Override
			protected void handleError(String error) {
				System.err.println(error);
				System.exit(1);
			}
		}.parse();
		System.out.println("Minimal path is: " + Triangle.solve(triangle).toDescription(ImmutableList.<Integer>of()));
	}
}
