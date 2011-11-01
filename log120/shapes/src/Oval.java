import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;
import java.lang.Math;

public class Oval extends Shape {
  private Point center;
  private Dimension dimension;
	
  /*
   * Constructor
   * @param x The x-coordinate of the center
   * @param y The y-coordinate of the center
   * @param width The horizontal diameter
   * @param height The vertical diameter
   */
  public Oval(int id, Color color, int x, int y, int width, int height) {
    super(id, color, (width == height?ShapeType.Circle:ShapeType.Oval));
    this.center = new Point(x, y);
    this.dimension = new Dimension(width, height);
  }

  /*
   * Constructor
   * @param center The center point
   * @param dimension The dimensions
   */
  public Oval(int id, Color color, Point center, Dimension dimension) {
    super(id, color,
        (dimension.width == dimension.height?
         ShapeType.Square:ShapeType.Rectangle)
    );
    this.center = center;
    this.dimension = dimension;
  }

  public void draw(Graphics2D g) {
    g.setColor(getColor());
    g.fillOval(center.x, center.y, dimension.width, dimension.height);
    g.setColor(Color.BLACK);
    g.drawOval(center.x, center.y, dimension.width, dimension.height);
  }

  public void drawAt(Graphics2D g, int x, int y) {
    g.setColor(getColor());
    g.fillOval(x, y, dimension.width, dimension.height);
    g.setColor(Color.BLACK);
    g.drawOval(x, y, dimension.width, dimension.height);
  }

  public double getArea() {
    double a = dimension.getWidth() / 2;
    double b = dimension.getHeight() / 2;
    return Math.PI * a * b;
  }

  public double getDistance() {
    return Math.max(dimension.getWidth(), dimension.getHeight());
  }

  public int getWidth() {
    return dimension.width;
  }

  public int getHeight() {
    return dimension.height;
  }
}

