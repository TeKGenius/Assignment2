import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * BookListWindow
 */
public class BookListWindow extends JFrame implements ActionListener{

    //======== Top ========
    private JPanel topPanel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton clearButton;

    //======== Middle ========
    private JScrollPane titleListScrollPane;
    private JList<String> bookTitleList;

    //======== Bottom ========
    private JPanel bottomPanel;
    private JButton addButton;
    private JButton detailButton;
    private JButton removeButton;

    //======== Data ========
    private BookStorage bookStorage;
    private BookArrayModel bookListModel;

    public BookListWindow(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
        bookListModel = new BookArrayModel(bookStorage.getAll());
        initComponents();
    }

    /**
     * Clears the search results and list all the books.
     */
    public void resetToAll() {
        bookListModel.setBookArray(bookStorage.getAll());
        searchTextField.setText("");
        bookTitleList.updateUI();
    }

    /**
     * Returns the book storage.
     */
    public BookStorage getBookStorage() {
        return bookStorage;
    }

    /**
     * Initializes the components.
     */
    private void initComponents() {
        Container contentPane = getContentPane();
        this.setTitle("Book Management");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //======== Top ========
        topPanel = new JPanel();
        searchTextField = new JTextField();
        searchButton = new JButton("SEARCH");
        clearButton = new JButton("CLEAR");

        searchButton.addActionListener(this);
        clearButton.addActionListener(this);

        {
            // Set the layout for topPanel and add the buttons.
        	topPanel.setLayout(new BorderLayout());
        	searchTextField.setColumns(10);
        	topPanel.add(searchTextField, BorderLayout.WEST);
        	topPanel.add(searchButton, BorderLayout.CENTER);
        	topPanel.add(clearButton, BorderLayout.EAST);
        	
        	
        	
        }

        //======== Middle ========
        titleListScrollPane = new JScrollPane();
        bookTitleList = new JList<>();

        {
            // Configure the bookTitleList 1) Use single selection
            //TODO Add your code here...
        	BookStorage storaging = new BookStorage();
        	storaging.initBooks();
        	Book[] books = new Book[storaging.getAll().length];
        	books = storaging.getAll();
        	String[] showing = new String[books.length];
        	for(int i = 0; i < storaging.getAll().length; i++) {
        		if(books[i] != null) {
        			if(books[i+1] != null) {
        				showing[i] = books[i].getTitle();
        			}
            		else {
            			break;
            		}
        		}

        	}

        	bookTitleList.setListData(showing);
        	titleListScrollPane.add(bookTitleList);

        }

        titleListScrollPane.setViewportView(bookTitleList);

        //======== Bottom ========
        bottomPanel = new JPanel();
        addButton = new JButton("ADD");
        detailButton = new JButton("DETAIL");
        removeButton = new JButton("REMOVE");

        addButton.addActionListener(this);
        detailButton.addActionListener(this);
        removeButton.addActionListener(this);

        {
            // Set the layout for bottomPanel and add the buttons.
            // TODO Add your code here...
        	bottomPanel.setLayout(new BorderLayout());
        	bottomPanel.add(addButton, BorderLayout.WEST);
        	bottomPanel.add(detailButton, BorderLayout.CENTER);
        	bottomPanel.add(removeButton, BorderLayout.EAST);

        }

        contentPane.setLayout(new BorderLayout());
        {
            // Add the components to contentPane with proper layout options.
            // TODO Add your code here...
        	contentPane.add(topPanel, BorderLayout.NORTH);
        	contentPane.add(titleListScrollPane, BorderLayout.CENTER);
        	contentPane.add(bottomPanel, BorderLayout.SOUTH);
        	

        }

        pack();
        setLocationRelativeTo(getOwner());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == searchButton) {
           // Action for the SEARCH button
           // TODO Add your code here...
    	   BookStorage storaging = new BookStorage();
    	   Book[] searched = storaging.titleSearch(searchTextField.getText());
       		String[] showing = new String[searched.length];
       		for(int i = 0; i < storaging.getAll().length; i++) {
       		if(searched[i] != null) {
       			if(searched[i+1] != null) {
       				showing[i] = searched[i].getTitle();
       				System.out.println(i);
       			}
           		else {
           			System.out.println(i);
           			break;
           		}
       		}

       	}

       	bookTitleList.setListData(showing);
    	titleListScrollPane.revalidate();

       } else if (e.getSource() == clearButton) {
           // Action for the CLEAR button
           // TODO Add your code here...
    	   BookStorage storaging = new BookStorage();
    	   storaging.initBooks();
    	   Book[] books;
    	   books = storaging.getAll();
    	   String[] showing = new String[books.length];

    	   for(int j = 0; j < books.length; j++){
    	       if()
           }

    	   for(int i = 0; i < storaging.getAll().length; i++) {
       			if(books[i] != null) {
                    showing[i] = books[i].getTitle();
       			}
               else if(books[i+1] != null) {
                   showing[i] = books[i].getTitle();
               }
               else {
                   break;
               }
       			
    	   }
    	   bookTitleList.setListData(showing);
    	   titleListScrollPane.revalidate();

       } else if (e.getSource() == addButton) {
           // Action for the ADD button
           // TODO Add your code here...
    	   AddBookDialog adder = new AddBookDialog(this);
    	  
    	   //adder.doSave();

       } else if (e.getSource() == detailButton) {
           // Action for the DETAIL button
           // TODO Add your code here...
    	   

       } else if (e.getSource() == removeButton) {
           // Action for the REMOVE button
           if (!bookTitleList.isSelectionEmpty()) {
               bookStorage.remove(bookTitleList.getSelectedValue());
               JOptionPane.showMessageDialog(this, "Remove Successful!");
               resetToAll();
           }
       }
    }

    public static void main(String[] args) {
        BookStorage bookStore = new BookStorage();
        bookStore.initBooks();
        BookListWindow bookListWindow = new BookListWindow(bookStore);
        bookListWindow.setVisible(true);
    }
}
