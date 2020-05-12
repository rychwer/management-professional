package br.com.mecontrata.management.professional.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ServicoEntity {

    private String titloServico;

    private List<ServicoDetailEntity> servicoDetailDTOList;

}
