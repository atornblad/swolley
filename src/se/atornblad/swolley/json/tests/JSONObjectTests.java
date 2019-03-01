package se.atornblad.swolley.json.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

import se.atornblad.swolley.io.PeekableReader;
import se.atornblad.swolley.json.InvalidJSONException;
import se.atornblad.swolley.json.JSONBoolean;
import se.atornblad.swolley.json.JSONNull;
import se.atornblad.swolley.json.JSONNumber;
import se.atornblad.swolley.json.JSONObject;
import se.atornblad.swolley.json.JSONString;

class JSONObjectTests {

	private JSONObject read(String value) throws IOException, InvalidJSONException {
		try (StringReader reader = new StringReader(value)) {
			try (PeekableReader peeker = new PeekableReader(reader)) {
				return JSONObject.readObject(peeker);
			}
		}
	}
	
	@Test
	public void testToJSONEmpty() {
		JSONObject obj = new JSONObject();
		String json = obj.toJSON();
		assertEquals("{}", json);
	}
	
	@Test
	public void testToJSONOneKey() {
		JSONObject obj = new JSONObject();
		obj.set("foo", new JSONString("bar"));
		String json = obj.toJSON();
		assertEquals("{\n  \"foo\" : \"bar\"\n}", json);
	}
	
	@Test
	public void testToJSONTwoKeys() {
		JSONObject obj = new JSONObject();
		obj.set("foo", new JSONString("bar"));
		obj.set("num", new JSONNumber(123));
		String json = obj.toJSON();
		assertEquals("{\n  \"foo\" : \"bar\",\n  \"num\" : 123.0\n}", json);
	}
	
	@Test
	public void testToJSONSubObject() {
		JSONObject obj = new JSONObject();
		JSONObject sub = new JSONObject();
		obj.set("foo", new JSONString("bar"));
		obj.set("sub", sub);
		sub.set("one",  new JSONBoolean(true));
		sub.set("two",  new JSONNull());
		sub.set("three", new JSONNumber(1));
		String json = obj.toJSON();
		assertEquals("{\n  \"foo\" : \"bar\",\n  \"sub\" : {\n    \"one\" : true,\n    \"two\" : null,\n    \"three\" : 1.0\n  }\n}", json);
	}

	@Test
	public void testReadObject() throws IOException, InvalidJSONException {
		JSONObject obj = read("{\"foo\":\"bar\"}");
		assertEquals(1, obj.getPropertyNames().size(), "Should have only one key");
		assertEquals("bar", obj.get("foo").toString());
	}

}
