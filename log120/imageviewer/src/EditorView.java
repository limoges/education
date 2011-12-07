
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.BorderFactory;

public class EditorView extends JPanel {

  private EditorModel editor;

  public EditorView() {
    super();
    this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
  }

  public void paintComponent(Graphics g) {
    g.drawImage(editor.getImage(), 0, 0, null);
  }
}

