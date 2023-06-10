package shop.uflu.platform.core.product.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import shop.uflu.platform.core.product.exception.custom.NotFoundException;
import shop.uflu.platform.core.product.model.entity.Product;
import shop.uflu.platform.core.product.model.json.ImageTO;
import shop.uflu.platform.core.product.model.json.ProductImageTO;
import shop.uflu.platform.core.product.model.json.ProductTO;
import shop.uflu.platform.core.product.repository.api.ProductRepository;
import shop.uflu.platform.core.product.service.api.ImageService;
import shop.uflu.platform.core.product.service.api.ProductService;

@ApplicationScoped
public class ProductServiceBean implements ProductService {

	@Inject
	private ProductRepository repository;
	
	@Inject
	private ImageService imageService;
	
	@Override
	public void save(ProductTO json) {
		String id = UUID.randomUUID().toString(); 
		Product entity = new Product();
		entity.setId(id);
		entity.setName(json.getName());
		entity.setInfo(json.getInfo());
		entity.setProviderId(json.getProviderId());
		entity.setProviderName(json.getProviderName());
		entity.setReference(json.getReference());
		entity.setTimestamp(Instant.now());
		entity.setValue(json.getValue());
		repository.save(entity);
	}

	@Override
	public ProductTO find(String providerId, String id) throws NotFoundException {
		ProductTO product = new ProductTO();
		Product entity = repository.findById(providerId, id);
		if (entity == null) {
			throw new NotFoundException();
		}
		product.setId(entity.getId());
		product.setName(entity.getName());
		product.setInfo(entity.getInfo());
		product.setProviderId(entity.getProviderId());
		product.setProviderName(entity.getProviderName());
		product.setReference(entity.getReference());
		product.setValue(entity.getValue());
		return product;
	}

	@Override
	public List<ProductTO> findByProvider(String providerId) throws NotFoundException {
		List<ProductTO> products = new ArrayList<ProductTO>();
		List<Product> entities = repository.findByProvider(providerId);
		if (entities == null || entities.size() == 0) {
			throw new NotFoundException();
		} else {
			for (Product entity : entities) {
				ProductTO product = new ProductTO();
				product.setId(entity.getId());
				product.setName(entity.getName());
				product.setInfo(entity.getInfo());
				product.setProviderId(entity.getProviderId());
				product.setProviderName(entity.getProviderName());
				product.setReference(entity.getReference());
//				product.setTimestamp(entity.getTimestamp());
				product.setValue(entity.getValue());
				products.add(product);
			}
		}
		return products;
	}

	@Override
	public List<ProductTO> findAll() throws NotFoundException {
		List<ProductTO> products = new ArrayList<ProductTO>();
		List<Product> entities = repository.findAll();
		if (entities == null || entities.size() == 0) {
			throw new NotFoundException();
		} else {
			for (Product entity : entities) {
				ProductTO product = new ProductTO();
				product.setId(entity.getId());
				product.setName(entity.getName());
				product.setInfo(entity.getInfo());
				product.setProviderId(entity.getProviderId());
				product.setProviderName(entity.getProviderName());
				product.setReference(entity.getReference());
				product.setValue(entity.getValue());				
				List<ProductImageTO> productImages = new ArrayList<ProductImageTO>();
				List<ImageTO> images = imageService.findByProviderProduct(product.getProviderId(), product.getId());
				for (ImageTO image : images) {
					ProductImageTO to = new ProductImageTO();
					to.setPath(image.getPath());
					productImages.add(to);
				}
				product.setImages(productImages);
				products.add(product);
			}
		}
		return products;
	}
	
}
