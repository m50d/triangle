package net.homelinux.md401.triangle;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public abstract class Parser {
	public Parser(final InputStream is) {
		try {
			//Yes, we really do want to use the system default encoding, because we're reading from stdin
			IOUtils.readLines(is);
		} catch (IOException e) {
			//If we get an error reading from stdin that's almost certainly unrecoverable
			throw new RuntimeException(e);
		}
	}
}
