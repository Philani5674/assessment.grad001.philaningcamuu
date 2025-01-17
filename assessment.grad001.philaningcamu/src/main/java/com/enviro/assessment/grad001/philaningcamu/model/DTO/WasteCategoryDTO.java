package com.enviro.assessment.grad001.philaningcamu.model.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WasteCategoryDTO {

    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    private String description;

    public @NotBlank(message = "Name is required") String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}