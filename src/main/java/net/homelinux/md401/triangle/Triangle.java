package net.homelinux.md401.triangle;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.Lists;
import net.homelinux.md401.triangle.ISolvedNode.EndNode;
import net.homelinux.md401.triangle.ISolvedNode.SolvedNode;

public class Triangle {
	private Triangle(){}
	
	public static ISolvedNode solve(final ImmutableList<ImmutableList<Integer>> rows) {
		final List<ImmutableList<Integer>> rowsInReverseOrder = Lists.reverse(rows);
		final int lengthOfDummyRow = rowsInReverseOrder.get(0).size() + 1;
		ImmutableList<ISolvedNode> workingRow = ImmutableList.copyOf(Collections.<ISolvedNode>nCopies(lengthOfDummyRow, new EndNode()));
		for(ImmutableList<Integer> row: rowsInReverseOrder)
			workingRow = solveRow(row, workingRow);
		return workingRow.get(0); //we know it must have length 1 because input validation has already happened. Possibly would be nice to enforce this at the type level
	}
	
	private static ImmutableList<ISolvedNode> solveRow(final ImmutableList<Integer> row, final ImmutableList<ISolvedNode> nextRow) {
		final Builder<ISolvedNode> toReturn = ImmutableList.builder();
		for (int i = 0; i < row.size(); i++) {
			final ISolvedNode leftSolvedNode = nextRow.get(i);
			final ISolvedNode rightSolvedNode = nextRow.get(i + 1);
			final ISolvedNode bestNextNode = leftSolvedNode.costFromHereOn() <= rightSolvedNode.costFromHereOn() ? leftSolvedNode : rightSolvedNode;
			toReturn.add(new SolvedNode(row.get(i), bestNextNode));
		}
		return toReturn.build();
	}
}
