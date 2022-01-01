package models;

public class ImagesFolderDetector implements IImagesFolderDetector {
    public String getFolder(ImageType imageType) throws Exception {
        String applicationDir = "D:\\Школьные задания\\Программирование\\Match-3\\images";
        switch (imageType) {
            case CRYSTALS: return applicationDir;
            case FRUIT: return applicationDir + "2";
            case VEGETABLES: return applicationDir + "3";
            case ANIMALS: return applicationDir + "4";
            default: throw new Exception("Неизвестный тип картинок");
        }
    }
}
