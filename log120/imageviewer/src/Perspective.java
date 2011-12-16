
import java.util.Observable;
import java.util.Observer;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Image;

public class Perspective extends Observable {

  public static int BASE_FACTOR = 50;
  public static int ZOOM_STEP = 10;
  public static int ZOOM_MIN = 1;
  public static int ZOOM_MAX = 100;
  private Point coordinates;
  private int zoom;
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

  public int getZoom() {  
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

  public void setZoom(int zoom) {
    if (zoom > ZOOM_MAX)
      this.zoom = ZOOM_MAX;
    else if (zoom < ZOOM_MIN)
      this.zoom = ZOOM_MIN;
    else
      this.zoom = zoom;

    setChanged();
    notifyObservers();
  }

  public void zoomIn() {
    if ((this.zoom + ZOOM_STEP) > ZOOM_MAX)
      this.zoom = ZOOM_MAX;
    else
      this.zoom += ZOOM_STEP;
    setChanged();
    notifyObservers();                    
  }

  public void zoomOut() {
    if ((this.zoom - ZOOM_STEP) < ZOOM_MIN)
      this.zoom = ZOOM_STEP;
    else
      this.zoom -= ZOOM_STEP;
    setChanged();
    notifyObservers();                    
  }

  public void reset() {
    this.coordinates = new Point(0, 0);
    this.zoom = 0;
    setChanged();
    notifyObservers();
  }

}

