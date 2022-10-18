import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        String[] symbolArray = (new Scanner(System.in)).nextLine().trim().split(" ");
        if(symbolArray.length != 3)throw new Exception("Ошибка: Неправильный ввод параметров!");
        System.out.println(Calculator.Calc(symbolArray));
    }
}