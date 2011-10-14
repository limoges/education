
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.Color;
import ca.etsmtl.log.util.IDLogger;

class ShapeFactory {

  private static IDLogger logger;

  /*
   * Creates a shape object given the shape command
   * @return A shape corresponding to the command given
   */
  public static Shape create(String command) {
    if (logger == null)
      logger = IDLogger.getInstance();
    // Technically, a well made command would be [0] <[1]> [2] [3] [4] [5] </[1]>
    String[] strings = ShapeParser.parse(command);

    // we don't want ArrayIndexOutOfBound exceptions
    // there must have been a problem...
    if (strings.length < 4)
      return null;

    int id = 0;
    int[] data = new int[strings.length - 2];
    try {
      id = Integer.parseInt(strings[0]);
      data[0] = Integer.parseInt(strings[2]);
      data[1] = Integer.parseInt(strings[3]);
      data[2] = Integer.parseInt(strings[4]);
      // this one is useless if we encounter a circle
      if (strings.length > 5)
        data[3] = Integer.parseInt(strings[5]);
    }
    catch (Exception e) {
      System.out.println("exception");
      e.printStackTrace();
    }

    logger.logID(id);
    // Line uses 2 points
    if (strings[1].equals("LIGNE")) {
      return new Line(id, Color.CYAN, data[0], data[1], data[2], data[3]);
    }
    // Rectangle uses a point and horizontal + vertical dimensions
    // Square is a subset of Rectangle
    if (strings[1].equals("CARRE")) {
      return new Rectangle(id, Color.RED, data[0], data[1],
          Math.abs(data[2]-data[0]), Math.abs(data[3]-data[1]));
    }
    if (strings[1].equals("RECTANGLE")) {
      return new Rectangle(id, Color.GREEN, data[0], data[1],
          Math.abs(data[2]-data[0]), Math.abs(data[3]-data[1]));
    }
    // Oval uses a center point, not top left, and horizontal + vertical diameters, not radii
    // Cercle is a subset of Oval
    if (strings[1].equals("CERCLE")) {
      return new Oval(id, Color.PINK, data[0]-data[2], data[1]-data[2], data[2]*2, data[2]*2);
    }
    if (strings[1].equals("OVALE")) {
      return new Oval(id, Color.BLUE, data[0]-data[2], data[1]-data[3], data[2]*2, data[3]*2);
    }

    return null;
  }

}
