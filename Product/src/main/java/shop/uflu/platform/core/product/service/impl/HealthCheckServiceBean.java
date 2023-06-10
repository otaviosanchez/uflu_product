package shop.uflu.platform.core.product.service.impl;

import javax.enterprise.context.ApplicationScoped;

import shop.uflu.platform.core.product.service.api.HealthCheckService;

@ApplicationScoped
public class HealthCheckServiceBean implements HealthCheckService {

	@Override
	public String check() {
		return "OK";
	}

}
