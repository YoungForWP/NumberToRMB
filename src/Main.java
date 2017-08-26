import static java.lang.String.valueOf;

public class Main {
    private static String[] numberFormat = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
    private static String[] unit = {"分","角","十","百","千","万"};

    public static void main(String[] args) {
        System.out.println(convertToRMB(666601.018));
    }

    private static String convertToRMB(Double number) {
        String stringNumber = valueOf(number);
        int index = stringNumber.indexOf(".");
        String rightOutPut = "";
        if (index > -1) {
            rightOutPut = dealWithDotRight(stringNumber, index);
            stringNumber = getSubstring(stringNumber, 0, index);
        }
        StringBuilder leftOutPut = dealWithDotLeft(stringNumber);
        return leftOutPut + rightOutPut;
    }

    private static StringBuilder dealWithDotLeft(String stringNumber) {
        StringBuilder leftOutPut = new StringBuilder();
        char[] numbers = stringNumber.toCharArray();
        int unitIndex = unit.length - 1;
        for (int i = 0; i < numbers.length - 1; i ++) {
            if (i < stringNumber.length() - 5) {
                leftOutPut.append(findNumberFormat(valueOf(numbers[i])));
            } else {
                leftOutPut.append(getHan(valueOf(numbers[i]), unitIndex));
                unitIndex --;
            }
        }
        return leftOutPut;
    }

    private static String dealWithDotRight(String stringNumber, int index) {
        String rightOutPut;
        String right = stringNumber.substring(index + 1);
        String secondDotNumber = "";
        String firstNumber = getSubstring(right, 0, 1);
        String firstDotNumber = getHan(firstNumber, 1);
        if (right.length() >= 2) {
            secondDotNumber = getHan(getSubstring(right, 1, 2), 0);
        }
        rightOutPut = String.format("%s%s",firstDotNumber, secondDotNumber);
        return rightOutPut;
    }

    private static String getHan(String firstNumber, int i) {
        if (!"0".equals(firstNumber)) {
            return findNumberFormat(firstNumber) + unit[i];
        }
         return "";
    }

    private static String getSubstring(String text, int beginIndex, int endIndex) {
        return text.substring(beginIndex, endIndex);
    }

    private static String findNumberFormat(String number) {
        String numbers = "0123456789";
        return numberFormat[numbers.indexOf(number)];
    }
}
