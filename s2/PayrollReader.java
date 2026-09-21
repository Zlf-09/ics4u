//Name: Oscar Zhu
//Date: September 21. 2026
//Purpose: Read employee data from a file, store it in parallel arrays. And print a formatted payroll report and summary.
package s2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PayrollReader {

    public static void main(String[] args) {
        final int MAX = 100;

        // Parallel arrays: index i in every array describes the same employee.
        String[] names = new String[MAX];
        int[] ids = new int[MAX];
        double[] rates = new double[MAX];
        double[] hours = new double[MAX];
        int count = 0;

        try {
            FileReader file = new FileReader("employees.txt");
            BufferedReader reader = new BufferedReader(file);
            String line;

            while ((line = reader.readLine()) != null) {
                // Each line looks like:   Amira Khan,1001,22.50,38

                // TODO 1: split the line on commas
                String[] parts = line.split(",");

                // TODO 2: store each piece in the right array at index count
                //         (parseInt for the id, parseDouble for rate and hours)
                names[count] = parts[0];
                ids[count] = Integer.parseInt(parts[1]);
                rates[count] = Double.parseDouble(parts[2]);
                hours[count] = Double.parseDouble(parts[3]);

                // TODO 3: count++
                count++;
            }
            reader.close();
            System.out.println("Read " + count + " employees.");
            // TODO 4: loop from 0 to count (NOT MAX) and print each employee on one line.
            //         Plain println is fine for now; printf formatting is the homework.
            System.out.println("Name             ID   Rate   Hours");
            System.out.println("----------------------------------");

            double totalHours = 0;
            double totalRates = 0;
            for (int i = 0; i < count; i++) {
                System.out.printf("%-15s %5d $%.2f %.1f%n", names[i], ids[i], rates[i], hours[i]);
                totalHours += hours[i];
                totalRates += rates[i];
            }
            double averageRate = totalRates / count;
            System.out.println("Number of employees: " + count);
            System.out.println("Total hours: " + totalHours);
            System.out.printf("Average hourly rate: $%.2f%n", averageRate);
        } catch (IOException e) {
            System.out.println("Could not read employees.txt: " + e.getMessage());
        }
    }
}


