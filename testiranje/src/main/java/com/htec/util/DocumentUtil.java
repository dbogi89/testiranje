package com.htec.util;

import com.htec.api.dto.response.DocumentResponse;
import com.htec.constants.Constants;
import com.htec.entity.DocumentType;
import com.htec.service.document.DocumentService;
import com.htec.service.document.DocumentServiceImplCSV;
import com.opencsv.bean.BeanVerifier;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * Created by dbogicevic
 */
public class DocumentUtil {

    public static <T> DocumentResponse parseDocument(String documentType, MultipartFile multipartFile, Class<T> type,
                                                  BeanVerifier<T> bean) throws IOException {
        DocumentService documentService;
        DocumentType docType = toDocumentType(documentType);
        DocumentResponse documentResponse = null;
        switch (docType) {
            case CSV:
                documentService = new DocumentServiceImplCSV();
                documentResponse = documentService.generate(multipartFile, type, bean);
                break;
            default:
                documentService = new DocumentServiceImplCSV();
                documentResponse = documentService.generate(multipartFile, type, bean);
                break;


        }
        return documentResponse;
    }


    public static DocumentType toDocumentType(String value) {
        if (value == null || value.isEmpty()) {
            return Constants.DEFAULT_DOCUMENT_TYPE;
        }

        if ("CSV".equalsIgnoreCase(value)) {
            return DocumentType.CSV;
        } else if ("EXCEL".equalsIgnoreCase(value)) {
            return DocumentType.EXCEL;
        }
        return Constants.DEFAULT_DOCUMENT_TYPE;
    }
}
