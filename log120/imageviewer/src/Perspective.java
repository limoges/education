
import java.util.Observable;
import java.util.Observer;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Image;

public class Perspective extends Observable implements ImageObserver {

  public static float BASE_FACTOR = 1000.0f;
  private static float ZOOM_STEP = 10.0f;
  private Point coordinates;
  private float zoom;
  private Image image;

  public Perspective(Image image) {
    this.image = image;
    this.coordinates = new Point(0, 0);
    this.zoom = BASE_FACTOR;
  }

  public Perspective(Perspective p) {
    this.coordinates = p.coordinates;
    this.zoom = p.zoom;
    this.image = p.image;
  }

  public float getZoom() {  
    return zoom;            
  }                         
                            
  public Image getImage() { 
    return image;           
  }                         

  public Point getCoordinates() {
    return coordinates;
  }

  public void translate(int dx, int dy) { 
    this.coordinates.translate(dx, dy);   
                                          
    setChanged();                         
    notifyObservers();                    
  }                                       

  public void zoomIn() {
    this.zoom += ZOOM_STEP;
    setChanged();
    notifyObservers();                    
  }

  public void zoomOut() {
    if (this.zoom <= ZOOM_STEP)
      return;
    this.zoom -= ZOOM_STEP;
    setChanged();
    notifyObservers();                    
  }

  public void setZoom(float factor) {
    this.zoom = factor;

    setChanged();
    notifyObservers();
  }

  public void reset() {
    this.coordinates = new Point(0, 0);
    this.zoom = 0;
    setChanged();
    notifyObservers();
  }

  public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
    return false;
  }

  /*
  public static Perspective create(String path) {
    // Create new perspective from the given path, which is supposedly an
    // image.
  }
  */

}

