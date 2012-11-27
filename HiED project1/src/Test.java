import java.util.ArrayList;

public class Test {

	private static double testLecturesNeighbours(int count) {
		double time = System.currentTimeMillis();
		ArrayList<Lecture> allLectures = LectureFactory.getLectures(
				Integer.MAX_VALUE, 0);
		for (int i = 0; i < count && i < allLectures.size(); i++) {
			allLectures.get(i).getJacards(
					LectureFactory.getLectures(Integer.MAX_VALUE, 0));
		}
		return System.currentTimeMillis() - time;
	}

	private static double testAuthorNeighbours(int count) {
		double time = System.currentTimeMillis();
		ArrayList<Author> allAuthors = AuthorFactory.getAuthors(
				Integer.MAX_VALUE, 0);
		for (int i = 0; i < count && i < allAuthors.size(); i++) {
			allAuthors.get(i).getJacards(
					AuthorFactory.getAuthors(4500, 0));
			allAuthors.get(i).getJacards(
					AuthorFactory.getAuthors(4500, 4500));
			allAuthors.get(i).getJacards(
					AuthorFactory.getAuthors(4500, 9000));
		}
		return System.currentTimeMillis() - time;
	}

	public static void main(String[] args) {
		try {
			double time = System.currentTimeMillis();

			Author author1 = AuthorFactory.getAuthor(18);
			Author author2 = AuthorFactory.getAuthor(402);

			System.out.println("Start " + time);
			System.out.println(author1.getJacards(AuthorFactory.getAuthors(
					1100, 0)));
			time = System.currentTimeMillis() - time;
			System.out.println("Stop " + time);

			System.out.println(author1.toString() + " J: "
					+ author1.getJaccard(author2));
			System.out.println(author2.toString() + " J: "
					+ author2.getJaccard(author1));

			// time = System.currentTimeMillis();
			// System.out.println("Start " + time);
			// System.out.println(author1.getJacards());
			// time = System.currentTimeMillis() - time;
			// System.out.println("Stop " + time);

			Lecture lecture1 = LectureFactory.getLecture(24);
			Lecture lecture2 = LectureFactory.getLecture(10);
			time = System.currentTimeMillis();
			System.out.println("Start " + time);
			System.out.println(lecture1.getJacards(LectureFactory.getLectures(
					1000, 0)));
			time = System.currentTimeMillis() - time;
			System.out.println("Stop " + time);

			System.out.println(lecture1.toString() + " J: "
					+ lecture1.getJaccard(lecture2));
			System.out.println(lecture2.toString() + " J: "
					+ lecture2.getJaccard(lecture1));

			 System.out.println("9 " + testLecturesNeighbours(9));
			 System.out.println("9 " + testLecturesNeighbours(9));
			 System.out.println("9 " + testLecturesNeighbours(9));
			 System.out.println("9 " + testLecturesNeighbours(9));
			 System.out.println("9 " + testLecturesNeighbours(9));
			//
			// System.out.println("86 " + testLecturesNeighbours(86));
			// System.out.println("86 " + testLecturesNeighbours(86));
			// System.out.println("86 " + testLecturesNeighbours(86));
			// System.out.println("86 " + testLecturesNeighbours(86));
			// System.out.println("86 " + testLecturesNeighbours(86));

			System.out.println("862 " + testLecturesNeighbours(862));
			System.out.println("862 " + testLecturesNeighbours(862));
			System.out.println("862 " + testLecturesNeighbours(862));
			System.out.println("862 " + testLecturesNeighbours(862));
			System.out.println("862 " + testLecturesNeighbours(862));

			System.out.println("8623 " + testLecturesNeighbours(8623));
			System.out.println("8623 " + testLecturesNeighbours(8623));
			System.out.println("8623 " + testLecturesNeighbours(8623));
			System.out.println("8623 " + testLecturesNeighbours(8623));
			System.out.println("8623 " + testLecturesNeighbours(8623));

			// time = System.currentTimeMillis();
			// System.out.println("Start " + time);
			// System.out.println(lecture1.getJacards());
			// time = System.currentTimeMillis() - time;
			// System.out.println("Stop " + time);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
