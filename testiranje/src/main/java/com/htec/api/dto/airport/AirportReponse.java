package com.htec.api.dto.airport;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dbogicevic
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirportReponse {

    private String airPortName;
    private String cityName;
    private String countryName;

}
