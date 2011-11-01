public class LinkedList<T> {

  private Link<T> first;
  private int length;

  public LinkedList() {
    length = 0;
    first = null;
  }

  public Link<T> getFirst() {
    return first;
  }

  public void add(T obj) {
    if (first == null) {
      first = new Link<T>(obj, length++, null);
      return;
    }

    Link<T> last = getLastLink();
    Link<T> element = new Link<T>(obj, length++, null);
    last.setNext(element);
  }

  private Link<T> getLastLink() {
    Link<T> link = first;
    // What happens if first = null?
    while (link.next() != null)
      link = link.next();
    return link;
  }

  public T getLast() {
    Link<T> e = getLastLink();
    if (e != null)
      return e.get();
    else
      return null;
  }

  private Link<T> getLink(int index) {
    if (index > length)
      return null;

    Link<T> link = first;
    int i = 0;
    while (i != index) {
      link = link.next();
      if (link == null)
        return null;
      else
        ++i;
    }

    return link;
  }

  public T get(int index) {
    if (index < 0 || index >= length) {
      throw new IndexOutOfBoundsException();
    }

    Link<T> e = getLink(index);
    return e.get();
  }

  public T set(int index, T obj) {
    Link<T> link = getLink(index);
    T replaced = link.get();
    link.set(obj);  
    return replaced;
  }

  public int size() {
    return length;
  }

}
