package se.atornblad.swolley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.charset.Charset;

import se.atornblad.swolley.io.PeekableReader;
import se.atornblad.swolley.json.InvalidJSONException;
import se.atornblad.swolley.json.JSONObject;
import se.atornblad.swolley.json.JSONValue;
import se.atornblad.swolley.swagger.Document;

public class Swolley {
	public static void main(String[] args) throws IOException, InvalidJSONException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		URL url = new URL(args[0]);
		try (InputStream input = url.openStream()) {
			try (InputStreamReader rawReader = new InputStreamReader(input, Charset.forName("UTF-8"))) {
				try (BufferedReader reader = new BufferedReader(rawReader)) {
					JSONObject obj = (JSONObject)JSONValue.read(new PeekableReader(reader));
					Document doc = obj.create(Document.class);
					System.out.println(doc.toString());
					System.out.println(obj.toJSON());
				}
			}
		}
	}
}
