package br.com.mecontrata.management.professional.service;

import br.com.mecontrata.management.professional.domain.ReceitaFederalDTO;
import org.springframework.scheduling.annotation.Async;

public interface ReceitaFederalService {

    @Async
    void checaDadosReceitaFederal(ReceitaFederalDTO receitaFederalDTO);

}
