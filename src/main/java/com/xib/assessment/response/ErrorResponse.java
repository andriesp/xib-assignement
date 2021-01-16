package com.xib.assessment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private int status;
    private String message;
    private List<String> errors;

    public ErrorResponse() {
    }

    public ErrorResponse(int status) {
        this.status = status;
    }
}
