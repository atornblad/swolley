package se.atornblad.swolley.strings;

import java.util.regex.Matcher;

@FunctionalInterface
public interface StringReplacerCallback {
	public String replace(Matcher match);
}
