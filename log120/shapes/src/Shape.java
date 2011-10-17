import java.awt.Graphics2D;
import java.awt.Color;

public abstract class Shape {

  // You shouldnt change a id
	private final int id;
  private Color color;
	
  /*
   * Constructor
   * @param id The IDLogger id
   * @param color The color to draw the shape with
   */
	public Shape(int id, Color color) {
		this.id = id;
    this.color = color;
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
}
