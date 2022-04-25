package examples.pubhub.dao;

import examples.pubhub.model.*;

import java.util.List;

public class TestBookDAO {
	
 public static void main(String[] args) {
    BookDAO dao = new BookDAOImpl();
    List<Book> list = dao.getBooksByTitle("Soccer");
    
    for (int i = 0; i < list.size(); i++){
	      Book t = list.get(i);
	      System.out.println(t);
	    }
    
}
}
