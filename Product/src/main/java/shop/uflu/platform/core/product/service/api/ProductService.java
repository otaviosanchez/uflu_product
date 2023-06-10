package shop.uflu.platform.core.product.service.api;

import java.util.List;

import shop.uflu.platform.core.product.exception.custom.NotFoundException;
import shop.uflu.platform.core.product.model.json.ProductTO;

public interface ProductService {
	
    void save(ProductTO json);

    ProductTO find(String providerId, String id) throws NotFoundException;
    
    List<ProductTO> findByProvider(String providerId) throws NotFoundException;
    
    List<ProductTO> findAll() throws NotFoundException;
}
