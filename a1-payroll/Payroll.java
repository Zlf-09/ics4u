public class Payroll {
    private static final int MAX_EMPLOYEES =50;
    private static final double REGULAR_HOURS = 44.0;
    private static final double OVERTIME_MULTIPLIER = 1.5;
    private static final double CPP_RATE = 0.0595;
    private static final double EI_RATE = 0.0166;
    private static final double TAX_BRACKET = 600.0;
    private static final double LOWER_TAX_RATE = 0.15;
    private static final double HIGH_TAX_RATE = 0.20;
    public static double grossPay(double rate, double hoursWorked) {
        if (hoursWorked <= REGULAR_HOURS){
            return rate * hoursWorked;
        }
        double regularPay = REGULAR_HOURS * rate;
        double overtimeHours = hoursWorked - REGULAR_HOURS;
        double overtimePay = overtimeHours * rate * OVERTIME_MULTIPLIER;
        return regularPay + overtimePay;
    }
    public static double incomeTax(double gross) {
        if (gross <= TAX_BRACKET){
            return gross * LOWER_TAX_RATE;
        }
        double lowerTax = TAX_BRACKET * LOWER_TAX_RATE;
        double higherTax = (gross - TAX_BRACKET) * HIGH_TAX_RATE;
        return lowerTax + higherTax;
    }
    public static double deductions (double gross){
        double cpp = gross * CPP_RATE;
        double ei = gross * EI_RATE;
        double tax = incomeTax(gross);
        return cpp + ei + tax;
    }
    public static double netPay(double gross, double totalDeductions){
        return gross - totalDeductions;

    }
    public static int findById (int[] ids, int count, int target){
        for  (int i = 0; i < count; i++){
            if (ids[i] == target) {
                return i;
            }
        }
        return -1;
    }
    public static int highestNetPayIndex(double[] net,int count){
        if (count <= 0 ){
            throw new IllegalArgumentException(
                    "highestNetPayIndex needs count > 0, got" +count);
        }
        int highestIndex = 0;
        for (int i = 1; i < count; i++){
            if (net [i] > net [highestIndex]){
                highestIndex = i;
            }
        }
        return highestIndex;
    }
}

