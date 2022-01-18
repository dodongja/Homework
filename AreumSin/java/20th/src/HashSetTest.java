import java.util.HashSet;
import java.util.Set;

public class HashSetTest {
    public static void main(String[] args) {
        // 집합의 특성?
        // 집합은 중복을 허용하지않는다.
        Set<String> s = new HashSet<>();
        // HashSet...?이 몰까?
        // Set이 집합
        // 속도가 빠르다는 장점이 있다.

        String[] duplicate = {"중복", "노중복", "중복", "예스 중복"};

        for(String dup: duplicate){
            // add() 동작시 중복이 발생하면 false를 리턴한다.
            // 그러므로 true가 되서 어떤 녀석이 중복이 되었는지판정할 수 있게하는 부분이다.

            if(!s.add(dup)){
                System.out.println("중복된 단어: "+ dup);
            }else
                System.out.println("증복이 아닌 단어: "+ dup);
        }

        System.out.println("중복되지 않은 단어: "+ s);
    }
}
