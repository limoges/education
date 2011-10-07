import java.awt.Graphics2D;

public abstract class Shape {

	private long id;
	
	public Shape(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

  public abstract void draw(Graphics2D g);
}
