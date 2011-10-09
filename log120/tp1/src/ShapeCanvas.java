import java.util.Enumeration;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;

class ShapeCanvas extends JPanel {
	private static final long serialVersionUID = 1L;
  private final int allocated = 30;
  private int used;
  private Shape[] shapes;

	public ShapeCanvas() {
		setSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		ShapeCanvas.this.setBackground(Color.white);
    /* 
     * Assuming that we receive ~1 shape per second, we allocate enough
     * memory for the next 10 seconds each increment.
     * Ideally, this canvas would use a form of circular buffer with a
     * fixed maximum size to prevent getting an infinite amount of
     * allocated memory as the time goes to infinity
     */
    shapes = new Shape[allocated]; 
    used = 0;
 		this.setBackground(Color.white);  
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

    System.out.println("painting");
    if (used == 0) {
      System.out.println(used);
      return;
    }

	  Graphics2D g2d = (Graphics2D) g;
	  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);

    int i = 0;
    System.out.println("decided to paint");
    while (i < used) {
      shapes[i].draw(g2d);
    }
	}

  public void addShape(Shape s) {
    System.out.println("added a shape");
    if (used >= allocated) {
      used = 0;
      System.out.println("used = 0");
    }
    shapes[used++] = s;
    repaint();
    System.out.println("repainted");
  }
}
