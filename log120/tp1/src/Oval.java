import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;

public class Oval extends Shape {
  private Point center;
  private Dimension dimension;
	
  public Oval(int id, Color color, int x, int y, int width, int height) {
    super(id, color);
    this.center = new Point(x, y);
    this.dimension = new Dimension(width, height);
  }
  public Oval(int id, Color color, Point center, Dimension dimension) {
    super(id, color);
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
    g.setColor(getColor());
    g.fillOval(center.x, center.y, dimension.width, dimension.height);
    g.setColor(Color.BLACK);
    g.drawOval(center.x, center.y, dimension.width, dimension.height);
  }

}

