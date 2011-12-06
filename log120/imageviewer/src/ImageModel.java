
import java.awt.Image;

import java.util.Observable;

public class ImageModel extends Observable {

  Image image;

  public ImageModel(Image image) {
    this.image = image;
  }

  public Image getImage() {
    return image;
  }

}

