package examples.pubhub.dao;


import examples.pubhub.dao.TagDAO;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;


public class TagDAOImpl implements TagDAO {
	
	Connection connection = null;  //connection to the database
	PreparedStatement stmt = null; //Useful to protect against SQL injection
	//End of Connection Database Statements

	
    @Override
	public List<Tag> getAllTags(){
		
		List<Tag> tags = new ArrayList<>();
		
		try {
			connection=DAOUtilities.getConnection();
			String sql = "SELECT tags FROM book_tags";
			stmt = connection.prepareStatement(sql);
			
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Tag tag = new Tag();
				
				
				tag.setTag(rs.getString("tags"));
				
				tags.add(tag);
				
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tags;
	}
	
	@Override
	public List<Tag> getAllBooks(){
		
		List<Tag> books = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT isbn_13 FROM book_tags";
			stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Tag book = new Tag();
				
				book.setIsbn13(rs.getString("isbn_13"));
				
				books.add(book);
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return books;
	}
	
	@Override
	public List<Tag> getTagsByBook(String isbn13){
		
		List<Tag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "Select * From book_tags where isbn_13 = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn13);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Tag tag = new Tag();
				
				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTag(rs.getString("tags"));
				
				tags.add(tag);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tags;
		
	}
	
	@Override
	public List<Book> getBooksByTag(String booktag){
		List<Book> books = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "Select * From books Where isbn_13 IN ( SELECT isbn_13 FROM book_tags WHERE tags = ?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, booktag);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Book book = new Book();
				
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				
				books.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return books;
		
	}
	
	@Override
	public Tag getBooksByISBN(String isbn) {
		Tag book = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String Sql = "Select * FROM book_tags WHERE isbn_13 = ?";
	        stmt = connection.prepareStatement(Sql);
	        
	        stmt.setString(1, isbn);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        if(rs.next()) {
	        	book = new Tag();
	        	book.setIsbn13(rs.getString("isbn_13"));
	        	
	        }
	        
      } catch (SQLException e) {
    	  e.printStackTrace();
      } finally {
    	  closeResources();
      }
		
	  return book;
	  
	  
	}
	
	@Override
	public boolean AddTag(Tag tag) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags VALUES (?, ?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag.getIsbn13());
			stmt.setString(2, tag.getTag());
			
			if(stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
			} catch (SQLException e){
				e.printStackTrace();
				return false;
			} finally {
				closeResources();
			}
		
	}
	
	@Override
	public boolean RemoveTag(Tag tag) {
		
		try {
			connection=DAOUtilities.getConnection();
			String sql = "DELETE FROM book_tags WHERE isbn_13=? AND tags=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag.getIsbn13());
			stmt.setString(2, tag.getTag());
			
			
			if(stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
			} catch (SQLException e){
				e.printStackTrace();
				return false;
			} finally {
				closeResources();
			}
	}
	

	private void closeResources() {
		try {
			if(stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
			}
		
		try {
			if(connection != null) 
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close the connection!");
			e.printStackTrace();
		}
	}

}
