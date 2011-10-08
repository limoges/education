
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class ShapeFactory {

  public static Shape create(String command) {
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
    if (strData.length < 4)
      return null;

    long id = 0;
    int[] data = new int[4];
    try {
      id = Long.parseLong(strId);
      data[0] = Integer.parseInt(strData[0]);
      data[1] = Integer.parseInt(strData[1]);
      data[2] = Integer.parseInt(strData[2]);
      data[3] = Integer.parseInt(strData[3]);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println(shape);
    if (shape.equals("LIGNE"))
      return new Line(id, data[0], data[1], data[2], data[3]);
    if (shape.equals("CARRE") || shape.equals("RECTANGLE"))
      return new Rectangle(id, data[0], data[1], data[2], data[3]);
    if (shape.equals("CERCLE") || shape.equals("OVAL"))
      return new Oval(id, data[0], data[1], data[2], data[3]);

    return null;
  }

}
