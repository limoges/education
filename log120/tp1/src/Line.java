import java.awt.Point;

public class Line extends Shape {
	
  // A line needs 2 coordinates
  private Point p1, p2;
	
	public Line(long id, int x1, int y1, int x2, int y2) {
		super(id);
    this.p1 = new Point(x1, y1);
    this.p2 = new Point(x2, y2);
	}

  public Line(long id, Point p1, Point p2) {
    super(id);
    this.p1 = p1;
    this.p2 = p2;
  }

  public void setP1(Point p1) {
    this.p1 = p1;
  }

  public void setP2(Point p2) {
    this.p2 = p2;
  }

  public Point getP1() {
    return p1;
  }

  public Point getP2() {
    return p2;
  }
}
