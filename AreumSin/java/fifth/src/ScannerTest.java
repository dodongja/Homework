import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        // Scanner는 class 타입의 데이터 타입.
        // 아직 클래스를 모르므로 입력을 수행하기 우해 존자해는 데이터 타입이라고 생각.
        // new Scanner(System.in)은 시스템으로 입력 들어가는 정보를 해석하겠다 정도로 받아들이면 된다.

        Scanner scanner = new Scanner(System.in);

        System.out.print("정수를 입력하세요 : ");
        int num1 = scanner.nextInt();
        // scanner.nextInt()를 통해서 정수를 입력 받을 수 있게 도와준다.
        // 다른 데이터 타입도 다 있다.

        System.out.println("입력하신 정수는 "+num1+"입니다.");
    }
}
