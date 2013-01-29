package net.homelinux.md401.triangle;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.ImmutableList;

class EndNode {
	String toDescription(ImmutableList<SolvedNode> pathUpToHere) {
		return StringUtils.join(pathUpToHere, " + ") + " = " + 0;
	}
}
