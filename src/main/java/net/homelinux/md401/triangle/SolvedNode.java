package net.homelinux.md401.triangle;

class SolvedNode {
	final int index;
	final int cost;
	final int costAfter;
	final int nextIndex;
	
	SolvedNode(int index, int cost, int costAfter, int nextIndex) {
		this.index = index;
		this.cost = cost;
		this.costAfter = costAfter;
		this.nextIndex = nextIndex;
	}
}
