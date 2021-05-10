import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main{
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        ArrayList<Integer> b = (ArrayList<Integer>) a.stream().map(x -> x*2).collect(Collectors.toList());
        for (Integer integer : b) {
            System.out.println(integer);
        }
    }
}