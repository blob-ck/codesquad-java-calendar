package honux.calendar;

public class Calendar {
	public static void main(String[] args) {
		// System.out.println("Hello, Calendar!");
		// 개행문자 \n, \r
		// 탭의 역할을 하는 \t
		// 이클립스에서 코드 들여쓰기 정렬 단축키 Ctrl + Shift + F
		String s = " 일\t월\t화\t수\t목\t금\t토\n---------------------------------------------------\n";
		for (int i = 1; i <= 28; i++) {
			if (i < 10) {
				s += " ";
			}
			s += i + "\t";
			if (i % 7 == 0) {
				s += "\r";
			}
		}
		System.out.println(s);
	}
}
