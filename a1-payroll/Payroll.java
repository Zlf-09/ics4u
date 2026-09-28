import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Payroll {
    private static final int MAX_EMPLOYEES = 50;
    private static final double REGULAR_HOURS = 44.0;
    private static final double OVERTIME_MULTIPLIER = 1.5;
    private static final double CPP_RATE = 0.0595;
    private static final double EI_RATE = 0.0166;
    private static final double TAX_BRACKET = 600.0;
    private static final double LOWER_TAX_RATE = 0.15;
    private static final double HIGH_TAX_RATE = 0.20;

    /**
     * Calculates the gross pay for one employee, including overtime.
     * Precondition: rate > 0 and hoursWorked >= 0.
     * Postcondition: returns the gross pay in dollars and changes nothing.
     *
     * @param rate the employee's hourly rate in dollars
     * @param hoursWorked the number of hours worked this week
     * @return the employee's gross pay in dollars
     */
    public static double grossPay(double rate, double hoursWorked) {
        if (hoursWorked <= REGULAR_HOURS) {
            return rate * hoursWorked;
        }
        double regularPay = REGULAR_HOURS * rate;
        double overtimeHours = hoursWorked - REGULAR_HOURS;
        double overtimePay = overtimeHours * rate * OVERTIME_MULTIPLIER;
        return regularPay + overtimePay;
    }

    /**
     * Calculates the income tax for a given gross pay.
     * Precondition: gross >= 0.
     * Postcondition: returns the income tax on the gross pay using the two tax brackets.
     *
     * @param gross the employee's gross pay in dollars
     * @return the income tax in dollars
     */
    public static double incomeTax(double gross) {
        if (gross <= TAX_BRACKET) {
            return gross * LOWER_TAX_RATE;
        }
        double lowerTax = TAX_BRACKET * LOWER_TAX_RATE;
        double higherTax = (gross - TAX_BRACKET) * HIGH_TAX_RATE;
        return lowerTax + higherTax;
    }

    /**
     * Calculates the total deductions for a given gross pay.
     * Precondition: gross>= 0.
     * Postcondition: returns the total of CPP, EI,and income tax.
     *
     * @param gross the employee's gross pay in dollars
     * @return the total deductions in dollars
     */
    public static double deductions(double gross) {
        double cpp = gross * CPP_RATE;
        double ei = gross * EI_RATE;
        double tax = incomeTax(gross);
        return cpp + ei + tax;
    }

    /**
     * Calculates the employee's net pay after deductions.
     * Precondition: gross >= 0, totalDeductions >= 0, and totalDeductions <= gross.
     *
     * @param gross the employee's gross pay in dollars
     * @param totalDeductions the employee's total deductions in dollars
     * @return the employee's net pay in dollars
     */
    public static double netPay(double gross, double totalDeductions) {
        return gross - totalDeductions;

    }

    /**
     * To find the first employee with the specified ID.
     * Precondition: 0<= count && count <= ids.length.
     * Postcondition: returns the index of the first matching ID, or -1 if no match is found.
     * The ids array is unchanged.
     *
     * @param ids the array of employee IDs
     * @param count the number of valid entries in ids
     * @param target the employee ID to find
     * @return the index of the first matching ID, or -1 if not found
     */
    public static int findById(int[] ids, int count, int target) {
        for (int i = 0; i < count; i++) {
            if (ids[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the employee with the highest net pay
     * Precondition: count > 0 and count <= net.length
     * Postcondition: returns an index from 0 to count -1, and the net array is unchanged.
     * @param net the array of employee net pay values
     * @param count the number of valid entries in net
     * @return the index of the employee with the highest net pay
     * @throws IllegalArgumentException, if count <= 0.
     */
    public static int highestNetPayIndex(double[] net, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException(
                    "highestNetPayIndex needs count > 0, got" + count);
        }
        int highestIndex = 0;
        for (int i = 1; i < count; i++) {
            if (net[i] > net[highestIndex]) {
                highestIndex = i;
            }
        }
        return highestIndex;
    }

    /**
     * Calculates the average gross pay of the employees.
     * Precondition: count > 0 and count <= gross.length.
     * Postcondition: returns the average of the first count gross pay values and the gross array is unchanged.
     * @param gross the array of employee gross pay values
     * @param count the number of valid entries in gross.
     * @return the average gross pay in dollars.
     * @throws IllegalArgumentException if count <= 0
     */
    public static double averageGrossPay(double[] gross, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("averageGrossPay needs count > 0, got" + count);
        }
        double sum = 0.0;
        for (int i = 0; i < count; i++) {
            sum += gross[i];
        }
        return sum / count;
    }

    /**
     * prints the employee payroll report.
     * Precondition: count >= 0, and the arrays contain matching employee data for indexes 0 to count -1.
     * Postcondition: prints the report and does not change any array.
     *
     * @param ids the array of employee IDs
     * @param names the array of employee names
     * @param gross the array of employee net pay values
     * @param net the array of employee net pay values
     * @param count the number of valid employee entries
     */

    public static void printReport(int[] ids, String[] names, double[] gross, double[] net, int count) {
        System.out.printf("%-6s %-18s %12s %12s %12s%n", "ID", "NAME", "GROSS", "DEDUCTIONS", "NET");
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < count; i++) {
            System.out.printf("%-6d, %-18s %12.2f %12.2f %12.2f%n", ids[i], names[i], gross[i], deductions(gross[i]), net[i]);
        }
    }

    /**
     * Prints the payroll summary.
     * Precondition: count > 0, and the gross and net arrays contain matching employee data.
     * Postcondition: Prints the summary section and does not change either array.
     *
     * @param gross the array of employee gross pay values
     * @param net the array of employee net pay values
     * @param count the number of valid employee entries
     */

    public static void printSummary(double[] gross, double[] net, int count) {
        System.out.println();
        System.out.println("SUMMARY");
        double totalGross = 0.0;
        double totalNet = 0.0;
        for (int i = 0; i < count; i++) {
            totalGross += gross[i];
            totalNet = +net[i];
        }
        double averageGross = averageGrossPay(gross, count);
        int highestIndex = highestNetPayIndex(net, count);
        double highestNet = net[highestIndex];
        System.out.printf("%-22s %10d %n", "Employees:", count);
        System.out.printf("%-22s %10.2f%n", "Total gross pay:", totalGross);
        System.out.printf("%-22s %10.2f%n", "Total net pay:", totalNet);
        System.out.printf("%-22s %10.2f%n", "Average gross pay:", averageGross);
        System.out.printf("%-22s %10.2f%n", "Highest net pay:", highestNet);
    }

    /**
     * Reads employee records from a file into four parallel arrays.
     * Precondition: The four arrays have the same length and are large enough to hold all valid employee records.
     * Postcondition: Fills indexes 0 to count -1 of each array and returns the number of valid employees read. Invalid lines are not stored or counted.
     * @param fileName the name of the employee data file
     * @param ids the array for employee IDs.
     * @param names the array for employee names
     * @param rates the array for employee hourly rates
     * @param hours the array for employee hours worked
     * @return the number of valid employees successfully read
     */
    public static int readEmployees(String fileName, int[] ids, String[] names, double[] rates, double[] hours) {
        int count = 0;
        int lineNumber = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    System.out.println("Skipping line " + lineNumber + ": blank line");
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    System.out.println("Skipping line " + lineNumber + ": expected 4 fields, found " + parts.length);
                    continue;
                }
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double rate = Double.parseDouble(parts[2].trim());
                    double hoursWorked = Double.parseDouble(parts[3].trim());
                    if (rate <= 0 || hoursWorked < 0) {
                        System.out.println("Skipping line " + lineNumber + ": rate must be > 0 and hours must be >= 0");
                        continue;
                    }
                    ids[count] = id;
                    names[count] = name;
                    rates[count] = rate;
                    hours[count] = hoursWorked;
                    count++;
                } catch (NumberFormatException e) {
                    System.out.println("Skipping line " + lineNumber + ": could not read a number");
                    continue;
                }
            }
        } catch (IOException e) {
            return 0;
        }
        return count;
    }
}