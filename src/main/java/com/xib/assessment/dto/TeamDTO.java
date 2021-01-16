package com.xib.assessment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDTO {
    @NotBlank(message = "Name is required.")
    private String name;
}
