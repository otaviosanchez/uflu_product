package shop.uflu.platform.core.product.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import shop.uflu.platform.core.product.exception.custom.NotFoundException;
import shop.uflu.platform.core.product.model.entity.Image;
import shop.uflu.platform.core.product.model.json.ImageTO;
import shop.uflu.platform.core.product.repository.api.ImageRepository;
import shop.uflu.platform.core.product.service.api.ImageService;

@ApplicationScoped
public class ImageServiceBean implements ImageService {

	@Inject
	private ImageRepository repository;
	
	@Override
	public void save(String providerId, String productId, String path) {
		String id = UUID.randomUUID().toString(); 
		Image entity = new Image();
		entity.setId(id);
		entity.setProviderId(providerId);
		entity.setProductId(productId);
		entity.setPath(path);
		entity.setTimestamp(Instant.now());
		repository.save(entity);
	}

	@Override
	public List<ImageTO> findByProviderProduct(String providerId, String productId) throws NotFoundException {
		List<ImageTO> images = new ArrayList<ImageTO>();
		List<Image> entities = repository.findByProviderProduct(providerId, productId);
		if (entities == null || entities.size() == 0) {
			throw new NotFoundException();
		} else {
			for (Image entity : entities) {
				ImageTO image = new ImageTO();
				image.setId(entity.getId());
				image.setProviderId(entity.getProviderId());
				image.setProductId(entity.getProductId());
				image.setPath(entity.getPath());
				images.add(image);
			}
		}
		return images;
	}
	
}
