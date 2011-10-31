import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;

public class Line extends Shape {
	
  private Point p1, p2;
	
  /*
   * Constructor
   * @param x1 The first point's x-coordinate
   * @param y1 The first point's y-coordinate
   * @param x2 The second point's x-coordinate
   * @param y2 The second point's y-coordinate
   */
	public Line(int id, Color color, int x1, int y1, int x2, int y2) {
		super(id, color, ShapeType.Line);
    this.p1 = new Point(x1, y1);
    this.p2 = new Point(x2, y2);
	}

  /*
   * Constructor
   * @param p1 The first point
   * @param p2 The second point
   */
  public Line(int id, Color color, Point p1, Point p2) {
    super(id, color, ShapeType.Line);
    this.p1 = p1;
    this.p2 = p2;
  }

  public void draw(Graphics2D g) {
    g.setColor(getColor());
    g.drawLine(p1.x, p1.y, p2.x, p2.y);
  }

  public double getArea() {
    return 0;
  }

  public double getDistance() {
    double dx = p2.getX() - p1.getX();
    double dy = p2.getY() - p1.getY();
    return Math.sqrt(dx + dy);
  }

}
