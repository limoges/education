
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Dimension;
import java.awt.Image;

public class ViewerFrame extends JFrame {

  private JFrame framePointer;
  private EditorPane editorPane;
  private CommandHistory history;

  public ViewerFrame() {
    super();
    framePointer = this;
    history = new CommandHistory();
    setTitle(VF_NAME);
    setJMenuBar(createMenuBar());
    editorPane = createEditorPane();
    setContentPane(editorPane);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
  }

  private EditorPane createEditorPane() {
    EditorPane contentPane = new EditorPane();
    contentPane.setOpaque(true);
    return contentPane;
  }

  private JMenuBar createMenuBar() {
    JMenu fileMenu = new JMenu("File");                         
    JMenu editMenu = new JMenu("Edit");                         
                                                                
    // New image                                                
    JMenuItem newAction = new JMenuItem("New image");           
    newAction.setAccelerator(
        KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)
    );
    newAction.addActionListener(new ActionListener() {          
      public void actionPerformed(ActionEvent e) {              
        String path = "images/test2.png";

        JFileChooser fc = new JFileChooser();
        int val = fc.showOpenDialog(framePointer);
        if (val == JFileChooser.APPROVE_OPTION) {
          File file = fc.getSelectedFile();
          path = file.getPath();
        }
        else return;
        
        Image img = new ImageIcon(path).getImage();
        if (img == null)
          return;
        
        PerspectiveView view = new PerspectiveView(img, history);
        editorPane.addThumbnail(new ThumbnailView(view.getPerspective()));
        editorPane.setEditingView(view);
        framePointer.validate();
      }                                                         
    });                                                         
    fileMenu.add(newAction);                                    
    // Exit                                                     
    JMenuItem exitAction = new JMenuItem("Exit");               
    exitAction.addActionListener(new ActionListener() {         
      public void actionPerformed(ActionEvent e) {              
        System.exit(0);
      }                                                         
    });                                                         
    fileMenu.add(exitAction);                                   
    // Undo                                                     
    JMenuItem undoAction = new JMenuItem("Undo");               
    undoAction.addActionListener(new ActionListener() {         
      public void actionPerformed(ActionEvent e) {              
        history.undo();
      }                                                         
    });                                                         
    undoAction.setAccelerator(                                       
        KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK) 
    );                                                              
    editMenu.add(undoAction);                                   
    // Redo                                                     
    JMenuItem redoAction = new JMenuItem("Redo");               
    redoAction.addActionListener(new ActionListener() {         
      public void actionPerformed(ActionEvent e) {              
        history.redo();
      }                                                         
    });                                                         
    redoAction.setAccelerator(                                       
        KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK) 
    );                                                              
    editMenu.add(redoAction);                                   
                                                                
    JMenuBar menuBar = new JMenuBar();                              
    menuBar.add(fileMenu);                                          
    menuBar.add(editMenu);                                          

    return menuBar;
  }

  private String VF_NAME = "Viewer";
  private int VF_WIDTH = 800;
  private int VF_HEIGHT = 600;
}

