package br.com.mecontrata.management.professional.mapper;

import br.com.mecontrata.management.professional.domain.ProfessionalDTO;
import br.com.mecontrata.management.professional.domain.ProfessionalDetailDTO;
import br.com.mecontrata.management.professional.entity.ProfessionalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessionalMapper {

    ProfessionalDTO toProfessionalDTO(ProfessionalEntity entity);

    ProfessionalEntity toProfessionalEntity(ProfessionalDTO professionalDTO);

    @Mapping(target = "profilePhoto", ignore = true)
    @Mapping(target = "portifolio", ignore = true)
    @Mapping(target = "thumbnail", ignore = true)
    ProfessionalDetailDTO toProfessionalDetailDTO(ProfessionalEntity entity);

    List<ProfessionalDetailDTO> toListProfessionalDetailDTO(List<ProfessionalEntity> entityList);

}
