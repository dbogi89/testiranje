package com.bigjava.api.dto.airport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbogicevic
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TravelDtoResponse {
    private AirportReponse start;
    private AirportReponse end;
    private List<AirportReponse> airportReponses = new ArrayList<>();
    private Double distance;
    private Double price;

}
