import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;

public class Ellipse extends Shape {
  private Point center;
  private Dimension dimension;
	
  public Ellipse(long id, Point center, Dimension dimension) {
    super(id);
    this.center = center;
    this.dimension = dimension;
  }

  public Point getCenter() {
    return center;
  }

  public void setCenter(Point center) {
    this.center = center;
  }

  public Dimension getDimension() {
    return dimension;
  }

  public void setDimension(Dimension Dimension) {
    this.dimension = dimension;
  }

  public void draw(Graphics2D g) {
    g.setColor(Color.BLACK);
    g.drawOval(center.x, center.y, dimension.width, dimension.height);
    g.setColor(Color.CYAN);
    g.fillOval(center.x, center.y, dimension.width, dimension.height);
  }

}

