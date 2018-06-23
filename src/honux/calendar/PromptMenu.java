package honux.calendar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PromptMenu {

	//CalendarCmd cmd = new CalendarCmd();
	CalendarFile cmd = new CalendarFile();
	
	public void menu() throws IOException {
		
		Scanner scn = new Scanner(System.in);
		boolean isLoop = true;
		System.out.print(  "+----------------------+\r\n"
				+"| 1. 일정 등록\r\n"
				+"| 2. 일정 검색\r\n"
				+"| 3. 달력 보기\r\n"
				+"| h. 도움말\r\n"
				+"| q. 종료\r\n"
				+"+----------------------+\r\n");
				
		while(isLoop) {
			System.out.print("명령 (1, 2, 3, h, q)\r\n"
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
