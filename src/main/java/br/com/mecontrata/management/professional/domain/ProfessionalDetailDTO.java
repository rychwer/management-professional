package br.com.mecontrata.management.professional.domain;

import br.com.mecontrata.management.professional.controller.validation.annotation.TelefoneFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfessionalDetailDTO {

    @NotEmpty(message = "{first.name.notnull}")
    @JsonProperty("primeiro_nome")
    private String firstName;

    @NotEmpty(message = "{last.name.notnull}")
    @JsonProperty("ultimo_nome")
    private String lastName;

    @JsonProperty("foto")
    private String thumbnail;

    @JsonProperty("avaliacao")
    private String rate;

    @NotEmpty(message = "{address.notnull}")
    @JsonProperty("endereco")
    private String address;

    @JsonProperty("complemento_endereco")
    private String addressComplement;

    @JsonProperty("distancia")
    private String distance;

    @NotEmpty(message = "{photo.notempty}")
    @JsonProperty("foto")
    private MultipartFile profilePhoto;

    @NotNull(message = "{service.notnull}")
    @JsonProperty("servicos")
    private List<ServicoDTO> servicos;

    @NotNull(message = "{list.phone.notnull}")
    @JsonProperty("telefones")
    //@TelefoneFields
    private List<PhoneDTO> phones;

    @JsonProperty("portifolio")
    private List<MultipartFile> portifolio;

    @JsonProperty("categoria_profissional")
    @NotEmpty(message = "{password.notnull}")
    private String professionalCategory;
}
