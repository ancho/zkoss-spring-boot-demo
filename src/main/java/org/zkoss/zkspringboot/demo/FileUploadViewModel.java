package org.zkoss.zkspringboot.demo;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;

public class FileUploadViewModel {

    @WireVariable
    FileUploadService fileUploadService;

    @Command
    public void upload(@BindingParam("event") UploadEvent event) {
        Media media = event.getMedia();
        if (media.isBinary()) {
            fileUploadService.uploadBytes(media.getName(), media.getByteData());
        } else {
            fileUploadService.uploadString(media.getName(), media.getStringData());
        }
    }

}
