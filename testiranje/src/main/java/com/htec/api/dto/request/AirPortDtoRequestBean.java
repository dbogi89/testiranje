package com.htec.api.dto.request;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.exceptions.CsvConstraintViolationException;

/**
 * Created by dbogicevic
 */
public class AirPortDtoRequestBean implements BeanVerifier<AitPortDtoRequest> {
    @Override
    public boolean verifyBean(AitPortDtoRequest aitPortDtoRequest) throws CsvConstraintViolationException {
        return aitPortDtoRequest.getAirportId() != 0;
    }
}
