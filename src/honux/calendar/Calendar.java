package honux.calendar;

import java.util.Scanner;

public class Calendar {
	
	private final static int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public int maxDayOfMonth(int inputYear, int inputMonth) {
		
		if(inputYear % 4 == 0 && inputMonth == 2) {
			return 29;
		}else {
			return MAX_DAYS[inputMonth-1];
		}
	}
	
	
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
		
		
		//숫자를 입력받아 해당하는 달의 최대 일수를 출력하는 프로그램
		//배열에 넣어 강의 대로 만들어보기
		//int[] month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		//최대일수는 정해진 상수이므로 static으로 빼서 사용해보기
		//메소드 만들어 사용해보기
		
		Scanner sc = new Scanner(System.in);
		System.out.println("연도를 입력하세요");
		System.out.println("달을 입력하세요.(1~12)");
		int inputYear = sc.nextInt();
		int inputMonth = sc.nextInt();
		Calendar cal = new Calendar();
		/*if(inputYear % 4 == 0 && inputMonth == 2) {
			System.out.println(inputMonth + "월은 29일 까지 있습니다.");
		}else {
			//System.out.println(inputMonth + "월은 " + month[inputMonth-1] + "일 까지 있습니다.");
			System.out.printf("%d월은 %d일 까지 있습니다. \n", inputMonth, month[inputMonth-1] );
		}*/
		System.out.printf("%d년 %d월은 %d일 까지 있습니다. \n", inputYear, inputMonth, cal.maxDayOfMonth(inputYear, inputMonth));
		sc.close();
		
		
	}
}
