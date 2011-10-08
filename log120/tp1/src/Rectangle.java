import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Dimension;

public class Rectangle extends Shape {

  private Point point;
  private Dimension dimension;
	
	public Rectangle(long id, int x1, int y1, int x2, int y2) {
		super(id);
    this.point = new Point(x1, y1);
    this.dimension = new Dimension(x2, y2);
	}

  public Rectangle(long id, Point point, Dimension dimension) {
    super(id);
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
    g.setColor(Color.BLACK);
    g.drawRect(point.x, point.y, dimension.width, dimension.height);
    g.setColor(Color.RED);
    g.fillRect(point.x, point.y, dimension.width, dimension.height);
  }
}
