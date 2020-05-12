package br.com.mecontrata.management.professional.controller.handler;

import br.com.mecontrata.management.professional.facade.exception.EmailNotConfirmedException;
import br.com.mecontrata.management.professional.facade.exception.ProfessionalAlreadyExists;
import br.com.mecontrata.management.professional.facade.exception.ProfessionalNotFoundException;
import br.com.server.resource.handle.DefaultControllerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProfessionalControllerAdvice extends DefaultControllerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProfessionalAlreadyExists.class)
    @ResponseBody
    public Map<String, Object> handleClientAlreadyExistsExceptions(ProfessionalAlreadyExists ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put(messageSource.getMessage("status.code", null, null), String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errors.put(messageSource.getMessage("message", null, null), ex.getLocalizedMessage());
        errors.put(messageSource.getMessage("message.code", null, null), 1);
        log.error(ex.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProfessionalNotFoundException.class)
    @ResponseBody
    public Map<String, Object> handleClientNotFoundExceptions(ProfessionalNotFoundException ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put(messageSource.getMessage("status.code", null, null), String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errors.put(messageSource.getMessage("message", null, null), ex.getLocalizedMessage());
        errors.put(messageSource.getMessage("message.code", null, null), 2);
        log.error(ex.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailNotConfirmedException.class)
    @ResponseBody
    public Map<String, Object> handleEmailNotConfirmedExceptions(EmailNotConfirmedException ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put(messageSource.getMessage("status.code", null, null), String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errors.put(messageSource.getMessage("message", null, null), ex.getMessage());
        errors.put(messageSource.getMessage("message.code", null, null), 3);
        log.error(ex.getLocalizedMessage());
        return errors;
    }

}
