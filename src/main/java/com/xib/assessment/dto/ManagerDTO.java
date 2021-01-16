package com.xib.assessment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManagerDTO {
    @NotBlank(message = "Firstname is required.")
    private String firstName;
    @NotBlank(message = "Lastname is required.")
    private String lastName;
    @NotBlank(message = "Id number is required.")
    private String idNumber;
    private Set<Long> teamIds;
}
