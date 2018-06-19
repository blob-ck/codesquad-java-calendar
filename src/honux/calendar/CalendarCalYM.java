package honux.calendar;
import java.util.ArrayList;
import java.util.Scanner;

public class CalendarCalYM {
	//연 월 입력받고 바로직전월까지의 총 일수를 계산하여 
	//해당월의 배열에 날짜를 반복문으로 입력하고, 
	
	
	
	private final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private ArrayList<Integer> monthDays = new ArrayList<Integer>();
	
	public void calMaxTotalDays(int year, int month) {
		
		boolean yoondal = false;
		//if(year % 4 == 0 && year % 100 != 0 && year % 400 ==0) {
		if(year % 4 == 0 && year % 100 != 0) {
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
		
		/*for (int i = 0; i < monthDays.size(); i++) {
			System.out.println(monthDays.get(i));
		}*/
		String s = " 일\t월\t화\t수\t목\t금\t토\n---------------------------------------------------\n";
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
		System.out.println(s);
	}
	
	public void prompt(String ms0, String ms1) {
		System.out.println(ms0);
		System.out.println(ms1);
		System.out.println("cal> ");
	}
	
	
	public final String ms0 = "종료 : -1 입력";
	public final String ms1 = "연도를 입력하세요. (양수)";
	public final String ms2 = "월을 입력하세요. (1~12)";
	public final String ms3 = "종료합니다.";

	
	
	
	//메인에서 호출되는 메소드
	public void cal() {
		
		
		/* 연을 입력하고
		 * 월을 입력하면 해당월의 달력을 출력한다 - 4의 배수 연도이고 2월을 출력하면 윤달 출력(29일)
		 * 연이나 월에 -1을 입력하면 종료한다
		 * 프롬프트로 출력한다
		*/
		
		//연을 입력하고
		Scanner sc = new Scanner(System.in);
		
		//프롬프트로 출력한다
	
		while(true) {
				
			if(!monthDays.isEmpty()) {
				monthDays.clear();
			}
			
			prompt(ms0, ms1);
			
			int year = sc.nextInt();
			
			if(year == -1) {
				System.out.println(ms3);
				sc.close();
				return;
			}else if(year < 0) {
				System.out.println("양수를 입력하세요");
				cal();
				break;
			}
			
			//월을 입력하면 해당월의 달력을 출력한다
			prompt(ms0, ms2);
			int month = sc.nextInt();
			//연이나 월에 -1을 입력하면 종료한다
			if(month == -1) {
				System.out.println(ms3);
				sc.close();
				return;
			}else if(month < 0 || month >12) {
				System.out.println("1~12 사이의 숫자를 입력하세요");
				cal();
				break;
			}
		
			calMaxTotalDays(year, month);
			
		
		} //while end
		
		//잊지말고 스캐너 닫아준다
		sc.close();
		
	} // cal() end
}
