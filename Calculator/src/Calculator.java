public class Calculator
{
    private static int RomanNumberCheck = 0;

    public static String Calc(String[] input) throws Exception
    {
        int[] operands = {parseInt(input[0]), parseInt(input[2])};
        if(RomanNumberCheck==1)
        {
            throw new Exception("Недопустимое сочитание операндов!");
        }
        if(RomanNumberCheck==2)
        {
            operands[0] = RomanNumbers.FromRomanToArabic(input[0].toUpperCase());
            operands[1] = RomanNumbers.FromRomanToArabic(input[2].toUpperCase());
        }

        switch (input[1]) {

            case "+" -> {
                if (RomanNumberCheck == 2) {//римские числа
                    if (operands[0] + operands[1] > 3999) {
                        throw new Exception("Превышен возможный(на языке java) лимит римского числа!");
                    }
                    int solution = operands[0] + operands[1];
                    return RomanNumbers.FromArabicToRoman(solution, 1000);
                    //второй пункт - порядок числа, вычисляется индивидуально для оптимизации рекурсии
                }
                return String.valueOf(operands[0] + operands[1]);//арабские числа
            }

            case "-" -> {
                if (RomanNumberCheck == 2) {
                    if (operands[0] - operands[1] > 3999) {
                        throw new Exception("Превышен возможный(на языке java) лимит римского числа!");
                    }
                    if ((operands[0] - operands[1]) <= 0) {
                        throw new Exception("Римские числа не могут быть меньше или равны нулю!");
                    }
                    int solution = operands[0] - operands[1];
                    return RomanNumbers.FromArabicToRoman(solution, 1000);
                }
                return String.valueOf(operands[0] - operands[1]);
            }

            case "*" -> {
                if (RomanNumberCheck == 2) {
                    if (operands[0] * operands[1] > 3999) {
                        throw new Exception("Превышен возможный(на языке java) лимит римского числа!");
                    }
                    int solution = operands[0] * operands[1];
                    return RomanNumbers.FromArabicToRoman(solution, 1000);
                }
                return String.valueOf(operands[0] * operands[1]);
            }

            case "/" -> {
                if (RomanNumberCheck == 2) {
                    if (operands[0] / operands[1] > 3999) {
                        throw new Exception("Превышен возможный(на языке java) лимит римского числа!");
                    }
                    int solution = operands[0] / operands[1];
                    return RomanNumbers.FromArabicToRoman(solution, 1000);
                }
                if (operands[1] == 0) {
                    throw new Exception("Деление на ноль запрещенно!");
                }
                return String.valueOf(operands[0] / operands[1]);
            }
        }
        return "Введен недопустимый оператор!";
    }

    public static Integer parseInt(String value) {
        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e)
        {
            RomanNumberCheck++;
            return 0;
        }
    }
}
