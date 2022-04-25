package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Tag;
import examples.pubhub.model.Book;


public interface TagDAO {
	
	public List<Tag> getAllTags();
	public List<Tag> getAllBooks();
	public List<Tag> getTagsByBook(String isbn13);
	public List<Book> getBooksByTag(String booktag);
	public Tag getBooksByISBN(String isbn);
	public boolean AddTag(Tag tag);
	public boolean RemoveTag(Tag tag);
}
