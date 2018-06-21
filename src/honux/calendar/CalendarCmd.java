package honux.calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class CalendarCmd {

	private final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private ArrayList<Integer> monthDays = new ArrayList<Integer>();
	private HashMap<Date, String> planMap;
	
	public CalendarCmd() {
		planMap = new HashMap<Date, String>();
	}
	
	//일정 검색
	public void cmdSearch(Scanner scn) {
		System.out.println("[일정 검색]");
		System.out.print("날짜를 입력해 주세요. (yyyy-MM-dd)\n>");
		String strDate = scn.next();
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
			System.out.println(date + "일정은 ");
			System.out.println(planMap.get(date) + "입니다.");
		} catch (ParseException e) {
			System.out.println("yyyy-MM-dd 양식으로 날짜를 입력하세요");
			cmdSearch(scn);
		}
		
		
	} // cmdSerch() end
	
	//일정 등록
	public void cmdRegister(Scanner scn) {
		System.out.println("[일정 등록]");
		System.out.print("날짜를 입력해 주세요. (yyyy-MM-dd)\n>");
		String date = scn.next();
		System.out.print("\n계획을 입력하세요.\n>");
		String plan = "";
		while(!plan.endsWith(";")) {
			if(plan.equals("")) {
				plan += scn.nextLine();
			}else {
				plan += "\n" + scn.nextLine();
			}
		}
		//세미콜론 제거
		plan = plan.substring(0, plan.length()-1);
		try {
			registerPlan(date, plan);
		} catch (ParseException e) {
			System.out.println("yyyy-MM-dd 양식으로 날짜를 입력하세요");
			cmdRegister(scn);
		}
	} // cmdRegister() end
	
	public void registerPlan(String strDate, String plan) throws ParseException {
			Date d = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
			planMap.put(d, plan);
			System.out.println(d + "일정은 " + planMap.get(d));
	}
	
	//도움말
	public void cmdHelp(Scanner scn) {
		System.out.println("[도움말]");
	} // cmdHelp() end
	
	public void out(int year, int month) {
		
		boolean yoondal = false;
		if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
			yoondal = true;
		}
		
		//해당연도 직전월까지의 총 일 수
		int nowYearDays = 0;
		for (int i = 0; i < month-1; i++) {
			nowYearDays += MAX_DAYS[i];
		}
		if(yoondal && month > 2) {
			nowYearDays ++;
		}
		//0연도부터 직전연도 + 해당연도 직전월까지 총 일 수
		int totalDays = ((year - 1) * 365) + (year / 4) - (year / 100) + (year / 400) + nowYearDays;
		//입력월의 시작일 요일을 계산하기 위함
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
	public void cmdCal(Scanner scn) {
		System.out.println("[달력 보기]");
		if(!monthDays.isEmpty()) {
			monthDays.clear();
		}
		System.out.println("연도를 입력하세요.");
		System.out.print("YEAR > ");
		int year = scn.nextInt();
		if(year < 0) {
			System.out.println("양수를 입력하세요");
			cmdCal(scn);
			return;
		}
		System.out.println("월을 입력하세요.");
		System.out.print("MONTH > ");
		int month = scn.nextInt();
		if(month < 0 || month >12) {
			System.out.println("1~12 사이의 숫자를 입력하세요");
			cmdCal(scn);
			return;
		}
		out(year, month);
	} // cmdCal() end
}
