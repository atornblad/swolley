package se.atornblad.swolley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;

import se.atornblad.swolley.io.PeekableReader;
import se.atornblad.swolley.json.InvalidJSONException;
import se.atornblad.swolley.json.JSONObject;
import se.atornblad.swolley.json.JSONValue;

public class Swolley {
	public static void main(String[] args) throws IOException, InvalidJSONException {
		/*URL url = new URL("https://helsoslingan-server.azurewebsites.net/v2/api-docs");
		try (InputStream input = url.openStream()) {
			try (InputStreamReader rawReader = new InputStreamReader(input, Charset.forName("UTF-8"))) {
				try (BufferedReader reader = new BufferedReader(rawReader)) {
					JSONObject obj = (JSONObject)JSONValue.read(new PeekableReader(reader));
					System.out.println(obj.toJSON());
				}
			}
		}*/
		
/*		StringReader reader = new StringReader("{\"key\":{\"foo\":\"bar\",\"hey\":\"ho\"},\"nyckel\":\"v�rde\"}");
		JSONValue value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());*/
		
		StringReader reader = new StringReader("0");
		JSONValue value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader("1");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader("2");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader(".0");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader(".1");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader(".2");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader("0.0");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader("0.1");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader("0.2");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader("1.0");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader("1.1");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader("1.2");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader("1.234");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader("-1.234");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader("-1.234e+2");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader("-123.456e-3");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
		
		reader = new StringReader("[0,+123.456e-3,45.67e8,NaN,1.3452346236345734573845]");
		value = JSONValue.read(new PeekableReader(reader));
		System.out.println(value.toJSON());
	}

}
