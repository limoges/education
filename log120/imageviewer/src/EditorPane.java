
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EditorPane extends JPanel {

  private EditorPane pane;
  private JPanel editingArea;
  private JPanel thumbnailArea;
  private PerspectiveView current;
  private Hashtable<ThumbnailView, PerspectiveView> perspectives;
  
  public EditorPane() {
    super();
    pane = this;
    
    Dimension dimension = new Dimension(EP_WIDTH, EP_HEIGHT);
    setPreferredSize(dimension);
    setMinimumSize(dimension);
    setMaximumSize(dimension);
    setLayout(new BorderLayout());

    editingArea = createEditingArea();
    editingArea.setPreferredSize(
        new Dimension(EP_WIDTH - EP_THUMBNAIL_WIDTH, EP_HEIGHT)
    );

    perspectives = new Hashtable<ThumbnailView, PerspectiveView>();
    thumbnailArea = createThumbnailArea();
    add(thumbnailArea, BorderLayout.LINE_START);
    add(editingArea, BorderLayout.LINE_END);
  }

  public void addPerspective(PerspectiveView perspective) {
    ThumbnailView thumbnail = new ThumbnailView(perspective.getPerspective());
    perspectives.put(thumbnail, perspective);
    thumbnail.addMouseListener(new MouseAdapter() { 
      public void mouseClicked(MouseEvent e) {      
        PerspectiveView view = perspectives.get(e.getSource());
        if (view != null)
          setEditingView(view);
      }                                             
    });                                             
    thumbnailArea.add(thumbnail);                   
    setEditingView(perspective);
  }

  public void setEditingView(PerspectiveView view) {
    editingArea.removeAll();
    current = view;
    editingArea.add(view, BorderLayout.CENTER);
    editingArea.validate();
    editingArea.repaint();
  }

  public Perspective getPerspective() {
    return current.getPerspective();
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
  private static int EP_WIDTH = 1200;
  private static int EP_HEIGHT = 900;
}

