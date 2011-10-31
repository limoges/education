
public class LinkedList<T> {

  public class Link<Type> {
    private T obj;
    private Link<Type> next;
    private int index;

    public Link(T obj, int index, Link<Type> next) {
      this.obj = obj;
      this.next = next;
    }

    public String toString() {
      return "Link(" + obj.toString() +")";
    }

    public void setNext(Link<T> next) {
      this.next = next;
    }

    public Link<Type> next() {
      return next;
    }

    public Type get() {
      return obj;
    }
  }

  private Link<T> first;
  private int length;

  public void LinkedList() {
    length = 0;
    first = null;
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
    while (link.getNext() != null)
      link = link.getNext();
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

  public int size() {
    return length;
  }
}
