package com.carolinapaulo.desafiomercadolivre.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsOutputDto {

    private final List<String> globalErrorMessages = new ArrayList<>();
    private final List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();

    public void addError(String message){
        globalErrorMessages.add(message);
    }

    public void addFieldError(String field, String message) {
        FieldErrorOutputDto fieldError = new FieldErrorOutputDto(field, message);
        fieldErrors.add(fieldError);
    }

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<FieldErrorOutputDto> getFieldErrors() {
        return fieldErrors;
    }
}
