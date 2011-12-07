
public class ViewerController {
  
  private static ViewerController instance;

  private ViewerController() {
    // Empty controller
  }

  public static ViewerController getInstance() {
    if (instance == null)
      instance = new ViewerController();
    return instance;
  }

  public void undo() {
    // undo
  }

  public void redo() {
    // redo
  }

}

