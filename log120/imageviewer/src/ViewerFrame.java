
import java.io.*;
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
        editorPane.addPerspective(view);
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
    JMenuItem serialAction = new JMenuItem("Save Perspective");                
    serialAction.addActionListener(new ActionListener() {          
      public void actionPerformed(ActionEvent e) {               
        try {
          FileOutputStream fout = new FileOutputStream("data.pers");
          ObjectOutputStream oos = new ObjectOutputStream(fout);
          Perspective p = editorPane.getPerspective();
          if (p == null)
            return;
          oos.writeObject(p.toPerspectiveState());
          oos.close();
        }
        catch (Exception ex) {
          System.err.println("Error writing the perspective to 'data.pers'");
        }
      }                                                          
    });                                                          
    fileMenu.add(serialAction);                                    
    JMenuItem loadAction = new JMenuItem("Load Perspective");                 
    loadAction.addActionListener(new ActionListener() {                       
      public void actionPerformed(ActionEvent e) {                              
        try {                                                                
          FileInputStream fin = new FileInputStream("data.pers");         
          ObjectInputStream ois = new ObjectInputStream(fin);             
          PerspectiveState state = (PerspectiveState) ois.readObject();
          ois.close();                                                       
          Perspective p = editorPane.getPerspective();
          if (p == null)
            return;
          p.fromPerspectiveState(state);
        }                                                                    
        catch (Exception ex) {
          System.out.println("Error loading the perspective from 'data.pers'");
        }                       
      }                                                                         
    });                                                                         
    fileMenu.add(loadAction);                                                 
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

