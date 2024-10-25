package org.zkoss.zkspringboot.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileUploadService {

    Logger logger = LoggerFactory.getLogger(FileUploadService.class);

    public void uploadString(String name, String data) {
        logger.debug("File uploaded with name: {}", name);
        logger.debug("File uploaded with length: {}", data.length());
    }

    public void uploadBytes(String name, byte[] data) {
        logger.debug("File uploaded with name: {}", name);
        logger.debug("File uploaded with length: {}", data.length);
    }
}
