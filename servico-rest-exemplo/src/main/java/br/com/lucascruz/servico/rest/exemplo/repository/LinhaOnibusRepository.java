package br.com.lucascruz.servico.rest.exemplo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.lucascruz.servico.rest.exemplo.bean.LinhaOnibus;

@RepositoryRestResource
public interface LinhaOnibusRepository extends MongoRepository{

	public LinhaOnibus findByLinha(String linha);
	
}
