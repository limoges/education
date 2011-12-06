
import java.awt.Image;

import java.util.Observable;

public class ImageModel extends Observable {

  private Image image;
  // Current zoom factor (0 = none, off < 0, in > 0)
  private int zoomFactor;
  // Current position in relation to origin
  private Point coordinates;

  public ImageModel(Image image) {
    this.image = image;
    this.zoomFactor = 0;
    this.coordinates = new Point(0, 0);
  }

  public Image getImage() {
    return image;
  }

  public int getZoomFactor() {
    return zoomFactor;
  }

  public Point getCoordinates() {
    return coordinates;
  }

  public void increaseZoom() {
    ++zoomFactor;
  }

  public void decreaseZoom() {
    --zoomFactor;
  }

  public void translate(int dx, int dy) {
    translate(dx, dy);
  }

}

