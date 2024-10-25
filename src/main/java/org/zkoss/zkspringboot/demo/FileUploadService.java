package org.zkoss.zkspringboot.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileUploadService {

    Logger logger = LoggerFactory.getLogger(FileUploadService.class);

    public void uploadString(String name, String data) {
        logFileUpload(name, data.length());
    }

    public void uploadBytes(String name, byte[] data) {
        logFileUpload(name, data.length);
    }

    private void logFileUpload(String name, int length) {
        logger.debug("File uploaded with name: '{}' and size: {}", name, length);
    }
}
