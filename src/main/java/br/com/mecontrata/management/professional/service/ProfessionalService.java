package br.com.mecontrata.management.professional.service;

import br.com.mecontrata.management.professional.domain.ProfessionalDTO;
import br.com.mecontrata.management.professional.domain.ProfessionalDetailDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProfessionalService {

    ProfessionalDTO hasProfessionalEmailAndCpf(String email, String cpf);

    ProfessionalDTO createProfessional(ProfessionalDTO professionalDTO);

    ProfessionalDTO getProfessional(String email);

    void deleteProfessional(String email);

    List<ProfessionalDetailDTO> listProfessional(String cetegory);

    void uploadPhotoProfileAndPortifolio(MultipartFile photoProfile, List<MultipartFile> portifolio, String email);

}
