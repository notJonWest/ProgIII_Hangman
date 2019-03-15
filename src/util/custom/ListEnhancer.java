package util.custom;

import linked_data_structures.*;

/**
 * 
 * @author Jonathan West
 * Since we are not permitted to modify the linked_data_structures package,
 * I have created this class to make code less repetitive and easier to maintain
 */
public interface ListEnhancer
{
	//SinglyLinkedList
	public static <E> void addToEnd(SinglyLinkedList<E> list, E el)
	{
		list.add(el, list.getLength());
	}
	
	public static <E> SinglyLinkedList<Integer> getIndices(SinglyLinkedList<E> list, E el)
	{
		SinglyLinkedList<Integer> indices = new SinglyLinkedList<Integer>();
		for (int i = 0; i < list.getLength(); i++)
			if (list.getElementAt(i).equals(el))
				indices.add(i);
		return indices;
	}
	
	public static <E> boolean includes(SinglyLinkedList<E> list, E el)
	{
		int i = 0;
		boolean inList = false;
		
		while (!inList && i < list.getLength())
		{
			inList = (list.getElementAt(i).equals(el));
			i++;
		}
		
		return inList;
	}
	
	public static boolean includes(SinglyLinkedList<Character> list, Character ch, boolean ignoreCase)
	{
		int i = 0;
		boolean inList = false;
		
		while (!inList && i < list.getLength())
		{
			if (ignoreCase)
				inList = (list.getElementAt(i).toString().equalsIgnoreCase(ch.toString()));
			else
				inList = (list.getElementAt(i).toString().equals(ch.toString()));
			i++;
		}
		
		return inList;
	}
	
	public static <E> boolean containsAll(SinglyLinkedList<E> list, SinglyLinkedList<E> list2)
	{
		boolean flag = true;
		int i = 0;
		while (i < list2.getLength() && flag)
		{
			flag = ListEnhancer.includes(list, list2.getElementAt(i));
			i++;
		}
		return flag;
	}
	
	public static <E> boolean containsAll(SinglyLinkedList<E> list, DoublyLinkedList<E> list2)
	{
		boolean flag = true;
		int i = 0;
		while (i < list2.getLength() && flag)
		{
			flag = ListEnhancer.includes(list, list2.getElementAt(i));
			i++;
		}
		return flag;
	}
	
	public static <E> boolean containsAll(SinglyLinkedList<Character> list, SinglyLinkedList<Character> list2, boolean ignoreCase)
	{
		boolean flag = true;
		int i = 0;
		while (i < list2.getLength() && flag)
		{
			flag = ListEnhancer.includes(list, list2.getElementAt(i), ignoreCase);
			i++;
		}
		return flag;
	}
	
	public static <E> boolean containsAll(SinglyLinkedList<Character> list, DoublyLinkedList<Character> list2, boolean ignoreCase)
	{
		boolean flag = true;
		int i = 0;
		while (i < list2.getLength() && flag)
		{
			flag = ListEnhancer.includes(list, list2.getElementAt(i), ignoreCase);
			i++;
		}
		return flag;
	}
	
	public static <E> String stringList(SinglyLinkedList<E> list)
	{
		return stringList(list, ", ");
	}
	
	public static <E> String stringList(SinglyLinkedList<E> list, String sep)
	{
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < list.getLength(); i++)
		{
			str.append(list.getElementAt(i).toString());
			if (i != list.getLength() - 1)
				str.append(sep);
		}
		return str.toString();
	}
	
	public static SinglyLinkedList<Character> stringToSList(String str)
	{
		SinglyLinkedList<Character> strList = new SinglyLinkedList<Character>();
		for (int i = str.length() - 1; i >= 0; i--)
			strList.add(str.charAt(i));
		return strList;
	}
	
	
	//DoublyLinkedList
	public static <E> void addToEnd(DoublyLinkedList<E> list, E el)
	{
		list.add(el, list.getLength());
	}
	
	public static <E> SinglyLinkedList<Integer> getIndices(DoublyLinkedList<E> list, E el)
	{
		SinglyLinkedList<Integer> indices = new SinglyLinkedList<Integer>();
		for (int i = 0; i < list.getLength(); i++)
			if (list.getElementAt(i).equals(el))
				indices.add(i);
		return indices;
	}
	
	public static <E> boolean includes(DoublyLinkedList<E> list, E el)
	{
		int i = 0;
		boolean inList = false;
		
		while (!inList && i < list.getLength())
		{
			inList = (list.getElementAt(i).equals(el));
			i++;
		}
		
		return inList;
	}
	
	public static boolean includes(DoublyLinkedList<Character> list, Character ch, boolean ignoreCase)
	{
		int i = 0;
		boolean inList = false;
		
		while (!inList && i < list.getLength())
		{
			if (ignoreCase)
				inList = (list.getElementAt(i).toString().equalsIgnoreCase(ch.toString()));
			else
				inList = (list.getElementAt(i).toString().equals(ch.toString()));
			i++;
		}
		
		return inList;
	}
	
	public static <E> boolean containsAll(DoublyLinkedList<E> list, DoublyLinkedList<E> list2)
	{
		boolean flag = true;
		int i = 0;
		while (i < list2.getLength() && flag)
		{
			flag = ListEnhancer.includes(list, list2.getElementAt(i));
			i++;
		}
		return flag;
	}
	
	public static <E> boolean containsAll(DoublyLinkedList<E> list, SinglyLinkedList<E> list2)
	{
		boolean flag = true;
		int i = 0;
		while (i < list2.getLength() && flag)
		{
			flag = ListEnhancer.includes(list, list2.getElementAt(i));
			i++;
		}
		return flag;
	}
	
	public static boolean containsAll(DoublyLinkedList<Character> list, DoublyLinkedList<Character> list2, boolean ignoreCase)
	{
		boolean flag = true;
		int i = 0;
		while (i < list2.getLength() && flag)
		{
			flag = ListEnhancer.includes(list, list2.getElementAt(i), ignoreCase);
			i++;
		}
		return flag;
	}
	
	public static boolean containsAll(DoublyLinkedList<Character> list, SinglyLinkedList<Character> list2, boolean ignoreCase)
	{
		boolean flag = true;
		int i = 0;
		while (i < list2.getLength() && flag)
		{
			flag = ListEnhancer.includes(list, list2.getElementAt(i), ignoreCase);
			i++;
		}
		return flag;
	}

	public static <E> String stringList(DoublyLinkedList<E> list)
	{
		return stringList(list, ", ");
	}
	
	public static <E> String stringList(DoublyLinkedList<E> list, String sep)
	{
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < list.getLength(); i++)
		{
			str.append(list.getElementAt(i).toString());
			if (i != list.getLength() - 1)
				str.append(sep);
		}
		return str.toString();
	}
	
	public static DoublyLinkedList<Character> stringToDList(String str)
	{
		DoublyLinkedList<Character> strList = new DoublyLinkedList<Character>();
		for (int i = str.length() - 1; i >= 0; i--)
			strList.add(str.charAt(i));
		return strList;
	}
}
