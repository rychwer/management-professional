package br.com.mecontrata.management.professional.facade;

import br.com.mecontrata.management.professional.domain.ProfessionalDTO;
import br.com.mecontrata.management.professional.domain.ProfessionalDetailDTO;
import br.com.mecontrata.management.professional.facade.exception.EmailNotConfirmedException;
import br.com.mecontrata.management.professional.facade.exception.ProfessionalAlreadyExists;
import br.com.mecontrata.management.professional.facade.exception.ProfessionalNotFoundException;
import br.com.mecontrata.management.professional.service.ProfessionalService;
import br.com.server.resource.domain.LoginDTO;
import br.com.server.resource.service.AuthorizationService;
import br.com.server.resource.service.KafkaService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfessionalFacade {

    private ProfessionalService professionalService;
    private MessageSource messageSource;
    private AuthorizationService authorizationService;
    private KafkaService kafkaService;
    //private ReceitaFederalService receitaFederalService;

    public ProfessionalFacade(ProfessionalService professionalService, MessageSource messageSource,
                              AuthorizationService authorizationService,
                              @Qualifier("emailNotification") KafkaService kafkaService) {
        this.professionalService = professionalService;
        this.messageSource = messageSource;
        this.authorizationService = authorizationService;
        this.kafkaService = kafkaService;
        //this.receitaFederalService = receitaFederalService;
    }

    public ProfessionalDTO createProfessional(ProfessionalDTO professionalDTO) {
        final ProfessionalDTO findProfessional = professionalService.hasProfessionalEmailAndCpf(professionalDTO.getEmail(), professionalDTO.getCpf());

        if(!ObjectUtils.isEmpty(findProfessional)) {
            if(!findProfessional.getEmailConfirmed()) {
                throw new EmailNotConfirmedException(messageSource.getMessage("error.professional.email.not.confirmed", null, null));
            } else {
                throw new ProfessionalAlreadyExists(messageSource.getMessage("error.professional.already.exists", null, null));
            }
        }

        //TODO wrapper de criação do profissional
        authorizationService.createProfessionalLogin(new LoginDTO(professionalDTO.getEmail(), professionalDTO.getPassword()));

        final ProfessionalDTO professional = professionalService.createProfessional(professionalDTO);

        //TODO Verificar a necessidade e se será API ou KAFKA
        //this.receitaFederalService.checaDadosReceitaFederal(new ReceitaFederalDTO(professional.getFirstName(), professional.getLastName(), professional.getCpf()));;
        //kafkaService.sendNotification(professional);

        return professional;
    }

    public ProfessionalDTO getProfessional(String email) {

        final ProfessionalDTO professional = professionalService.getProfessional(email);
        if(ObjectUtils.isEmpty(professional)) {
            throw new ProfessionalNotFoundException(messageSource.getMessage("error.professional.not.found", null, null));
        }

        return professional;
    }

    public void deleteProfessional(String email) {
        final ProfessionalDTO professional = professionalService.getProfessional(email);
        if(ObjectUtils.isEmpty(professional)) {
            throw new ProfessionalNotFoundException(messageSource.getMessage("error.professional.not.found", null, null));
        }

        professionalService.deleteProfessional(email);
    }

    public List<ProfessionalDetailDTO> listProfessional(String categoria) {
        final List<ProfessionalDetailDTO> professionalDetailDTOS = this.professionalService.listProfessional(categoria);
        if(ObjectUtils.isEmpty(professionalDetailDTOS)) {
            return new ArrayList<>();
        }
        return professionalDetailDTOS;
    }

    public void uploadPhotoProfileAndPortifolio(MultipartFile photoProfile, List<MultipartFile> portifolio, String email) {
        final ProfessionalDTO professional = professionalService.getProfessional(email);
        if(ObjectUtils.isEmpty(professional)) {
            throw new ProfessionalNotFoundException(messageSource.getMessage("error.professional.not.found", null, null));
        }
        this.professionalService.uploadPhotoProfileAndPortifolio(photoProfile, portifolio, email);
    }

}
