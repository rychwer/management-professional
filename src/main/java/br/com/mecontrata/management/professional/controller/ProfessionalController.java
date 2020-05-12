package br.com.mecontrata.management.professional.controller;

import br.com.mecontrata.management.professional.domain.ProfessionalDTO;
import br.com.mecontrata.management.professional.domain.ProfessionalDetailDTO;
import br.com.mecontrata.management.professional.facade.ProfessionalFacade;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/professional")
@Validated
public class ProfessionalController {

    private ProfessionalFacade professionalFacade;

    public ProfessionalController(ProfessionalFacade professionalFacade) {
        this.professionalFacade = professionalFacade;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessionalDTO createProfessional(@Valid @RequestBody ProfessionalDTO professional) {
        return professionalFacade.createProfessional(professional);
    }

    @GetMapping("/{email}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ProfessionalDTO getProfessional(@PathVariable @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "{email.invalid}") String email) {
        return professionalFacade.getProfessional(email);
    }

    @DeleteMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfessional(@RequestParam @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "{email.invalid}") String email) {
        professionalFacade.deleteProfessional(email);
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ProfessionalDetailDTO> listProfessional(@RequestParam String nomeCategoria) {
        return professionalFacade.listProfessional(nomeCategoria);
    }

    @PostMapping("/upload")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadPhotoProfileAndPortifolio(@RequestPart(value = "foto_perfil") MultipartFile photoProfile,
                                                @RequestPart(value = "portifolio") List<MultipartFile> portifolio,
                                                @RequestParam @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "{email.invalid}") String email) {
        this.professionalFacade.uploadPhotoProfileAndPortifolio(photoProfile, portifolio, email);
    }

}
