import java.awt.Graphics2D;
import java.awt.Color;

public abstract class Shape {

	private long id;
  private Color color;
	
	public Shape(long id, Color color) {
		this.id = id;
    this.color = color;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

  public Color getColor() {
    return this.color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public abstract void draw(Graphics2D g);
}
