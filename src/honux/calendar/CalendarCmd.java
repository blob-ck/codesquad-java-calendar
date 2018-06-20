package honux.calendar;
import java.util.ArrayList;
import java.util.Scanner;

public class CalendarCmd {

	private final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private ArrayList<Integer> monthDays = new ArrayList<Integer>();
	
	//일정 조회
	public void cmdSearch() {
		System.out.println("[일정 등록]");
	} // cmdSerch() end
	
	
	//일정 등록
	public void cmdRegister() {
		System.out.println("[일정 검색]");
	} // cmdRegister() end
	
	//도움말
	public void cmdHelp() {
		System.out.println("[도움말]");
	} // cmdHelp() end
	
	public void out(int year, int month) {
		
		boolean yoondal = false;
		if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
			yoondal = true;
		}
		int nowYearDays = 0;
		for (int i = 0; i < month-1; i++) {
			nowYearDays += MAX_DAYS[i];
		}
		if(yoondal && month > 2) {
			nowYearDays ++;
		}
		int totalDays = ((year - 1) * 365) + (year / 4) - (year / 100) + (year / 400) + nowYearDays;
		int beginDays = totalDays % 7;
		
		for (int i = 0; i < beginDays + MAX_DAYS[month-1]; i++) {
			if(i < beginDays) {
				monthDays.add(0);
			}else {
				monthDays.add(i+1-beginDays);
			}
		}
		
		if(month == 2 && yoondal) {
			monthDays.add(monthDays.size(), 29);
		}
		
		String s = "\t\t<< " + year + "년  " + month + "월 >>\n";
		s += " 일\t월\t화\t수\t목\t금\t토\n"
		    +"---------------------------------------------------\n";
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
	} // out() end

	// 달력 출력
	public void cmdCal(Scanner sc) {
		System.out.println("[달력 보기]");
		if(!monthDays.isEmpty()) {
			monthDays.clear();
		}
		System.out.println("연도를 입력하세요.");
		System.out.print("YEAR > ");
		int year = sc.nextInt();
		if(year < 0) {
			System.out.println("양수를 입력하세요");
			cmdCal(sc);
			return;
		}
		System.out.println("월을 입력하세요.");
		System.out.print("MONTH > ");
		int month = sc.nextInt();
		if(month < 0 || month >12) {
			System.out.println("1~12 사이의 숫자를 입력하세요");
			cmdCal(sc);
			return;
		}
		out(year, month);
	} // cmdCal() end
}
