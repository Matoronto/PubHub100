package examples.pubhub.model;


public class Tag {
	private String isbn13;           //International Standard Book Number, unique
	private String tag;              //Book Tags
    
	public String toString(){return tag  + "   " + isbn13 ; }
	
	//Default Constructor
	public Tag() {
		this.isbn13 = null;
		this.tag = null;
	}
	public String getIsbn13() {
		return isbn13;
	}
	public void setIsbn13(String isbn13) {
			this.isbn13 = isbn13;		
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
