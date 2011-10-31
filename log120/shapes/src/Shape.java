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

  // You shouldnt change an id
	private final int id;
  private Color color;
  private ShapeType shapeType;
	
  /*
   * Constructor
   * @param id The IDLogger id
   * @param color The color to draw the shape with
   */
	public Shape(int id, Color color, ShapeType shapeType) {
		this.id = id;
    this.color = color;
    this.shapeType = shapeType;
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
  public abstract double getDistance();
  public abstract double getArea();

  public ShapeType getShapeType() {
    return shapeType;
  }

  public String toString() {
    return "Shape(" + color.toString() + ")";
  }
}
