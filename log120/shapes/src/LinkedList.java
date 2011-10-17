
class Link<T> {
  private Link<T> previous;
  private T current;
  private int index;
  private Link<T> next;

  public Link(T element) {
    current = element;
  }

  public Link(T element, T previous) {
    this.previous = previous;
    current = element;
  }

  public String toString() {
    return current.toString();
  }

}

class LinkedList<T> {

  private Link<T> first;
  private Link<T> last;
  private int length;

  LinkedList() {
    first = null;
    last = null;
    length = 0;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public void add(T element) {
    current = new Link<T>(element);
    ++length;
  }

  public String toString() {
    Link<T> current = first;
    String value = "List : (";

    while (current != null) {
      value += current.toString() + ", ";
      current = current.getNext();
    }

    // We remove the ", " and add the ") "
    return value.substring(0, value.length()-2) + ") ";
  }

}
