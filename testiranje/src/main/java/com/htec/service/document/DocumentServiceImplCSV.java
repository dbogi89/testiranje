package com.htec.service.document;

import com.htec.api.dto.response.DocumentResponse;
import com.opencsv.bean.BeanVerifier;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Created by dbogicevic
 */
public class DocumentServiceImplCSV implements DocumentService{
    @Override
    public <T>DocumentResponse generate(MultipartFile multipartFile, Class<T> type,
                                     BeanVerifier<T> bean) throws IOException {
        List<T> csvList;
        Reader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
            CsvToBean<T> csvFile = new CsvToBeanBuilder<T>(bufferedReader)
                    .withType(type)
                    .withVerifier(bean)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            csvList = csvFile.parse();
        }finally {
            if(bufferedReader != null)bufferedReader.close();
        }
        return DocumentResponse.builder().content(csvList).build();

    }
}
