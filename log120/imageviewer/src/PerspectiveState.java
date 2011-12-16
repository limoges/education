import java.io.*;

public class PerspectiveState implements Serializable {

  public int x;
  public int y;
  public int zoom;

  public PerspectiveState(int x, int y, int zoom) {
    this.x = x;
    this.y = y;
    this.zoom = zoom;
  }

  private void readObject(ObjectInputStream is)
    throws ClassNotFoundException, IOException {
    is.defaultReadObject();
  }

  private void writeObject(ObjectOutputStream os) throws IOException {
    os.defaultWriteObject();
  }

}  

