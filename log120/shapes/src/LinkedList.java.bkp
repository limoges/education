class LinkedList<T> {

  // Link<T> definition is at the bottom
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
    if (first != null) {

      if (last != null) {
        last.setNext(new Link<T>(element, length++, null, last));
        last = last.getNext();
      }
      else {
        Link<T> current = new Link<T>(element, length++, null, first);
        first.setNext(current);
        last = current;
      }

    }
    else
      first = new Link<T>(element, length++, null, null);

  }

  public T get(int index) {
    if (index < 0 || index > this.length - 1)
      throw new IndexOutOfBoundsException();
    else
      return first.getLinkByIndex(index).get();
  }

  public void set(int index, T element) {
    // Maybe we should have this function at the LinkedList level
    // instead of the Link level
    Link<T> changed = this.first.getLinkByIndex(index);
    T obj = changed.get();
    changed.set(obj);
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

  public int size() {
    return length;
  }


  /*
   * Internal class for every Link of the chain
   */
  public class Link<T> {

    private T object;
    private int index;
    private Link<T> next;
    private Link<T> previous;

    /*
     * Constructor
     * @param obj The object to be contained in this link
     * @param index The index to associated with this link
     * @param next The next Link in the chain
     * @param previous The previous Link in the chain
     */
    public Link(T obj, int index, Link<T> next, Link<T> previous) {
      this.object = obj;
      this.index = index;
      this.next = next;
      this.previous = previous;
    }

    /*
     * Retrieves the last Link of the chain
     */
    public Link<T> getLast() {
      Link<T> last = this;

      // TODO improve this by avoiding recursive function calls
      if (this.getNext() != null) {
        last = last.getNext().getLast();
      }

      return last;
    }

    /*
     * Retrieves the Link which has the given index
     * @param The index of the Link to be retrieved
     */
    public Link<T> getLinkByIndex(int index) {
      Link<T> link = this;

      // TODO improve this by avoiding recursive function calls
      if (this.getIndex() != index) {
        link = link.getNext().getLast();
      }

      return link;
    }

    /*
     * Retrieves the index
     */
    public int getIndex() {
      return index;
    }

    /*
     * Sets the index
     * @param idx The index to be associated with the link
     */
    public void setIndex(int idx) {
      this.index = idx;
    }

    /*
     * Retrieves the object contained in the link
     */
    public T get() {
      return object;
    }

    /*
     * Sets the object held by the Link
     * @param obj The object that this link contains
     */
    public void set(T obj) {
      this.object = obj;
    }

    /*
     * Retrieves the next Link<T>
     */
    public Link<T> getNext() {
      return next;
    }

    /*
     * Sets the next Link<T> to the given Link<T>
     * @param next The Link<T> to be set as previous in the chain
     */
    public void setNext(Link<T> next) {
      this.next = next;
    }

    /*
     * Retrieves the previous Link<T>
     */
    public Link<T> getPrevious() {
      return previous;
    }

    /*
     * Sets the previous Link<T> to the given Link<T>
     * @param previous The Link<T> to be set as previous in the chain
     */
    public void setPrevious(Link<T> previous) {
      this.previous = previous;
    }

    /*
     * Get a string representing the object
     */
    public String toString() {
      return "Link(" + this.object.toString() + ")";
    }

  }

}
