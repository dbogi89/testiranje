package com.htec.api.dto.route;

import com.htec.api.dto.route.RouteDtoRequest;
import com.opencsv.bean.BeanVerifier;
import com.opencsv.exceptions.CsvConstraintViolationException;

/**
 * Created by dbogicevic
 */
public class RouteDtoRequestBean implements BeanVerifier<RouteDtoRequest> {
    @Override
    public boolean verifyBean(RouteDtoRequest routeDtoRequest) throws CsvConstraintViolationException {

        return routeDtoRequest.getAirlineId()!=0 && routeDtoRequest.getSourceAirPortId() != 0 && routeDtoRequest.getDestinationAirPortId()!=0;
    }
}
