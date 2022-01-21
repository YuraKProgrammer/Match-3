package models.detectors;

import models.ImageType;

public interface IImagesFolderDetector {
    String getFolder(ImageType imageType) throws Exception;
}
