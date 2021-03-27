package com.bigjava.api.v1;

import com.bigjava.api.dto.airport.AirPortDtoRequestBean;
import com.bigjava.api.dto.airport.AitPortDtoRequest;
import com.bigjava.api.dto.document.Response;
import com.bigjava.api.dto.route.RouteDtoRequest;
import com.bigjava.api.dto.route.RouteDtoRequestBean;
import com.bigjava.constants.Constants;
import com.bigjava.entity.Role;
import com.bigjava.service.AirPortService;
import com.bigjava.util.DocumentUtil;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.List;

/**
 * Created by dbogicevic
 */
@RestController
@RequestMapping("api/v1/upload")
@AllArgsConstructor
@RolesAllowed(Role.ROLE_ADMIN)
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
            return ResponseEntity.ok().body(airPortService.save((List<AitPortDtoRequest>) documentResponse.getContent()));

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
            return ResponseEntity.ok().body(airPortService.saveRoute((List<RouteDtoRequest>)documentResponse.getContent()));

        } catch (IOException e) {
            return new ResponseEntity<>(
                    Response.builder().
                            code(Constants.NOK).
                            content("Parse file problem")
                            .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
