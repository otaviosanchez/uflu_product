package shop.uflu.platform.core.product.exception.custom;

import java.io.Serializable;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class NotFoundException extends Exception implements Serializable {

	private static final long serialVersionUID = 8657205494503394771L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String msg) {
		super(msg);
	}

	public NotFoundException(String msg, Exception e) {
		super(msg, e);
	}

}
