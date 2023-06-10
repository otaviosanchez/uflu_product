package shop.uflu.platform.core.product.repository.api;

import java.util.List;

import shop.uflu.platform.core.product.model.entity.Product;

public interface ProductRepository {
	
	void save(Product vo);
		
	Product findById(String providerId, String id);
	
	List<Product> findByProvider(String providerId);
	
	List<Product> findAll();
}
