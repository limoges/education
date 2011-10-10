import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Dimension;

public class Rectangle extends Shape {

  private Point point;
  private Dimension dimension;
	
	public Rectangle(int id, Color color, int x, int y, int width, int height) {
		super(id, color);
    this.point = new Point(x, y);
    this.dimension = new Dimension(width, height);
	}

  public Rectangle(int id, Color color, Point point, Dimension dimension) {
    super(id, color);
    this.point = point;
    this.dimension = dimension;
  }

  public Point getPoint() { 
    return point;           
  }                         

  public void setPoint(Point point) {
    this.point = point;
  }

  public Dimension getDimension() { 
    return dimension;               
  }                                 

  public void setDimension(Dimension dimension) {
    this.dimension = dimension;
  }

  public void draw(Graphics2D g) {
    g.setColor(getColor());
    g.fillRect(point.x, point.y, dimension.width, dimension.height);
    g.setColor(Color.BLACK);
    g.drawRect(point.x, point.y, dimension.width, dimension.height);
  }
}
