package com.htec.api.v1;

import com.htec.api.dto.request.AirPortDtoRequestBean;
import com.htec.api.dto.request.AitPortDtoRequest;
import com.htec.api.dto.request.DocumentDtoRequest;
import com.htec.api.dto.response.DocumentResponse;
import com.htec.constants.Constants;
import com.htec.service.AirPortService;
import com.htec.util.DocumentUtil;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/airPortFile")
    public ResponseEntity<DocumentResponse> uploadAirPort(@RequestPart("file") MultipartFile  multipartFile, @RequestBody DocumentDtoRequest documentDtoRequest){
      if(multipartFile.isEmpty())
          return new ResponseEntity<>(DocumentResponse.builder().code(Constants.NOK).content("File not valid").build(), HttpStatus.BAD_REQUEST);

        try {
            DocumentResponse documentResponse =
                    DocumentUtil.parseDocument(documentDtoRequest.getDocumentType() , multipartFile, AitPortDtoRequest.class, new AirPortDtoRequestBean());
        List<AitPortDtoRequest> aitPortDtoRequestList = (List<AitPortDtoRequest>) documentResponse.getContent();
        return ResponseEntity.ok().body(DocumentResponse.builder().code(Constants.OK).content(airPortService.save(aitPortDtoRequestList)).build());

        } catch (IOException e) {
           return new ResponseEntity<>(DocumentResponse.builder().code(Constants.NOK).content("Parse file problem").build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }




}
