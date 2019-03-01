package se.atornblad.swolley.json;

import java.io.IOException;
import java.io.InvalidClassException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import se.atornblad.swolley.io.PeekableReader;

public class JSONObject extends JSONValue {
	private HashMap<String, JSONValue> properties;
	private List<String> orderedPropertyNames;
	
	public JSONObject() {
		properties = new HashMap<>();
		orderedPropertyNames = new ArrayList<>();
	}
	
	public void set(String key, JSONValue value) {
		if (!properties.containsKey(key)) {
			orderedPropertyNames.add(key);
		}
		properties.put(key, value);
	}
	
	public JSONValue get(String key) {
		return properties.get(key);
	}
	
	public Set<String> getPropertyNames() {
		return properties.keySet();
	}
	
	public boolean containsKey(String key) {
		return properties.containsKey(key);
	}

	public static JSONObject readObject(PeekableReader peeker) throws IOException, InvalidJSONException {
		JSONObject obj = new JSONObject();
		
		peeker.skipWhitespace();
		
		Character bracket = peeker.peek();
		if (bracket == null) {
			throw new InvalidJSONException("Unexpected end of stream - expected start of object");
		}
		bracket = peeker.readChar();
		if (bracket != '{') {
			throw new InvalidJSONException("Unexpected character '" + bracket + "' - expected start of object");
		}
		
		peeker.skipWhitespace();
		while (peeker.peek() != '}') {
			JSONString key = JSONString.readString(peeker);
			peeker.skipWhitespace();
			if (peeker.peek() == null) {
				throw new InvalidJSONException("Unexpected end of stream - expected colon");
			}
			if (peeker.readChar() != ':') {
				throw new InvalidJSONException("Unexpected character '" + peeker.peek() + "' - expected colon");
			}
			peeker.skipWhitespace();
			JSONValue value = JSONValue.read(peeker);
			obj.set(key.toString(), value);
			peeker.skipWhitespace();
			if (peeker.peek() == ',') {
				peeker.readChar();
				peeker.skipWhitespace();
			}
		}
		
		// Read final } character
		peeker.readChar();
		
		return obj;
	}
	
	@Override
	public String toString() {
		return this.toJSON();
	}

	@Override
	public String toJSON(int indentation) {
		if (properties.size() == 0) return "{}";
		StringBuilder builder = new StringBuilder();
		builder.append("{\n");
		Iterator<String> keyIter = orderedPropertyNames.iterator();
		while (keyIter.hasNext()) {
			String key = keyIter.next();
			builder.append(String.format("%" + ((indentation + 1) * 2) + "s", ""));
			builder.append((new JSONString(key)).toJSON());
			builder.append(" : ");
			builder.append(properties.get(key).toJSON(indentation + 1));
			if (keyIter.hasNext()) {
				builder.append(",\n");
			}
		}
		builder.append("\n");
		if (indentation > 0) builder.append(String.format("%" + (indentation * 2) + "s", ""));
		builder.append("}");
		
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof JSONObject)) return false;
		JSONObject o = (JSONObject)obj;
		Set<String> remainingKeys = o.getPropertyNames();
		for (String key : properties.keySet()) {
			if (remainingKeys.contains(key)) {
				remainingKeys.remove(key);
				if (!get(key).equals(o.get(key))) return false;
			}
			else {
				return false;
			}
		}
		return remainingKeys.isEmpty();
	}
	
	public <T> T create(Class<T> theClass) throws InvalidClassException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		T obj = theClass.newInstance();
		
		Method[] methods = Arrays.stream(theClass.getMethods())
				                 .filter(m -> m.getName().startsWith("set"))
				                 .filter(m -> m.getName().length() >= 4)
				                 .filter(m -> Character.isUpperCase(m.getName().charAt(3)) || m.getName().charAt(3) == '$')
				                 .filter(m -> m.getReturnType().equals(Void.TYPE))
				                 .filter(m -> m.getParameterCount() == 1)
				                 .toArray(Method[]::new);
		
		for (Method m : methods) {
			String name = m.getName().substring(3);
			name = name.substring(0, 1).toLowerCase() + name.substring(1);
			if (containsKey(name)) {
				Parameter param = m.getParameters()[0];
				m.invoke(obj, createParameter(get(name), param.getParameterizedType(), name));
			}
		}
		
		return obj;
	}

	private Object createParameter(JSONValue jsonValue, Type type, String parameterName) throws InvalidClassException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		if (jsonValue == null || jsonValue instanceof JSONNull) {
			return null;
		}
		
		if (type instanceof ParameterizedType) {
			return createParameterizedTypeParameter(jsonValue, type, parameterName);
		}
		
		else if (type instanceof Class<?>) {
			return createSimpleTypeParameter(jsonValue, type, parameterName);
		}
		
		else {
			throw new InvalidClassException("Parameter " + parameterName + " requires a " + type.getTypeName() + " value");
		}
	}

	private Object createSimpleTypeParameter(JSONValue jsonValue, Type type, String parameterName)
			throws InvalidClassException, InstantiationException, IllegalAccessException, InvocationTargetException,
			ClassNotFoundException {
		Class<?> cls = (Class<?>)type;
		
		if (cls.isAssignableFrom(String.class) && jsonValue instanceof JSONString) {
			return ((JSONString)jsonValue).getValue();
		}
		
		if (jsonValue instanceof JSONObject) {
			JSONObject obj = (JSONObject)jsonValue;
			return obj.create(cls);
		}
		
		if (jsonValue instanceof JSONBoolean && (cls.equals(Boolean.class) || cls.isPrimitive())) {
			JSONBoolean bool = (JSONBoolean)jsonValue;
			return bool.getValue();
		}
		
		if (cls.isArray() && jsonValue instanceof JSONArray) {
			JSONArray arr = (JSONArray)jsonValue;
			
			Object typedArray = Array.newInstance(cls.getComponentType(), arr.size());
			for (int i = 0; i < arr.size(); ++i) {
				Array.set(typedArray, i, createParameter(arr.get(i), cls.getComponentType(), parameterName + "[" + i + "]"));
			}
			
			return typedArray;
		}
		
		throw new InvalidClassException("Parameter " + parameterName + " requires a " + cls.getSimpleName() + " value");
	}

	private Object createParameterizedTypeParameter(JSONValue jsonValue, Type type, String parameterName)
			throws InvalidClassException, InstantiationException, IllegalAccessException, InvocationTargetException,
			ClassNotFoundException {
		ParameterizedType ptype = (ParameterizedType)type;
		
		Type rawType = ptype.getRawType();
		if (rawType.getTypeName().equals("java.util.Map") && jsonValue instanceof JSONObject) {
			JSONObject obj = (JSONObject)jsonValue;
			Type keyType = ptype.getActualTypeArguments()[0];
			Type valueType = ptype.getActualTypeArguments()[1];
			
			if (keyType.equals(String.class)) {
				HashMap<String, Object> map = new HashMap<>();
				for (String key : obj.getPropertyNames()) {
					map.put(key, createParameter(obj.get(key), valueType, parameterName + "[\"" + key + "\"]"));
				}
				return map;
			}
		}
		
		throw new InvalidClassException("Parameter " + parameterName + " requires a " + ptype.getTypeName() + " value (ParameterizedType)");
	}
}
