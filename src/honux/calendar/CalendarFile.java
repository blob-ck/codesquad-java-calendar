package honux.calendar;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

//콘솔에 출력하던걸 파일로 저장하여 관리하도록 바꾼 클래스
public class CalendarFile {

	private final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private ArrayList<Integer> monthDays = new ArrayList<Integer>();
	//private HashMap<Date, String> planMap;
	private HashMap<String, String> planMap;
	
	//워크스페이스에 텍스트파일이 생성되는걸 보면 상대경로인듯 (앞에 아무런 경로를 적지 않았을때)
	private final String fileLocate = "plan.txt";
	
	public CalendarFile() {

		planMap = new HashMap<String, String>();
		File f = new File(fileLocate);
		if(!f.exists()) {
			System.err.println("File does not exists.");
			return;
		}
	}
	
	//일정 검색
	public void cmdSearch(Scanner scn) {
		System.out.println("[일정 검색]");
		System.out.println("날짜를 입력해 주세요. (yyyy-MM-dd)");
		System.out.print("DATE > ");
		String strDate = scn.next();
		try {
			@SuppressWarnings("unused")
			Date d = new SimpleDateFormat("yyyy-MM-dd").parse(strDate); // yyyy-MM-dd 포맷에 맞춰 키를 입력했는지 try/catch로 거르려고 그냥 놔뒀다.
			File f = new File(fileLocate);
			Scanner sc;
			try {
				sc = new Scanner(f);
				while(sc.hasNext()) {
					String[] line = sc.nextLine().split(" , ");
					planMap.put(line[0], line[1]);
				}
				boolean exist = false;
				for (String s : planMap.keySet()) {
					
					if(strDate.equals(s)) {
						System.out.println(s + "의 일정은 " + planMap.get(s) +" 입니다.");
						exist = true;
					}
				}
				if(!exist) {
					System.out.println("일정이 존재하지 않습니다.");
				}
				sc.close();
			} catch (FileNotFoundException e) {
				System.out.println("등록된 일정이 없습니다.");
			}
		} catch (ParseException e) {
			System.err.println("\r\n yyyy-MM-dd 양식으로 날짜를 입력하세요");
			cmdSearch(scn);
		}
	} // cmdSerch() end
	
	//일정 등록
	public void cmdRegister(Scanner scn) throws FileNotFoundException {
		System.out.println("[일정 등록]");
		System.out.println("날짜를 입력해 주세요. (yyyy-MM-dd)");
		System.out.print("DATE > ");
		String date = scn.next();
		System.out.println("계획을 입력하세요.");
		System.out.print("PLAN > ");
		String plan = "";
		while(!plan.endsWith(";")) {
			if(plan.equals("")) {
				plan += scn.nextLine();
			}else {
				plan += " " + scn.nextLine();
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
	
	public void registerPlan(String strDate, String plan) throws ParseException, FileNotFoundException {
		
		try {
			FileWriter f = new FileWriter(fileLocate, true); //  true : 파일이 존재할 때 새로 생성하여 덮어씌우지 않고 기존파일에 내용을 덧붙인다.
			f.write(strDate + " , " + plan + "\r\n");
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(strDate + "\r\n일정은 " + plan);
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
		
		String s = "\t\t<< " + year + "년  " + month + "월 >>\r\n";
		s += " 일\t월\t화\t수\t목\t금\t토\r\n"
		    +"---------------------------------------------------\r\n";
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
		System.out.println(s + "\r\n");
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
