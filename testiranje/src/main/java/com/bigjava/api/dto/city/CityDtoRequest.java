package com.bigjava.api.dto.city;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Created by dbogicevic
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CityDtoRequest {

    @Max(value = 80)
    @NotNull
    private String cityName;
    @Max(value = 50)
    @NotNull
    private String countryName;
    @Max(value = 150)
    @NotNull
    private String description;
}
