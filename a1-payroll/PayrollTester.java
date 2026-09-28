public class PayrollTester {
    public static void main(String[] args) {
        double expected1 = 855.0;
        double actual1 = Payroll.grossPay(22.50, 38);
        System.out.println("Test 1 - grossPay under 44 hours");
        System.out.println("Expected: " + expected1);
        System.out.println("Actual: " + actual1);
        System.out.println(actual1 == expected1 ? "Pass" : "Fail");
        System.out.println();

        double expected2 = 869.0;
        double actual2 = Payroll.grossPay(19.75, 44);
        System.out.println("Test 2 - grossPay exactly 44 hours");
        System.out.println("Expected: " + expected2);
        System.out.println("Actual: " + actual2);
        System.out.println(actual2 == expected2 ? "Pass" : "Fail");

        double expected3 = 1193.75;
        double actual3 = Payroll.grossPay(25.00, 46.5);
        System.out.println("Test 3 - grossPay with overtime");
        System.out.println("Expected: " + expected3);
        System.out.println("Actual: " + actual3);
        System.out.println(actual3 == expected3 ? "Pass" : "Fail");
        System.out.println();

        double expected4 = 0.0;
        double actual4 = Payroll.grossPay(22.50, 0);
        System.out.println("Test 4 - grossPay with 0 hours");
        System.out.println("Expected: " + expected4);
        System.out.println("Actual: " + actual4);
        System.out.println(actual4 == expected4 ? "Pass" : "Fail");
        System.out.println();

        double expected5 = 75.0;
        double actual5 = Payroll.incomeTax(500.0);
        System.out.println("Test 5 - incomeTax under $600");
        System.out.println("Expected: " + expected5);
        System.out.println("Actual: " + actual5);
        System.out.println(actual5 == expected5 ? "Pass" : "Fail");

        double expected6 = 90.0;
        double actual6 = Payroll.incomeTax(600.0);
        System.out.println("Test 6 - incomeTax exactly $600");
        System.out.println("Expected: " + expected6);
        System.out.println("Actual: " + actual6);
        System.out.println(actual6 == expected6 ? "Pass" : "Fail");
        System.out.println();

        double expected7 = 170.0;
        double actual7 = Payroll.incomeTax(1000.0);
        System.out.println("Test 7 - incomeTax over $600");
        System.out.println("Expected: " + expected7);
        System.out.println("Actual: " + actual7);
        System.out.println(actual7 == expected7 ? "Pass" : "Fail");
        System.out.println();

        double expected8 = 299.594375;
        double actual8 = Payroll.deductions(1193.75);
        System.out.println("Test 8 - deductions for gross 1193.75");
        System.out.println("Expected: " + expected8);
        System.out.println("Actual: " + actual8);
        System.out.println(actual8 == expected8 ? "Pass" : "Fail");

        double expected9 = 894.155625;
        double actual9 = Payroll.netPay(1193.75, 299.594375);
        System.out.println("Test 9 - netPay");
        System.out.println("Expected: " + expected9);
        System.out.println("Actual: " + actual9);
        System.out.println(Math.abs(actual9 - expected9) < 0.000001 ? "Pass" : "Fail");
        System.out.println();

        int[] testIds = {104, 105, 106, 107, 108, 109, 110, 111};
        int expected10 = 3;
        int actual10 = Payroll.findById(testIds, 8, 107);
        System.out.println("Test 10 - findById existing ID");
        System.out.println("Expected: " + expected10);
        System.out.println("Actual: " + actual10);
        System.out.println(actual10 == expected10 ? "Pass" : "Fail");
        System.out.println();

        int expected11 = -1;
        int actual11 = Payroll.findById(testIds, 8, 999);
        System.out.println("Test 11 - findById missing ID");
        System.out.println("Expected: " + expected11);
        System.out.println("Actual: " + actual11);
        System.out.println(actual11 == expected11 ? "Pass" : "Fail");

        double[] testNet = {500.0, 700.0, 600.0};
        System.out.println("Test 12 - highestNetPayIndex with count 0");
        try {
            Payroll.highestNetPayIndex(testNet, 0);
            System.out.println("Fail - no exception was thrown");
        }catch (IllegalArgumentException e ) {
            System.out.println("Pass - IllegalArgumentException was thrown");
        }
        System.out.println();

        double[] testGross = {500.0, 700.0, 600.0};
        System.out.println("Test 13 - averageGrossPay with count 0");
        try {
            Payroll.averageGrossPay(testGross, 0);
            System.out.println("Fail - no exception was thrown");
        }catch (IllegalArgumentException e ){
            System.out.println("Pass - IllegalArgumentException was thrown");
        }
        System.out.println();
        int [] badIds = new int[50];
        String[] badNames = new String[50];
        double[] badRates = new double[50];
        double[] badHours = new double[50];
        int expected14 = 2;
        int actual14 = Payroll.readEmployees("a1-payroll/employees-bad.txt", badIds, badNames, badHours, badHours);
        System.out.println("Test 14 - readEmployees skips bad lines");
        System.out.println("Expected: " + expected14);
        System.out.println("Actual: " + actual14);
        System.out.println(actual14 == expected14? "Pass" : "Fail");
        System.out.println();

        double[] customNet1 = {500.0, 700.0, 600.0};
        int expected15 = 1;
        int actual15 = Payroll.highestNetPayIndex(customNet1,3);
        System.out.println("Test 15 - highestNetPayIndex normal case");
        System.out.println("Expected: " + expected15);
        System.out.println("Actual: " + actual15);
        System.out.println(actual15 == expected15 ? "Pass" : "Fail");
        System.out.println();

        double[] customNet2 = {700.0, 700.0, 600.0};
        int expected16 = 0;
        int actual16 = Payroll.highestNetPayIndex(customNet2,3);
        System.out.println("Test 16 - highestNetPayIndex tie returns first");
        System.out.println("Expected: " + expected16);
        System.out.println("Actual: " + actual16);
        System.out.println(actual16 == expected16 ? "Pass" : "Fail");
        System.out.println();
    }
}