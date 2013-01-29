package net.homelinux.md401.triangle;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

interface ISolvedNode {

	String toDescription(ImmutableList<Integer> pathUpToHere);

	int costFromHereOn();

	static class SolvedNode implements ISolvedNode {
		final int cost;
		final int totalFromHereOn;
		final ISolvedNode next;

		public SolvedNode(int cost, ISolvedNode next) {
			this.cost = cost;
			this.next = next;
			this.totalFromHereOn = cost + next.costFromHereOn();
		}

		public String toDescription(ImmutableList<Integer> pathUpToHere) {
			Builder<Integer> path = ImmutableList.builder();
			path.addAll(pathUpToHere);
			path.add(cost);
			return next.toDescription(path.build());
		}

		public int costFromHereOn() {
			return totalFromHereOn;
		}
	}

	static class EndNode implements ISolvedNode {
		public String toDescription(ImmutableList<Integer> pathUpToHere) {
			// XXX: is there really no sum function in Java? I've been writing scala too long
			int total = 0;
			for (int i : pathUpToHere)
				total += i;
			return StringUtils.join(pathUpToHere, " + ") + " = " + total;
		}

		public int costFromHereOn() {
			return 0;
		}
	}
}
