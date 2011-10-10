
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class ShapeParser {
  public static final String[] parse(String command) {
    Pattern p = Pattern.compile("([0-9]*)");    
    Matcher m = p.matcher(command);             
    m.find();                               
    String strId = m.group(0);                  
                                                
    p = Pattern.compile("<(.*)> (.*)</\\1>");   
    m = p.matcher(command);                     
    m.find();                                   
                                                
    String shape = m.group(1);                  
    String[] strData = m.group(2).split("[ ]"); 
    String[] strings = new String[2 + strData.length];

    strings[0] = strId;
    strings[1] = shape;
    for (int i = 2, n = 0; i < strings.length && n < strData.length; ++i, ++n)
      strings[i] = strData[n];

    return strings;
  }
}
