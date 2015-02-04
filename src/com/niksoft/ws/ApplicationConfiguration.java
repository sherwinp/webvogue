package com.niksoft.ws;

import java.util.Set;
import java.util.HashSet;
import javax.ws.rs.core.Application;

public class ApplicationConfiguration extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public ApplicationConfiguration() {
		singletons.add(new VoucherResource());
		singletons.add(new UserResource());
		singletons.add(new PayForItem());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}