package honux.calendar;

import java.util.Scanner;

public class PromptMenu {

	CalendarCmd cmd = new CalendarCmd();
	
	public void menu() {
		System.out.println(  "+----------------------+\n"
				+"| 1. 일정 등록\n"
				+"| 2. 일정 검색\n"
				+"| 3. 달력 보기\n"
				+"| h. 도움말\n"
				+"| q. 종료\n"
				+"+----------------------+\n"
				+"명령 (1, 2, 3, h, q)\n"
				+"Command > ");
		
		Scanner scn = new Scanner(System.in);
		while(true) {
			String command = scn.next();
			if(command.equals("1")) {
				cmd.cmdSearch();
			}
			else if(command.equals("2")) {
				cmd.cmdRegister();
			}		
			else if(command.equals("3")) {
				cmd.cmdCal(scn);
			}
			else if(command.equals("h")) {
				cmd.cmdHelp();
			}	
			else if(command.equals("q")) {
				System.out.println("[종료합니다.]");
				scn.close();
				break;
			}
		}
	}
}
