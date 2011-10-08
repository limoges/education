import java.util.Vector;
import java.util.Enumeration;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;

class ShapeCanvas extends JPanel {
	private static final long serialVersionUID = 1L;
  private Vector<Shape> shapes;

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
    shapes = new Vector<Shape>(10, 10); 
 		this.setBackground(Color.white);  
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

    if (shapes.isEmpty())
      return;

    Enumeration<Shape> e = shapes.elements();
	  Graphics2D g2d = (Graphics2D) g;
	  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);

    while (e.hasMoreElements()) {
      (e.nextElement()).draw(g2d);
    }
	}

  public void addShape(Shape s) {
    shapes.add(s);
  }
}
