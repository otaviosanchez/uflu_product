package shop.uflu.platform.core.product.model.json;

import java.io.Serializable;
import java.util.List;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ProductTO implements Serializable {

	private static final long serialVersionUID = 5769371120405434201L;

	private String providerId;

	private String providerName;
	
	private String id;
	
	private String name;
	
	private Double value;
	
	private String reference;
	
	private String info;

	private String timestamp;
	
	private List<ProductImageTO> images;

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public List<ProductImageTO> getImages() {
		return images;
	}

	public void setImages(List<ProductImageTO> images) {
		this.images = images;
	}

}
