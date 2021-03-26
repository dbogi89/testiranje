package com.htec.service.document;

import com.htec.api.dto.document.Response;
import com.opencsv.bean.BeanVerifier;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by dbogicevic
 */
public class DocumentServiceImplCSV implements DocumentService {

    @Override
    public <T> Response generate(MultipartFile multipartFile, Class<T> type,
                                 BeanVerifier<T> bean) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), "UTF-8"))) {
            CsvToBean<T> csvFile = new CsvToBeanBuilder<T>(bufferedReader)
                    .withType(type)
                    .withVerifier(bean)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return Response.builder().content(csvFile.parse()).build();

        }
    }
}