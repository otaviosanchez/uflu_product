package shop.uflu.platform.core.product.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;

import shop.uflu.platform.core.product.model.entity.Image;
import shop.uflu.platform.core.product.repository.api.ImageRepository;

@ApplicationScoped
public class ImageRepositoryBean implements ImageRepository {

	@Inject
	private QuarkusCqlSession session;

	@Override
	public void save(Image vo) {
		try {
			PreparedStatement prepared = session.prepare(
					"insert into product.image (provider_id, product_id, id, path, timestamp) "
					+ "values (?,?,?,?,?,?,?,?)");
			BoundStatement bound = prepared.bind(
					vo.getProviderId(), 
					vo.getProductId(),
					vo.getId(),
					vo.getPath(), 
					vo.getTimestamp());
			session.execute(bound);
		} catch (Exception e) {
			System.err.println("ProductImageRepository.save.Exception: " + e.getMessage());
		}
	}

	@Override
	public List<Image> findByProviderProduct(String providerId, String productId) {
		List<Image> products = null;
		try {
			PreparedStatement prepared = session.prepare("SELECT * FROM product.image WHERE provider_id = ? AND product_id = ?");
			BoundStatement bound = prepared.bind(providerId, productId);
			List<Row> rows = session.execute(bound).all();
			if (rows != null && rows.size() > 0) {
				products = new ArrayList<Image>();
				for (Row row : rows) {
					Image vo = new Image();
					vo.setProviderId(row.getString("provider_id"));
					vo.setProductId(row.getString("product_id"));
					vo.setId(row.getString("id"));
					vo.setPath(row.getString("path"));
					vo.setTimestamp(row.getInstant("timestamp"));
					products.add(vo);
				}
			}
		} catch (Exception e) {
			System.err.println("ProductImageRepository.findByProviderProduct.Exception: " + e.getMessage());
		}
		return products;
	}

}
