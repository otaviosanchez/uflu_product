package shop.uflu.platform.core.product.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;

import shop.uflu.platform.core.product.model.entity.Product;
import shop.uflu.platform.core.product.repository.api.ProductRepository;

@ApplicationScoped
public class ProductRepositoryBean implements ProductRepository {

	@Inject
	private QuarkusCqlSession session;

	@Override
	public void save(Product vo) {
		try {
			PreparedStatement prepared = session.prepare(
					"insert into product.product (id, info, name, provider_id, provider_name, reference, value, timestamp) "
					+ "values (?,?,?,?,?,?,?,?)");
			BoundStatement bound = prepared.bind(
					vo.getId(), 
					vo.getInfo(),
					vo.getName(), 
					vo.getProviderId(),
					vo.getProviderName(),
					vo.getReference(), 
					vo.getValue(), 
					vo.getTimestamp());
			session.execute(bound);
		} catch (Exception e) {
			System.err.println("ProductRepository.save.Exception: " + e.getMessage());
		}
	}

	@Override
	public Product findById(String providerId, String id) {
		Product vo = null;
		try {
			PreparedStatement prepared = session.prepare("SELECT * FROM product.product WHERE provider_id = ? AND id = ?");
			BoundStatement bound = prepared.bind(providerId, id);
			Row row = session.execute(bound).one();
			if (row != null && row.size() > 0) {
				vo = new Product();
				vo.setId(row.getString("id"));
				vo.setName(row.getString("name"));
				vo.setInfo(row.getString("info"));
				vo.setProviderId(row.getString("provider_id"));
				vo.setProviderName(row.getString("provider_name"));
				vo.setReference(row.getString("reference"));
				vo.setTimestamp(row.getInstant("timestamp"));
				vo.setValue(row.getDouble("value"));
			}
		} catch (Exception e) {
			System.err.println("ProductRepository.findById.Exception: " + e.getMessage());
		}
		return vo;
	}
	
	@Override
	public List<Product> findByProvider(String providerId) {
		List<Product> products = null;
		try {
			PreparedStatement prepared = session.prepare("SELECT * FROM product.product WHERE provider_id = ?");
			BoundStatement bound = prepared.bind(providerId);
			List<Row> rows = session.execute(bound).all();
			if (rows != null && rows.size() > 0) {
				products = new ArrayList<Product>();
				for (Row row : rows) {
					Product vo = new Product();
					vo.setId(row.getString("id"));
					vo.setName(row.getString("name"));
					vo.setInfo(row.getString("info"));
					vo.setProviderId(row.getString("provider_id"));
					vo.setProviderName(row.getString("provider_name"));
					vo.setReference(row.getString("reference"));
					vo.setTimestamp(row.getInstant("timestamp"));
					vo.setValue(row.getDouble("value"));
					products.add(vo);
				}
			}
		} catch (Exception e) {
			System.err.println("ProductRepository.findByProvider.Exception: " + e.getMessage());
		}
		return products;
	}

	@Override
	public List<Product> findAll() {
		List<Product> products = null;
		try {
			PreparedStatement prepared = session.prepare("SELECT * FROM product.product");
			BoundStatement bound = prepared.bind();
			List<Row> rows = session.execute(bound).all();
			if (rows != null && rows.size() > 0) {
				products = new ArrayList<Product>();
				for (Row row : rows) {
					Product vo = new Product();
					vo.setId(row.getString("id"));
					vo.setName(row.getString("name"));
					vo.setInfo(row.getString("info"));
					vo.setProviderId(row.getString("provider_id"));
					vo.setProviderName(row.getString("provider_name"));
					vo.setReference(row.getString("reference"));
					vo.setTimestamp(row.getInstant("timestamp"));
					vo.setValue(row.getDouble("value"));
					products.add(vo);
				}
			}
		} catch (Exception e) {
			System.err.println("ProductRepository.findAll.Exception: " + e.getMessage());
		}
		return products;
	}

}
