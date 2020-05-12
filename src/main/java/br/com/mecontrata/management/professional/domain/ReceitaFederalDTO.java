package br.com.mecontrata.management.professional.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReceitaFederalDTO {

    private String firstName;
    private String lastName;
    private String cpf;

}
