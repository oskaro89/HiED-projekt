
public class Test {
	public static void main(String[] args) {
		Author author1 = AuthorFactory.getAuthor(18);
		Author author2 = AuthorFactory.getAuthor(18);

		System.out.println(author1.toString() + " J: " + author1.getJaccard(author2));
		System.out.println(author2.toString() + " J: " + author2.getJaccard(author1));
		
		double time = System.currentTimeMillis();
		System.out.println("Start " + time);
		System.out.println(author1.getJacards());
		time = System.currentTimeMillis() - time;
		System.out.println("Stop " + time);
		
		
	}
}
