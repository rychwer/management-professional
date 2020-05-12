package br.com.mecontrata.management.professional.service.impl;

import br.com.mecontrata.management.professional.domain.ProfessionalDTO;
import br.com.mecontrata.management.professional.domain.ProfessionalDetailDTO;
import br.com.mecontrata.management.professional.entity.PhotoEntity;
import br.com.mecontrata.management.professional.entity.ProfessionalEntity;
import br.com.mecontrata.management.professional.facade.exception.UploadFileException;
import br.com.mecontrata.management.professional.mapper.ProfessionalMapper;
import br.com.mecontrata.management.professional.repository.ProfessionalRepository;
import br.com.mecontrata.management.professional.service.ProfessionalService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ProfessionalServiceImpl implements ProfessionalService {

    private ProfessionalRepository professionalRepository;
    private ProfessionalMapper professionalMapper;

    public ProfessionalServiceImpl(ProfessionalRepository professionalRepository, ProfessionalMapper professionalMapper) {
        this.professionalRepository = professionalRepository;
        this.professionalMapper = professionalMapper;
    }

    @Override
    @Transactional
    public ProfessionalDTO hasProfessionalEmailAndCpf(String email, String cpf) {
        final ProfessionalEntity professionalEntity = professionalRepository.findProfessionalByEmailOrCpf(email, cpf);
        return professionalMapper.toProfessionalDTO(professionalEntity);
    }

    @Override
    @Transactional
    public ProfessionalDTO createProfessional(ProfessionalDTO professionalDTO) {
        final ProfessionalEntity professionalEntity = professionalMapper.toProfessionalEntity(professionalDTO);
        return professionalMapper.toProfessionalDTO(this.professionalRepository.save(professionalEntity));
    }

    @Override
    @Transactional
    public ProfessionalDTO getProfessional(String email) {
        AtomicReference<ProfessionalDTO> professionalDTO = new AtomicReference<>(null);
        final Optional<ProfessionalEntity> optionalProfessionalEntity = this.professionalRepository.findById(email);
        optionalProfessionalEntity.ifPresent(item -> professionalDTO.set(professionalMapper.toProfessionalDTO(item)));
        return professionalDTO.get();
    }

    @Override
    @Transactional
    public void deleteProfessional(String email) {
        this.professionalRepository.deleteById(email);
    }

    @Override
    public List<ProfessionalDetailDTO> listProfessional(String category) {
        return professionalMapper.toListProfessionalDetailDTO(this.professionalRepository.findProfessionalByProfessionalCategory(category));
    }

    @Override
    public void uploadPhotoProfileAndPortifolio(MultipartFile photoProfile, List<MultipartFile> portifolio, String email) {
        final Optional<ProfessionalEntity> optionalEntity = this.professionalRepository.findById(email);
        final ProfessionalEntity entity = optionalEntity.get();
        entity.setProfilePhoto(createPhoto(photoProfile));
        entity.setPortifolio(portifolio.stream().map(item -> createPhoto(item)).collect(Collectors.toList()));
        this.professionalRepository.save(entity);
    }

    private PhotoEntity createPhoto(MultipartFile multipartFile) {
        try {
            final PhotoEntity photoEntity = new PhotoEntity();
            photoEntity.setPhotoTitle(multipartFile.getName());
            photoEntity.setPhoto(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
            return photoEntity;
        } catch (IOException e) {
            throw new UploadFileException("Erro ao carregar arquivo: ", e);
        }
    }

}
