package com.htec.api.dto.airport;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.processor.PreAssignmentProcessor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by dbogicevic
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AitPortDtoRequest {

    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByPosition(position = 1)
    private String airPortName;

    @CsvBindByPosition(position = 2)
    private String cityName;

    @CsvBindByPosition(position = 3)
    private String countryName;

    @CsvBindByPosition(position = 4)
    private String iata;

    @CsvBindByPosition(position = 5)
    private String icao;

    @CsvBindByPosition(position = 6)
    private BigDecimal latitude;

    @CsvBindByPosition(position = 7)
    private BigDecimal longitude;

    @CsvBindByPosition(position = 8)
    private String altitude;

    @CsvBindByPosition(position = 9)
    private String timezone;

    @CsvBindByPosition(position = 10)
    private String dst;

    @CsvBindByPosition(position = 11)
    private String tz;

    @CsvBindByPosition(position = 12)
    private String type;

    @CsvBindByPosition(position = 13)
    private String source;
    private Long countId;
    private Long cityId;


}
