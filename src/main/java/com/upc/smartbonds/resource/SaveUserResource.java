package com.upc.smartbonds.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveUserResource {

    @NotNull
    @NotBlank
    @Size(min = 8, max = 15)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 20)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String name;
}
