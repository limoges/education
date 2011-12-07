
import javax.swing.JPanel;
import java.awt.Color;

public class EditorPane extends JPanel {

  public EditorPane() {
    setBackground(Color.DARK_GRAY);
  }

  public void addNewView() {
    add(new EditorView());
  }

}

