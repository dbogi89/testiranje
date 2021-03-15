package com.htec.api.v1;

import com.htec.api.dto.airport.AirPortDtoRequestBean;
import com.htec.api.dto.airport.AitPortDtoRequest;
import com.htec.api.dto.document.DocumentDtoRequest;
import com.htec.api.dto.document.Response;
import com.htec.api.dto.route.RouteDtoRequest;
import com.htec.api.dto.route.RouteDtoRequestBean;
import com.htec.constants.Constants;
import com.htec.service.AirPortService;
import com.htec.util.DocumentUtil;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by dbogicevic
 */
@RestController
@RequestMapping("api/v1/upload")
@AllArgsConstructor
public class AirPortUploadApi {

    private final AirPortService airPortService;

    @PostMapping(value = "/airPorts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> uploadAirPort(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(Response.builder().code(Constants.NOK).content("File not valid").build(), HttpStatus.BAD_REQUEST);
        }

        try {
            Response documentResponse
                    = DocumentUtil.parseDocument("CSV", file, AitPortDtoRequest.class, new AirPortDtoRequestBean());
            List<AitPortDtoRequest> aitPortDtoRequestList = (List<AitPortDtoRequest>) documentResponse.getContent();
            return ResponseEntity.ok().body(airPortService.save(aitPortDtoRequestList));

        } catch (IOException e) {
            return new ResponseEntity<>(Response.builder().code(Constants.NOK).content("Parse file problem").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/routes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> uploadRoutes(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return new ResponseEntity<>(Response.builder().code(Constants.NOK).content("File not valid").build(), HttpStatus.BAD_REQUEST);
        }

        try {
            Response documentResponse = DocumentUtil.parseDocument("CSV", multipartFile, RouteDtoRequest.class, new RouteDtoRequestBean());
            List<RouteDtoRequest> routeDtoRequests = (List<RouteDtoRequest>) documentResponse.getContent();
            return ResponseEntity.ok().body(airPortService.saveRoute(routeDtoRequests));

        } catch (IOException e) {
            return new ResponseEntity<>(
                    Response.builder().
                            code(Constants.NOK).
                            content("Parse file problem")
                            .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
