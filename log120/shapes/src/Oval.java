import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;

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
    super(id, color);
    this.center = new Point(x, y);
    this.dimension = new Dimension(width, height);
  }

  /*
   * Constructor
   * @param center The center point
   * @param dimension The dimensions
   */
  public Oval(int id, Color color, Point center, Dimension dimension) {
    super(id, color);
    this.center = center;
    this.dimension = dimension;
  }

  public void draw(Graphics2D g) {
    g.setColor(getColor());
    g.fillOval(center.x, center.y, dimension.width, dimension.height);
    g.setColor(Color.BLACK);
    g.drawOval(center.x, center.y, dimension.width, dimension.height);
  }

}
