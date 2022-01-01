package models;
public class Settings {
    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    ImageType imageType = ImageType.CRYSTALS;
}
