import java.awt.Graphics2D;
import java.awt.Color;

public abstract class Shape {

  enum ShapeType {
    Square,
    Rectangle,
    Circle,
    Oval,
    Line
  };

	private int id;
  private Color color;
  private ShapeType shapeType;
  private int orderOfArrival;
  private static int lastArrival = 0;
	
  /*
   * Constructor
   * @param id The IDLogger id
   * @param color The color to draw the shape with
   */
	public Shape(int id, Color color, ShapeType shapeType) {
		this.id = id;
    this.color = color;
    this.shapeType = shapeType;
    this.orderOfArrival = lastArrival++;
	}

  /*
   * Retrieves the id
   * @return The id
   */
	public int getId() {
		return this.id;
	}

  /*
   * Retrieves the color
   * @return The current color
   */
  public Color getColor() {
    return this.color;
  }

  /*
   * Sets the color
   * @param color The new color
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /*
   * Draws the shape on the given graphic context
   * @param g The graphic context to draw on
   */
  public abstract void draw(Graphics2D g);
  public abstract void drawAt(Graphics2D g, int x, int y);
  public abstract double getDistance();
  public abstract double getArea();
  public abstract int getWidth();
  public abstract int getHeight();

  public ShapeType getShapeType() {
    return shapeType;
  }

  public int getShapeTypeValue() {
    switch (shapeType) {
      case Square: return 0;
      case Rectangle: return 1;
      case Circle: return 2;
      case Oval: return 3;
      case Line: return 4;
      default: return 5;
    }
  }

  public int getOrderOfArrival() {
    return orderOfArrival;
  }

  public void debug() {
    System.out.printf
      ("Shape(id %d, order %d, dist %d, area %d, width %d, height %d)\n",
       id, orderOfArrival, (int) getDistance(),
       (int) getArea(), getWidth(), getHeight());
  }

  public String toString() {
    return "Shape()";
  }
}
