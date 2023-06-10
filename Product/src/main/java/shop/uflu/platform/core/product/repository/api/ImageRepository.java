package shop.uflu.platform.core.product.repository.api;

import java.util.List;

import shop.uflu.platform.core.product.model.entity.Image;

public interface ImageRepository {

	void save(Image vo);

	List<Image> findByProviderProduct(String providerId, String productId);
}
