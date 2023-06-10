package shop.uflu.platform.core.product.service.api;

import java.util.List;

import shop.uflu.platform.core.product.exception.custom.NotFoundException;
import shop.uflu.platform.core.product.model.json.ImageTO;

public interface ImageService {
	
    void save(String providerId, String productId, String path);

    List<ImageTO> findByProviderProduct(String providerId, String productId) throws NotFoundException;
}
