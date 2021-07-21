package com.yataygecisle.commons.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nimbusds.jose.shaded.json.annotate.JsonIgnore;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    @JsonIgnore
    private HttpStatus httpStatus;

    @JsonProperty("error")
    private String error;

    @JsonProperty("error_description")
    private String errorDescription;

    @JsonProperty("error_descriptions")
    private List<FieldError> errorDescriptions = new ArrayList<>();

    public ErrorResponse(String errorType, String errorDescription, HttpStatus httpStatus){
        this.error = errorType;
        this.errorDescription = errorDescription;
        this.httpStatus = httpStatus;
    }

    public ErrorResponse(String errorType, List<FieldError> fieldErrors, HttpStatus httpStatus){
        this.error = errorType;
        this.httpStatus = httpStatus;
        this.errorDescription = "Method argument not valid";
        for(FieldError fieldError : fieldErrors) {
            errorDescriptions.add(new FieldError(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage()));
        }
    }

    public ErrorResponse(String errorType, HttpStatus httpStatus) {
        this.error = errorType;
        this.errorDescription = httpStatus.getReasonPhrase();
        this.httpStatus = httpStatus;
    }

    public ErrorResponse(String errorType) {
        this.error = errorType;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorDescription = HttpStatus.BAD_REQUEST.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public List<FieldError> getErrorDescriptions() {
        return errorDescriptions;
    }
}
