package br.com.mecontrata.management.professional.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "professional")
public class ProfessionalEntity {

    @Id
    private String email;

    private String firstName;

    private String lastName;

    @Indexed
    private String cpf;

    private PhotoEntity profilePhoto;

    private PhotoEntity thumbnail;

    private List<ServicoEntity> servicos;

    private String address;

    private String addressComplement;

    private List<PhoneEntity> phones;

    private List<PhotoEntity> portifolio;

    private Boolean emailConfirmed = Boolean.FALSE;

    @Version
    private Long version;

    @Indexed
    private String professionalCategory;

}
