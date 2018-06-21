package honux.calendar;

import java.util.Scanner;

public class PromptMenu {

	CalendarCmd cmd = new CalendarCmd();
	
	public void menu() {
		
		Scanner scn = new Scanner(System.in);
		boolean isLoop = true;
		while(isLoop) {
			System.out.println(  "+----------------------+\n"
					+"| 1. 일정 등록\n"
					+"| 2. 일정 검색\n"
					+"| 3. 달력 보기\n"
					+"| h. 도움말\n"
					+"| q. 종료\n"
					+"+----------------------+\n"
					+"명령 (1, 2, 3, h, q)\n"
					+"Command > ");
			String command = scn.next();
			
			switch (command) {
			case "1":
				cmd.cmdRegister(scn);
				break;
			case "2":
				cmd.cmdSearch(scn);
				break;
			case "3":
				cmd.cmdCal(scn);
				break;
			case "h":
				cmd.cmdHelp(scn);
				break;
			case "q":
				System.out.println("[종료합니다.]");
				scn.close();
				isLoop = false;
				break;
			}
		}
	}
}
