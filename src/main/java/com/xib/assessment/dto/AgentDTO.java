package com.xib.assessment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgentDTO {
    private Long id;
    @NotBlank(message = "Firstname is required.")
    private String firstName;
    @NotBlank(message = "Lastname is required.")
    private String lastName;
    @NotBlank(message = "Id number is required.")
    private String idNumber;
}
