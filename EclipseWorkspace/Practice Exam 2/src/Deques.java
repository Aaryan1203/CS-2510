class Deque<T> {
  Sentinel<T> header;


  //returns the size of this deque
  int size() {
    return this.header.sizeHelp();
  }

  // Appends the other list to this list and removes everything from the given list
  void append(Deque<T> other) {
    this.header.prev.updateNext(other.header.next); 
    other.header.prev.updateNext(this.header);
    
//    while (other.size() > 0) {
//      this.addToTail(other.removeFromHead());
//    }
  }
}

abstract class ANode<T> {
  ANode<T> next;
  ANode<T> prev;

  abstract int size();
  
  abstract void updateNext(ANode<T> other);

}
class Sentinel<T> extends ANode<T> { 

  int sizeHelp() {
    return this.next.size();
  }

  int size() {
    return 0;
  }

  // Updates the next
  void updateNext(ANode<T> other) {    
  }
} 

class Node<T> extends ANode<T> {

  T data; 

  int size() {
    return 1 + this.next.size();
  }
  
  // Updates the next
  void updateNext(ANode<T> other) {
    this.next = other;
    other.prev = this;
  }
}