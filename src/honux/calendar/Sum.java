package honux.calendar;

import java.util.Scanner;

public class Sum {
	
	public void sum() {

		System.out.println("두 수를 입력하세요.");
		Scanner sc = new Scanner(System.in);
		String inputString = sc.nextLine();
		String[] splitedString = inputString.split(" ");
		if (inputString.equals("quit")) {
			System.out.println("입력 종료");
		}else {
			while (splitedString.length != 2) {
				System.out.println("숫자 두 개를 입력해라");
				sum();
			}
			calculation(splitedString);
		}
	}

	public void calculation(String[] splitedString) {

		try {
			int first = Integer.parseInt(splitedString[0]);
			int second = Integer.parseInt(splitedString[1]);
			System.out.println("두 수의 합은 " + (first + second) + " 입니다.");
		} catch (NumberFormatException e) {
			System.out.println("숫자만 입력해라");
			sum();
		}
	}
}
