package shop.uflu.platform.core.product.model.json;

import java.io.Serializable;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ProductImageTO implements Serializable {

	private static final long serialVersionUID = -8822258408405896108L;
	
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

 }
