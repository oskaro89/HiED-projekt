
public class Test {
	public static void main(String[] args) {
		Author author1 = AuthorFactory.getAuthor(10);
		Author author2 = AuthorFactory.getAuthor(13);

		System.out.println(author1.toString() + " J: " + author1.getJaccard(author2));
		System.out.println(author2.toString() + " J: " + author2.getJaccard(author1));
		
		
	}
}
