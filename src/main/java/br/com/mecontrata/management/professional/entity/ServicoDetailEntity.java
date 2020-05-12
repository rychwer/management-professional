package br.com.mecontrata.management.professional.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServicoDetailEntity {

    private String nomeServico;

    private BigDecimal valorServico;

    private String tempoEstimadoSevico;

    private String descricaoServico;

}
