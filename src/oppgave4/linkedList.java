package oppgave4;

import java.util.ListIterator;

public class linkedList 
{
	public static void main(String[] args) 
	{
		TwoWayLinkedList<Integer> list = new TwoWayLinkedList<Integer>();
	    list.add(1);
	    list.add(2);
	    list.add(3);
	    list.add(4);
	
	    java.util.ListIterator<Integer> iterator = list.iterator();
	    iterator.next();
	    iterator.next();
	    iterator.next();
	
	    System.out.println();
	    while (iterator.hasPrevious())
	    System.out.print(iterator.previous() + " ");
	}

	static class TwoWayLinkedList<E> extends java.util.AbstractSequentialList<E> 
	{
	    private Node<E> head, tail;
	    private int size;
	
	    public TwoWayLinkedList() 
	    {
	    }
	
	    public TwoWayLinkedList(E[] objects) 
	    {
	    	for (E e : objects)
	        add(e);
	    }

	    public E getFirst() 
	    {
	    	if (size == 0) 
	    	{
	    		return null;
	    	} 
	    	else 
	    	{
	    		return head.element;
	    	}
	    }

	    public E getLast() 
	    {
	    	if (size == 0) 
	    	{
	    		return null;
	    	} 
	    	else 
	    	{
	    		return tail.element;
	    	}
	    }	

	    public void addFirst(E e) 
	    {
	    	Node<E> newNode = new Node<E>(e); 
	    	newNode.next = head; 
	    	head = newNode; 
	    	size++; 

	    	if (tail == null) 
	    		tail = head;

	    	if (head != tail)
	    		head.next.previous = head; 
	    }

    
	    public void addLast(E e) 
	    {
	    	Node<E> newNode = new Node<E>(e);

	    	Node<E> temp = tail;

	    	if (tail == null) 
	    	{
	    		head = tail = newNode;
	    	} 
	    	else 
	    	{
	    		tail.next = newNode; 
	    		tail = tail.next; 
	    	}

	    	size++; 

	    	tail.previous = temp; 
	    }

	    public void add(int index, E e) 
	    {
	    	if (index == 0) 
	    	{
	    		addFirst(e);
	    	} 
	    	else if (index >= size) 
	    	{
	    		addLast(e);
	    	} 
	    	else 
	    	{
	    		Node<E> current = head;
	    		for (int i = 1; i < index; i++) 
	    		{
	    			current = current.next;
	    		}
	    		Node<E> temp = current.next;
	    		current.next = new Node<E>(e);
	    		(current.next).next = temp;
	    		size++;
	    		
	    		temp.previous = current.next;
	    		current.next.previous = current;
	    	}
	    }

	    public E removeFirst() 
	    {
	    	if (size == 0) 
	    	{
	    		return null;
	    	} 
	    	else 
	    	{
	    		Node<E> temp = head;
	    		head = head.next;
	    		size--;
	    		if (head == null) 
	    		{
	    			tail = null;
	    		}
	    		return temp.element;
	    	}
	    }

	    public E removeLast() 
	    {
	    	if (size == 0) 
	    	{
	    		return null;
	    	} 
	    	else if (size == 1) 
	    	{
	    		Node<E> temp = head;
	    		head = tail = null;
	    		size = 0;
	    		return temp.element;
	    	} 
	    	else 
	    	{
	    		Node<E> current = head;

	    		for (int i = 0; i < size - 2; i++) 
	    		{
	    			current = current.next;
	    		}

		        Node<E> temp = tail;
		        tail = current;
		        tail.next = null;
		        size--;
		        return temp.element;
	    	}
	    }

	    public E remove(int index) 
	    {
	    	if (index < 0 || index >= size) 
	    	{
	    		return null;
	    	} 
	    	else if (index == 0) 
	    	{
	    		return removeFirst();
	    	} 
	    	else if (index == size - 1) 
	    	{
	    		return removeLast();
	    	} 
	    	else 
	    	{
	    		Node<E> previous = head;

	    		for (int i = 1; i < index; i++) 
	    		{
	    			previous = previous.next;
	    		}

		        Node<E> current = previous.next;
		        previous.next = current.next;
		        current.next.previous = previous; // For a two-way linked list
		        size--;
		        return current.element;
	    	}
	    }

	    @Override
	    public String toString() 
	    {
	    	StringBuilder result = new StringBuilder("[");

	    	Node<E> current = head;
	    	for (int i = 0; i < size; i++) 
	    	{
	    		result.append(current.element);
	    		current = current.next;
	    		if (current != null) 
	    		{
	    			result.append(", "); 
	    		} 
	    		else 
	    		{
	    			result.append("]");
	    		}
	    	}

	    	return result.toString();
	    }

	    public void clear() 
	    {
	    	head = tail = null;
	    }

	    public boolean contains(Object e) 
	    {
	    	System.out.println("Implementation left as an exercise");
	    	return true;
	    }

	    public E get(int index) 
	    {
	    	System.out.println("Implementation left as an exercise");
	    	return null;
	    }

	    public int indexOf(Object e) 
	    {
	    	System.out.println("Implementation left as an exercise");
	    	return 0;
	    }

	    public int lastIndexOf(Object e) 
	    {
	    	System.out.println("Implementation left as an exercise");
	    	return 0;
	    }

	    public E set(int index, E e) 
	    {
	    	System.out.println("Implementation left as an exercise");
	    	return null;
	    }

	    public java.util.ListIterator<E> iterator() 
	    {
	    	return new LinkedListIterator<E>();
	    }

	    private class LinkedListIterator<E> implements java.util.ListIterator<E> 
	    {
	    	private Node<E> current = (Node<E>)head; // Current index
	    	int index = 0;

	    	public LinkedListIterator() 
	    	{
	    	}
      
	    	public LinkedListIterator(int index) 
	    	{
	    		if (index < 0 || index > size)
	    			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
	    					+ size);
	    		for (int nextIndex = 0; nextIndex < index; nextIndex++)
	    			current = current.next;
	    	}
    
	    	public boolean hasNext() 
	    	{
	    		return (current != null);
	    	}

	    	public E next() 
	    	{
	    		E e = current.element;
	    		current = current.next;
	    		return e;
	    	}

	    	public void remove() 
	    	{
	    		System.out.println("Implementation left as an exercise");
	    	}

	    	public void add(E e) 
	    	{
	    		System.out.println("Implementation left as an exercise");
	    	}

	    	public boolean hasPrevious() 
	    	{
	    		return current != head;
	    	}

	    	public int nextIndex() 
	    	{
	    		System.out.println("Implementation left as an exercise");
	    		return 0;
	    	}

	    	public E previous() 
	    	{
	    		E e = current.element;
	    		current = current.previous;
	    		return e;
	    	}

	    	public int previousIndex() 
	    	{
	    		System.out.println("Implementation left as an exercise");
	    		return 0;
	    	}

	    	@Override
	    	public void set(E e) 
	    	{
	    		current.element = e; // TODO Auto-generated method stub
	    	}
	    }

	    public class Node<E> 
	    {
	    	E element;
	    	Node<E> next;
	    	Node<E> previous;

	    	public Node(E o) 
	    	{
	    		element = o;
	    	}
	    }

	    public int size() 
	    {
	    	return size;
	    }

	    public ListIterator<E> listIterator(int index) 
	    {
	    	return new LinkedListIterator<E>(index); 
	    }
	}
}