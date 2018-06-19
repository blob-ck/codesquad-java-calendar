package honux.calendar;
import java.util.ArrayList;
import java.util.Scanner;

public class CalendarCalYM {
	//연 월 입력받고 바로직전월까지의 총 일수를 계산하여 
	//7로 나눈 나머지만큼 출력할 배열의 시작일 앞에 0을 입력하여 달력모양을 만든다
	//반복문으로 출력
	
	private final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private ArrayList<Integer> monthDays = new ArrayList<Integer>();
	
	
	public void out(int year, int month) {
		
		boolean yoondal = false;
		//윤달 판별
		if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
			yoondal = true;
		}
		
		//입력 연도의 바로 직전 연도까지 총 일수를 계산한다
		int nowYearDays = 0;
		for (int i = 0; i < month-1; i++) {
			nowYearDays += MAX_DAYS[i];
		}
		if(yoondal && month > 2) {
			nowYearDays ++;
		}
		//int totalDays = ((year - 1) * 365) + ((97 * year) / 400) + nowYearDays;
		int totalDays = ((year - 1) * 365) + (year / 4) - (year / 100) + (year / 400) + nowYearDays;
		int beginDays = totalDays % 7;
		
		for (int i = 0; i < beginDays + MAX_DAYS[month-1]; i++) {
			if(i < beginDays) {
				monthDays.add(0);
			}else {
				monthDays.add(i+1-beginDays);
			}
		}
		
		// - 4의 배수이고 100의 배수가 아니고 400의 배수인 연도이고 2월을 입력하면 윤달로 출력(29일)
		if(month == 2 && yoondal) {
			monthDays.add(monthDays.size(), 29);
		}
		
		String s = "\t\t<< " + year + "년  " + month + "월 >>\n";
		s += " 일\t월\t화\t수\t목\t금\t토\n---------------------------------------------------\n";
		for (int i = 1; i <= monthDays.size(); i++) {
			if (monthDays.get(i-1) < 10) {
				s += " ";
			}
			if(i <= beginDays) {
				s += " " + "\t";
			}else {
				s += monthDays.get(i-1) + "\t";
			}
			if (i % 7 == 0) {
				s += "\r";
			}
		}
		System.out.println(s + "\n");
	}

	
	
	//메인에서 호출되는 메소드
	public void cal() {
		
		/* 연을 입력하고
		 * 월을 입력하면 해당월의 달력을 출력한다 - 4의 배수 연도이고 2월을 출력하면 윤달 출력(29일)
		 * 연이나 월에 -1을 입력하면 종료한다
		 * 프롬프트로 출력한다
		*/
		
		Scanner sc = new Scanner(System.in);
	
		while(true) {
				
			if(!monthDays.isEmpty()) {
				monthDays.clear();
			}
			
			System.out.println("연도를 입력하세요. (exit : -1 입력)");
			System.out.print("YEAR > ");
			
			int year = sc.nextInt();
			
			if(year == -1) {
				System.out.println("종료합니다.");
				sc.close();
				return;
			}else if(year < 0) {
				System.out.println("양수를 입력하세요");
				cal();
				break;
			}
			
			System.out.println("월을 입력하세요. (exit : -1 입력)");
			System.out.print("MONTH > ");
			int month = sc.nextInt();
			if(month == -1) {
				System.out.println("종료합니다.");
				sc.close();
				return;
			}else if(month < 0 || month >12) {
				System.out.println("1~12 사이의 숫자를 입력하세요");
				cal();
				break;
			}
		
			out(year, month);
			
		
		} //while end
		
		sc.close();
		
	} // cal() end
}
