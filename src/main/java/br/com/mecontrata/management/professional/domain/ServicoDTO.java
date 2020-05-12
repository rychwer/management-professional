package br.com.mecontrata.management.professional.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ServicoDTO {

    @JsonProperty("titulo_servico")
    private String titloServico;

    @JsonProperty("datalhe_servico")
    private List<ServicoDetailDTO> servicoDetailDTOList;

}
