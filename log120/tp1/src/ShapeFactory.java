
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.Color;

class ShapeFactory {

  public static Shape create(String command) {
    System.out.println("pre : " + command);
    Pattern p = Pattern.compile("([0-9]*)");
    Matcher m = p.matcher(command);
    m.find();
    String strId = m.group(0);

    p = Pattern.compile("<(.*)> (.*)</\\1>");
    m = p.matcher(command);
    m.find();

    String shape = m.group(1);
    String[] strData = m.group(2).split("[ ]");

    // we don't want ArrayIndexOutOfBound exceptions
    if (strData.length < 3)
      return null;

    long id = 0;
    int[] data = new int[4];
    try {
      id = Long.parseLong(strId);
      data[0] = Integer.parseInt(strData[0]);
      data[1] = Integer.parseInt(strData[1]);
      data[2] = Integer.parseInt(strData[2]);
      // this one is useless if we encounter a circle
      if (strData.length > 3)
        data[3] = Integer.parseInt(strData[3]);
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    System.out.println("shape(" + shape + ") x(" + data[0] + ") y(" + data[1] + ") 1(" + data[2] + ") 2(" + data[3] + ")");

    // Line uses 2 points
    if (shape.equals("LIGNE")) {
      return new Line(id, Color.CYAN, data[0], data[1], data[2], data[3]);
    }
    // Rectangle uses a point and horizontal + vertical dimensions
    // Square is a subset of Rectangle
    if (shape.equals("CARRE")) {
      return new Rectangle(id, Color.RED, data[0], data[1], Math.abs(data[2]-data[0]), Math.abs(data[3]-data[1]));
    }
    if (shape.equals("RECTANGLE")) {
      return new Rectangle(id, Color.GREEN, data[0], data[1], Math.abs(data[2]-data[0]), Math.abs(data[3]-data[1]));
    }
    // Oval uses a center point, not top left, and horizontal + vertical diameters, not radii
    // Cercle is a subset of Oval
    if (shape.equals("CERCLE")) {
      return new Oval(id, Color.PINK, data[0]-data[2], data[1]-data[2], data[2]*2, data[2]*2);
    }
    if (shape.equals("OVALE")) {
      return new Oval(id, Color.BLUE, data[0]-data[2], data[1]-data[3], data[2]*2, data[3]*2);
    }

    return null;
  }

}
