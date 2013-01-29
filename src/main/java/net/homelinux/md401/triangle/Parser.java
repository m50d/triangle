package net.homelinux.md401.triangle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public abstract class Parser {
	private final InputStream is;

	public Parser(final InputStream is) {
		this.is = is;
	}

	public ImmutableList<ImmutableList<Integer>> parse() {
		final List<String> rows;
		try {
			// Yes, we really do want to use the system default encoding,
			// because we're reading from stdin
			rows = IOUtils.readLines(is);
		} catch (IOException e) {
			// If we get an error reading from stdin that's almost certainly
			// unrecoverable, and not likely to be the result of user error
			throw new RuntimeException(e);
		}
		if(rows.isEmpty()) handleError("No input lines");
		Iterator<String> rowIterator = rows.iterator();
		Builder<ImmutableList<Integer>> toReturn = ImmutableList.builder();
		for (int i = 1; rowIterator.hasNext(); i++) {
			String[] entries = StringUtils.split(rowIterator.next(), ' ');
			if (i != entries.length)
				handleError("Wrong number of entries in line " + i + ", should be " + i + " but was " + entries.length);
			Builder<Integer> parsedEntries = ImmutableList.builder();
			for (int j = 0; j < entries.length; j++)
				try {
					parsedEntries.add(Integer.valueOf(entries[j]));
				} catch (NumberFormatException nfe) {
					handleError("Entry " + (j + 1) + " of line " + i + " could not be parsed as an integer: " + nfe);
				}
			toReturn.add(parsedEntries.build());
		}
		return toReturn.build();
	}

	// At the moment the only implementation calls System.exit.
	// Feel free to change the API if you need different semantics for when this
	// returns successfully
	protected abstract void handleError(String error);
}
