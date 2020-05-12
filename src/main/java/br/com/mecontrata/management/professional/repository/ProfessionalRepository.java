package br.com.mecontrata.management.professional.repository;

import br.com.mecontrata.management.professional.entity.ProfessionalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalRepository extends MongoRepository<ProfessionalEntity, String> {

    ProfessionalEntity findProfessionalByEmailOrCpf(String email, String cpf);

    List<ProfessionalEntity> findProfessionalByProfessionalCategory(String category);
}
