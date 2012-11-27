
public class Test {
	public static void main(String[] args) {
		double time = System.currentTimeMillis();
		
		Author author1 = AuthorFactory.getAuthor(18);
		Author author2 = AuthorFactory.getAuthor(402);
		
		System.out.println("Start " + time);
		System.out.println(author1.getJacards(AuthorFactory.getAuthors(1100, 0)));
		time = System.currentTimeMillis() - time;
		System.out.println("Stop " + time);

		System.out.println(author1.toString() + " J: " + author1.getJaccard(author2));
		System.out.println(author2.toString() + " J: " + author2.getJaccard(author1));
		
//		time = System.currentTimeMillis();
//		System.out.println("Start " + time);
//		System.out.println(author1.getJacards());
//		time = System.currentTimeMillis() - time;
//		System.out.println("Stop " + time);
		
		Lecture lecture1 = LectureFactory.getLecture(24);
		Lecture lecture2 = LectureFactory.getLecture(10);
		time = System.currentTimeMillis();
		System.out.println("Start " + time);
		System.out.println(lecture1.getJacards(LectureFactory.getLectures(1000, 0)));
		time = System.currentTimeMillis() - time;
		System.out.println("Stop " + time);

		System.out.println(lecture1.toString() + " J: " + lecture1.getJaccard(lecture2));
		System.out.println(lecture2.toString() + " J: " + lecture2.getJaccard(lecture1));
		
//		time = System.currentTimeMillis();
//		System.out.println("Start " + time);
//		System.out.println(lecture1.getJacards());
//		time = System.currentTimeMillis() - time;
//		System.out.println("Stop " + time);
		
		
		
		
	}
}
