package com.htec.api.dto.route;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;


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
    private String airlineId;
    @CsvBindByPosition(position = 6)
    private String codeShare;
    @CsvBindByPosition(position = 7)
    private Integer stops;
    @CsvBindByPosition(position = 8)
    private String equipment;
    @CsvBindByPosition(position = 9)
    private Double price;
    @CsvBindByPosition(position = 5)
    private String destinationAirPortId;
    @CsvBindByPosition(position = 3)
    private String sourceAirPortId;
    @CsvBindByPosition(position = 2)
    private String sourceCode;
    @CsvBindByPosition(position = 4)
    private String destinationCode;
    
}
