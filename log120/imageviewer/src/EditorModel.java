
import java.awt.Image;
import java.awt.Point;
import java.util.Observable;

public class EditorModel extends Observable {

  private Image initial;
  // Editor properties
  private int zoomFactor;
  private Point coordinates;
  
  private boolean hasChanges;
  private Image result;

  public EditorModel(Image image) {
    this.initial = image;
    reset();
  }

  public void reset() {
    this.result = this.initial;
    this.zoomFactor = 0;
    this.coordinates = new Point(0, 0);
    this.hasChanges = false;
  }

  public Image getImage() {
    // Return an image with the applied properties
    if (hasChanges)
      result = applyChanges();

    return result;
  }

  private Image applyChanges() {
    return result;
  }

}

