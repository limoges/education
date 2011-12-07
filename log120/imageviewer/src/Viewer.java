
public class Viewer {

  private ViewerController controller;

  private Viewer() {
    controller = ViewerController.getInstance();
    new ViewerFrame().setVisible(true);
  }

  public static void main(String[] args) {
    new Viewer();
  }

}

