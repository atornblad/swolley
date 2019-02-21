package se.atornblad.swolley.swagger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Path {
	private Action get;
	private Action head;
	private Action post;
	private Action put;
	private Action delete;
	private Action options;
	private Action patch;
	
	public Action getGet() {
		return get;
	}
	
	public void setGet(Action get) {
		this.get = get;
	}
	
	public Action getHead() {
		return head;
	}
	
	public void setHead(Action head) {
		this.head = head;
	}
	
	public Action getPost() {
		return post;
	}
	
	public void setPost(Action post) {
		this.post = post;
	}
	
	public Action getPut() {
		return put;
	}
	
	public void setPut(Action put) {
		this.put = put;
	}
	
	public Action getDelete() {
		return delete;
	}
	
	public void setDelete(Action delete) {
		this.delete = delete;
	}
	
	public Action getOptions() {
		return options;
	}
	
	public void setOptions(Action options) {
		this.options = options;
	}
	
	public Action getPatch() {
		return patch;
	}
	
	public void setPatch(Action patch) {
		this.patch = patch;
	}

	public boolean hasOnlyOneMethod() {
		List<Action> actions = Arrays.asList(get, head, post, put, delete, options, patch);
		Stream<Action> stream = actions.stream();
		Stream<Action> filtered = stream.filter(a -> a != null);
		long count = filtered.count();
		return count == 1;
//		return Arrays.asList(get, head, post, put, delete, options, patch).stream().filter(a -> a != null).count() == 1;
	}
	
	
}
