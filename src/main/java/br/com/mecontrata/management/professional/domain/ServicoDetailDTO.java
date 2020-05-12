package br.com.mecontrata.management.professional.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServicoDetailDTO {

    @JsonProperty("nome_servico")
    private String nomeServico;

    @JsonProperty("valor_servico")
    private BigDecimal valorServico;

    @JsonProperty("tempo_estimado_servico")
    private String tempoEstimadoSevico;

    @JsonProperty("descricao_servico")
    private String descricaoServico;

}
