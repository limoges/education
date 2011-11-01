import java.util.Vector;
import java.util.Enumeration;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;

/*
 * A panel used to draw and store shapes
 */
class ShapeCanvas extends JPanel {
	private static final long serialVersionUID = 1L;
  //private Shape[] shapes;
  private LinkedList<Shape> shapes;
  private SortType sortType;
  private boolean ascending;
  private boolean sorted;
  private boolean sortActivated;

  public void setSort(boolean activated, boolean ascending, SortType type) {
    this.ascending = ascending;
    this.sortActivated = activated;
    this.sortType = type;
    sorted = false;
    repaint();
  }

  /*
   * Constructor
   * @param maxShapes The maximum number of shape that should be stored
   */
	public ShapeCanvas(int maxShapes) {
		setSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		ShapeCanvas.this.setBackground(Color.white);

    sortType = SortType.ORIGINAL;
    ascending = true;
    sorted = false;
    sortActivated = false;
    /* 
     * Assuming that we receive ~1 shape per second, we allocate enough
     * memory for the next 10 seconds each increment.
     * Ideally, this canvas would use a form of circular buffer with a
     * fixed maximum size to prevent getting an infinite amount of
     * allocated memory as the time goes to infinity
     */
    shapes = new LinkedList<Shape>(); 

 		this.setBackground(Color.white);  
	}

  /*
   * Draws the component
   * @param g The graphic context
   */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

    // No need to go further if we have nothing to draw
    if (shapes.size() == 0)
      return;

	  Graphics2D g2d = (Graphics2D) g;
	  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);

    if (!sortActivated) {
      int i = 0;
      Link<Shape> en = shapes.getFirst();
      do {
        if (en == null)
          break;
        en.get().draw(g2d);
        en = en.next();
      }
      while (true);
      return;
    }

    if (!sorted) {
      ShapeSort.sort(shapes, sortType, ascending);
      sorted = true;
    }

    Link<Shape> en = shapes.getFirst();
    System.out.println("------Sort by " + sortType + "--------");

    for (int x = 0, y = 0;; en = en.next(), y += 40, x += 40) {
      if (en == null)
        break;
      Shape sh = en.get();
      sh.drawAt(g2d, x, y);
      sh.debug();
    }
	}

  /*
   * Adds a shape to the storage and clears the extra shapes
   * Works in FIFO mode.
   * @param s The shape to add to the storage
   */
  public void addShape(Shape s) {
    shapes.add(s);
    repaint();
  }

  public void setShapes(LinkedList<Shape> shapes) {
    this.shapes = shapes;
    sorted = false;
    repaint();
  }
}
