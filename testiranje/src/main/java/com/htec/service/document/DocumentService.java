package com.htec.service.document;

import com.htec.api.dto.document.DocumentResponse;
import com.opencsv.bean.BeanVerifier;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by dbogicevic
 */
public interface DocumentService {
    <T>DocumentResponse generate(MultipartFile multipartFile, Class<T> type,
                              BeanVerifier<T> bean) throws IOException;
}
