package list;

import problems.IsPalindrome;

public class Main {

    public static void main(String[] args) {
         // Create a new CursorLinkedList
    CursorLinkedList<Integer> list = new CursorLinkedList<>(10);

    // Create a new list
    int headNodeIndex = list.createList();

    // Insert elements at the head of the list
    list.insertAtHead(1, headNodeIndex);
    list.insertAtHead(2, headNodeIndex);
    list.insertAtHead(3, headNodeIndex);
    list.insertAtHead(2, headNodeIndex);
    list.insertAtHead(1, headNodeIndex);

    // Check if the list is a palindrome
    IsPalindrome  
    isPalindrome = new IsPalindrome(list);
    System.out.println("Is the list a palindrome? " + isPalindrome.isPalindrome(headNodeIndex));
    
        // Create another linked list
        // head = list.createList();
        // list.insertAtTail(1, head); 
        // list.insertAtTail(2, head);
        // list.insertAtTail(3, head);
        
        // Check if palindrome
        // isPal = list.isPalindrome(head);
        // System.out.println("Is Palindrome: " + isPal);
        
        // Integer x = 12;
        // Integer y = 20;
        
        // System.out.println("Original List: ");
        
        // SwapNode<Integer> swap = new SwapNode<>(list);
        // swap.swap(x, y, head);
        // System.out.println("List after swapping "+x+" and "+y+": "); 
        // list.traversList(head);
    }
}
