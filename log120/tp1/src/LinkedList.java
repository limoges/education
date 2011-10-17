
class Link<T> {

  private T current;
  private Link<T> next;

  public Link(T element) {
    current = element;
    next = null;
  }

  public T getElement() {
    return current;
  }

  public Link<T> getNext() {
    return next;
  }

  public void setNext(Link<T> nextLink) {
    next = nextLink;  
  }

  public String toString() {
    return current.toString();
  }

}

class LinkedList<T> {

  private int length;
  private Link<T> top;

  public LinkedList() {
    length = 0;
    top = null;
  }

  public void add(T element) {
    Link<T> current = new Link<T>(element);
    current.setNext(top);
    top = current;
  }

  public String toString() {
    String value = "[LinkedList](";
    Link<T> current = top;

    while (current == null) {
      value += current.toString() + ", ";
      current = current.getNext();
    }

    return value.substring(0, value.length()) + ")";
  }

  public static void main(String[] argv) {
    LinkedList<String> list = new LinkedList<String>();

    list.add(new String("First"));
    list.add(new String("Second"));
    list.add(new String("Third"));
    list.add(new String("Fourth"));

    System.out.println(list);
  }

}
