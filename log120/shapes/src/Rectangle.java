import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Dimension;

public class Rectangle extends Shape {

  private Point point;
  private Dimension dimension;
	
  /*
   * Constructor
   * @param x The x-coordinate of the bottom left corner
   * @param y The y-coordinate of the bottom left corner
   * @param width The rectangle's width
   * @param height The rectangle's height
   */
	public Rectangle(int id, Color color, int x, int y, int width, int height) {
		super(id, color);
    this.point = new Point(x, y);
    this.dimension = new Dimension(width, height);
	}

  /*
   * Constructor
   * @param point The bottom left point of the rectangle
   * @param dimension The rectangle's dimensions
   */
  public Rectangle(int id, Color color, Point point, Dimension dimension) {
    super(id, color);
    this.point = point;
    this.dimension = dimension;
  }

  public void draw(Graphics2D g) {
    g.setColor(getColor());
    g.fillRect(point.x, point.y, dimension.width, dimension.height);
    g.setColor(Color.BLACK);
    g.drawRect(point.x, point.y, dimension.width, dimension.height);
  }
}