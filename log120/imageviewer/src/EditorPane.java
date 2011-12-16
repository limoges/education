
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EditorPane extends JPanel {

  private JPanel editingArea;
  private JPanel thumbnailArea;;
  
  public EditorPane() {
    super();
    
    Dimension dimension = new Dimension(EP_WIDTH, EP_HEIGHT);
    setPreferredSize(dimension);
    setMinimumSize(dimension);
    setMaximumSize(dimension);
    setLayout(new BorderLayout());

    editingArea = createEditingArea();
    thumbnailArea = createThumbnailArea();
    add(thumbnailArea, BorderLayout.LINE_START);
    add(editingArea, BorderLayout.LINE_END);
  }

  public void addThumbnail(ThumbnailView thumbnail) {
    thumbnailArea.add(thumbnail);
  }

  public void setEditingView(PerspectiveView view) {
    editingArea.removeAll();
    editingArea.add(view);
  }

  private JPanel createThumbnailArea() {
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(EP_THUMBNAIL_WIDTH, 600));
    panel.setBackground(Color.DARK_GRAY);
    return panel;
  }

  private JPanel createEditingArea() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(Color.BLACK);
    return panel;
  }

  private static int EP_THUMBNAIL_WIDTH = 194;
  private static int EP_WIDTH = 800;
  private static int EP_HEIGHT = 600;
}

