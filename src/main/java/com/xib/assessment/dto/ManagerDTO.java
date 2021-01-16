package com.xib.assessment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManagerDTO {
    private Long id;
    @NotBlank(message = "Firstname is required.")
    private String firstName;
    @NotBlank(message = "Lastname is required.")
    private String lastName;
    @NotBlank(message = "Id number is required.")
    private String idNumber;
    @Size(min = 1, max = 2, message = "A manager can manages at least one but not more than 2 teams")
    private Set<Long> teamIds;
}
