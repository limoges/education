import java.awt.Point;

public class Ellipse extends Shape {
	
  private Point center, radius;
	
  public Ellipse(long id, Point center, Point radius) {
    super(id);
    this.center = center;
    this.radius = radius;
  }

  public Point getCenter() {
    return center;
  }

  public void setCenter(Point center) {
    this.center = center;
  }

  public Point getRadius() {
    return radius;
  }

  public void setRadius(Point radius) {
    this.radius = radius;
  }
}

