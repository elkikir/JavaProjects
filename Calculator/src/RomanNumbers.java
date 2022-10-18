public class RomanNumbers {
    private static int arabicNumberFromRoman;
    private static int previousRomanNumber;

    //флаги для контроля формата римского числа
    private static int correctnessFlagSubtraction;
    private static int correctnessFlagRepeating;

    private static  void formatControl(int number) throws Exception
    {
        if(previousRomanNumber < number)//колличество операций вычитания не должно превышать 1
        {
            if(correctnessFlagSubtraction > 1)throw new Exception("Не верный формат записи римского числа!");
            correctnessFlagSubtraction += 1;
            arabicNumberFromRoman -= previousRomanNumber*2;
        }

        if(previousRomanNumber == number)//колличество повторений одного и того же числа подряд
        {
            if(correctnessFlagRepeating > 3)throw new Exception("Не верный формат записи римского числа!");
            correctnessFlagRepeating++;
        }
        else {correctnessFlagRepeating = 0;}
    }

    public static int FromRomanToArabic(String romanNumberString) throws Exception
    {
        arabicNumberFromRoman = 0;
        previousRomanNumber = 0;
        correctnessFlagSubtraction = 0;
        correctnessFlagRepeating  = 0;
        for(char i : romanNumberString.toCharArray())
        {
            switch (i) {
                case 'I' -> {
                    formatControl(1);
                    arabicNumberFromRoman += 1;
                    previousRomanNumber = 1;
                }
                case 'V' -> {
                    formatControl(5);
                    arabicNumberFromRoman += 5;
                    previousRomanNumber = 5;
                }
                case 'X' -> {
                    formatControl(10);
                    arabicNumberFromRoman += 10;
                    previousRomanNumber = 10;
                }
                case 'L' -> {
                    formatControl(50);
                    arabicNumberFromRoman += 50;
                    previousRomanNumber = 50;
                }
                case 'C' -> {
                    formatControl(100);
                    arabicNumberFromRoman += 100;
                    previousRomanNumber = 100;
                }
                case 'D' -> {
                    formatControl(500);
                    arabicNumberFromRoman += 500;
                    previousRomanNumber = 500;
                }
                case 'M' -> {
                    formatControl(1000);
                    arabicNumberFromRoman += 1000;
                    previousRomanNumber = 1000;
                }
                default -> throw new Exception("Недопустимые операнды!");
            }
        }
        return arabicNumberFromRoman;
    }

    private static final String[] RomanNumArray= {"I", "V", "X", "L", "C", "D", "M"};
    private static int indexOfRomanNumArray = 6;
    public static String FromArabicToRoman(int arabicNumberToRoman, int depth)
    {
        StringBuilder romanNumber = new StringBuilder();
        romanNumber.append(RomanNumArray[indexOfRomanNumArray].repeat(Math.max(0, arabicNumberToRoman / (depth))));
        arabicNumberToRoman %= depth;

        if(depth == 1) return romanNumber.toString();

        if(arabicNumberToRoman == 5*(depth/10))
        {
            romanNumber.append(RomanNumArray[indexOfRomanNumArray-1]);
        }
        else if(arabicNumberToRoman >= 4*(depth/10) && arabicNumberToRoman <= 8*(depth-1))
        {
            if(arabicNumberToRoman>5*(depth/10))
            {
                romanNumber.append(RomanNumArray[indexOfRomanNumArray-1]);
                arabicNumberToRoman %= 5*(depth/10);
            }
            else
            {
                romanNumber.append(RomanNumArray[indexOfRomanNumArray - 2]).append(RomanNumArray[indexOfRomanNumArray - 1]);
                arabicNumberToRoman %= 4*(depth/10);
            }
        }
        else if(arabicNumberToRoman >= 9*(depth/10))
        {
            romanNumber.append(RomanNumArray[indexOfRomanNumArray - 2]).append(RomanNumArray[indexOfRomanNumArray]);
            arabicNumberToRoman %= 9*(depth/10);
        }

        indexOfRomanNumArray -= 2;
        depth/=10;
        return romanNumber.append(FromArabicToRoman(arabicNumberToRoman, depth)).toString();
    }
}
