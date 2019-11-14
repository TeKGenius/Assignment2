import java.util.Arrays;
import java.util.Comparator;

/**
 * A collection of {@link Book}.
 */
public class BookStorage {

    private Book[] books = new Book[100];

    public BookStorage() {

    }

    /**
     * Initializes the book storage with some arbitrary book objects.
     */
    public void initBooks() {
        // TODO Add your code here...
    	int cat = 1; 
    	String[] titles = {"Cracking the Coding Interview", "Database Design for Mere Mortals", "The Design of Everyday Things", "Programming: Principles and Practice Using C++", "Fundamentals of Database Systems", "Design Is Storytelling", "Practical Programming for Strength Training", "Database Reliability Engineering", "Design: The Definitive Visual History", "Game Programming Patterns"};
    	String[] authors = {"Gayle Laakmann McDowell", "Michael J. Hernandez", "Dan Norman", " Bjarne Stroustrup", "Ramez Elmasri and  Shamkant B. Navathe", "Ellen Lupton", " Mark Rippetoe and Andy Baker", "Laine Campbell", "Robert Nystrom", "Christine W. Garcia"};
    	Integer[] pages = {706, 672, 368, 1312, 1280, 160, 300, 501, 651, 984};
    	
    	for(int i = 0; i < 10 ;i++) {
    		switch(cat) {
    			case 1:
    				books[i] = new Book(titles[i], authors[i], pages[i], Book.BookCategory.Programming);
    				cat += 1;
    			case 2:
    				books[i] = new Book(titles[i], authors[i], pages[i], Book.BookCategory.Database);
    				cat += 1;
    			case 3:
    				books[i] = new Book(titles[i], authors[i], pages[i], Book.BookCategory.Design);
    				cat = 1;
    		}
    	}
    	
    }

    /**
     * Uses the given book to update the existing book with the same title.
     */
    public void update(Book book) {
        // TODO Add your code here...
    	String title;
    	title = book.getTitle();
    	
    	for(int i = 0; i < books.length; i++) {
    		if(books[i].getTitle().equals(title)) {
    			books[i] = book;
    		}
    	}    	
    }

    /**
     * Removes a book by title.
     */
    public void remove(String bookTitle) {
        // TODO Add your code here...

    	for(int i = 0; i < books.length; i++) {
    		if(books[i].getTitle() != null){
				if(books[i].getTitle().equals(bookTitle)) {
					books[i] = null;
					break;
				}
			}

    	}    	    	
    }

    /**
     * Adds a new book.
     */
    public void add(Book book) {
        // TODO Add your code here...
    	for(int i = 0; i < books.length; i++) {
    		if(books[i] == null) {
    			books[i] = book;
    			break;
    		}
    	}    	
    	
    }

    /**
     * Gets a book by title.
     */
    public Book getByTitle(String title) {
    	
    	for(int i = 0; i < books.length; i++) {
    		if(books[i].getTitle().equals(title)) {
    			return books[i];
    		}
    	}
    	
    	return null;
    	
        
    }

    /**
     * Searches for books whose title contains the keyword and returns them ordered by titles (in alphabet order).
     */
    public Book[] titleSearch(String keyword) {
        // TODO Add your code here...
    	int num = 0;
    	
    	Book[] sortedBooks = new Book[books.length];
    	
    	for(int i = 0; i < books.length; i++) {
    		if(books[i] != null) {
    			if(books[i+1] != null) {
    				if(books[i].getTitle().contains(keyword)) {
        				sortedBooks[num] = books[i];
            			num += 1;
        			}
    			}
    			else {
    				break;
    			}
    		}
    	}
    	
    	Arrays.sort(sortedBooks, Comparator.nullsLast(Comparator.comparing(Book::getTitle)));     
    	
        return sortedBooks;
    }

    /**
     * Returns all books sorted by their titles (in alphabet order).
     */
    public Book[] getAll() {
        // TODO Add your code here...
    	
    	Book[] sortAllbook = new Book[books.length];
    	
    	sortAllbook = books;
    	
    	Arrays.sort(sortAllbook, Comparator.nullsLast(Comparator.comparing(Book::getTitle)));     	
    	
        return sortAllbook;
    }

    /**
     * Sorts an array of books by their titles in alphabet order.
     */
    private Book[] sortByTitle(Book[] bookArray) {
        // TODO Add your code here...
    	
    	Arrays.sort(bookArray, Comparator.nullsLast(Comparator.comparing(Book::getTitle)));     	

    	
        return bookArray;
    }

}
