package com.htec.api.dto.route;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Created by dbogicevic
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteDtoRequest {
    @CsvBindByPosition(position = 0)
    private String airlineCode;

    @CsvBindByPosition(position = 1)
    private Long airlineId;

    @CsvBindByPosition(position = 2)
    private Boolean codeShare;
    @CsvBindByPosition(position = 3)
    private Integer stops;
    @CsvBindByPosition(position = 4)
    private String equipment;
    @CsvBindByPosition(position = 5)
    private Double price;
    @CsvBindByPosition(position = 6)
    private Long destinationAirPortId;
    @CsvBindByPosition(position = 7)
    private Long sourceAirPortId;
}
