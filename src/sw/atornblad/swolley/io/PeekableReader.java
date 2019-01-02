package sw.atornblad.swolley.io;

import java.io.IOException;
import java.io.Reader;
import java.util.Stack;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PeekableReader extends Reader {
	private Reader reader;
	private Character peeked;
	
	public PeekableReader(Reader reader) {
		this.reader = reader;
		this.peeked = null;
	}

	@Override
	public void close() throws IOException {
		reader.close();
		reader = null;
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		int add = 0;
		
		if (peeked != null && len >= 1) {
			cbuf[off] = peeked;
			off++;
			len--;
			add++;
			peeked = null;
		}
		
		return add + reader.read(cbuf, off, len);
	}
	
	public char readChar() throws IOException {
		if (peeked != null) {
			char output = peeked;
			peeked = null;
			return output;
		}
		
		int charCode = reader.read();
		if (charCode == -1) {
			throw new java.io.EOFException();
		}
		else {
			return (char)charCode;
		}
	}
	
	public char readEscapedChar() throws IOException {
		char c = this.readChar();
		if (c == '\\') {
			throw new NotImplementedException();
		}
		else {
			return c;
		}
	}
	
	public void skipWhitespace() throws IOException {
		Character peeked = this.peek();
		while (peeked != null && Character.isWhitespace(peeked)) {
			peeked = this.readChar();
			peeked = this.peek();
		}
	}
	
	public Character peek() throws IOException {
		if (peeked != null) {
			return peeked;
		}
		
		int charCode = reader.read();
		if (charCode == -1) {
			return null;
		}
		else {
			peeked = (char)charCode;
			return peeked;
		}
	}
}
