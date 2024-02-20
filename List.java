/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;
	
    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }

    /** Returns the number of elements in this list. */
    public int getSize() {
 	      return size;
    }

    /** Returns the first element in the list */
    public CharData getFirst() {
        return first.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {

        // create new Chardata object
        CharData c = new CharData(chr);
        // create a new node
        Node new_node = new Node(c, first);

        // set the first item as the new node
        this.first = new_node;

        // increase the size
        size ++;
    }
    
    /** GIVE Textual representation of this list. */
    public String toString() {

        // init the result varriable
        String result = "(";

        // update the first node of the list
        Node temp = this.first;

        // print each item in the list
        for (int i = 0; i < size; i++) {
            result += temp.toString() + " ";
            temp = temp.next;
        }

        // return the result
        return result.substring(0, result.length() - 1) + ')';
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public int indexOf(char chr) {

        // if empty list
        if (first == null){
            return -1;
        }

        // set new pointer on the first node
        Node n = this.first;
        int i = 0;
        for (; i < size; i++) {
            // check equals to the param
            if (n.cp.chr == chr){
                // return the current index
                return i;
            }

            // get the next item
            n = n.next;
        }

        // if not found
        return -1;
    }

    /** If the given character exists in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */
    public void update(char chr) {

        // check if the char in the list
        int index = this.indexOf(chr);

        // not in list
        if (index == -1){
            // add the new char
            addFirst(chr);
            return;
        }
        // get the node with the char
        Node n = first;
        boolean found = false;
        while (n != null && found == false){
            if (n.cp.chr == chr){
                // update counter
                found = true;
                n.cp.count ++;
            }
            // get the next item
            n = n.next;
        }


    }

    /** GIVE If the given character exists in one of the CharData objects
     *  in this list, removes this CharData object from the list and returns
     *  true. Otherwise, returns false. */
    public boolean remove(char chr) {

        // if the chr is not in list
        if (indexOf(chr) == -1){
            return false;
        }


        // get the first element
        Node current = first;
        // remove in case of first element
        if (current.cp.chr == chr){
            first = first.next;
            size --;
            return true;
        }

        // get the next item
        Node next = current.next;

        while (next != null){

            // remove the node
            if (next.cp.chr == chr){
                current.next = next.next;
                size --;
                return true;
            }

            // get the next element to iterate
            current = current.next;
            next = next.next;
        }


        return false;
    }

    /** Returns the CharData object at the specified index in this list. 
     *  If the index is negative or is greater than the size of this list, 
     *  throws an IndexOutOfBoundsException. */
    public CharData get(int index) {

        // if not valid index throw the exception
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        // get the first element
        Node n = first;

        // go to the index element
        for (int i = 0; i <= index ; i++) {
            // if found
            if (i == index){
                return n.cp;
            }

            // get the next element
            n = n.next;

        }

        // return the char of the current element
        return n.cp;
    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {

        // set new array with the size of the list
	    CharData[] arr = new CharData[size];

        // get the first element
	    Node current = first;
	    int i = 0;
        // set at each index the value of the current item
        while (current != null) {
    	    arr[i++]  = current.cp;
    	    current = current.next;
        }

        // return the new array
        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
	    // If the list is empty, there is nothing to iterate   
	    if (size == 0) return null;
	    // Gets the element in position index of this list
	    Node current = first;
	    int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
	    return new ListIterator(current);
    }
}
