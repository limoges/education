import java.util.Vector;
import java.util.Enumeration;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;

class ShapeCanvas extends JPanel {
	private static final long serialVersionUID = 1L;
  private Shape[] shapes;
  private int used, current;

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
    shapes = new Shape[10]; 
    current = used = 0;
 		this.setBackground(Color.white);  
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

    if (used == 0)
      return;

	  Graphics2D g2d = (Graphics2D) g;
	  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);

    int i = 0;
    while (i < used)
      shapes[i++].draw(g2d);
	}

  public void addShape(Shape s) {
    if (current == shapes.length)
      current = 0;
    if (used < shapes.length)
      ++used;
    // TODO is this necessary? there is a definite vagueness with memory leaks in java
    // Memory leak can happen here
    if (shapes[current] != null) {
      Shape a = shapes[current];
      // The object reference is removed
      a = null;
    }
    // The reference to the object reference is overwrote
    shapes[current++] = s;
    repaint();
  }
}
