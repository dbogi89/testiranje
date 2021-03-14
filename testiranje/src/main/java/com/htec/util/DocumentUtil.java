package com.htec.util;

import com.htec.api.dto.document.Response;
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
  //https://attacomsian.com/blog/read-write-csv-files-opencsv
    public static <T> Response parseDocument(String documentType, MultipartFile multipartFile, Class<T> type,
                                             BeanVerifier<T> bean) throws IOException {
        DocumentService documentService = null;
        DocumentType docType = toDocumentType(documentType);
        Response documentResponse = null;
        switch (docType) {
            case CSV:
                documentService = new DocumentServiceImplCSV();
                break;
            //case EXCEL:
                //u buducnosti
             //   break;
            default:
                documentService = new DocumentServiceImplCSV();
                break;
        }

        return documentService.generate(multipartFile, type, bean);
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
