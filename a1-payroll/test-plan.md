# Payroll Test Plan
| #  | Method             | Input               | Expected   | Actual     | Result |
|----|--------------------|---------------------|------------|------------|--------|
| 1  | grossPay           | 22.50, 38 hrs       | 855.0      | 855.0      | Pass   |
| 2  | grossPay           | 19.75, 44 hrs       | 869.0      | 869.0      | Pass   |
| 3  | grossPay           | 25.00, 46.5 hrs     | 1193.75    | 1193.75    | Pass   |
| 4  | grossPay           | 22.50, 0 hrs        | 0.0        | 0.0        | Pass   |
| 5  | incomeTax          | gross = 500         | 75.0       | 75.0       | Pass   |
| 6  | incomeTax          | gross = 600         | 90.0       | 90.0       | Pass   |
| 7  | incomeTax          | gross = 1000        | 170.0      | 170.0      | Pass   |
| 8  | deductions         | gross = 1193.75     | 299.594375 | 299.594375 | Pass   |
| 9  | netPay             | 1193.75, 299.594375 | 894.155625 | 894.155625 | Pass   | 
| 10 | findByld           | target = 107        | index 3    | index 3    | Pass   |
| 11 | findByld           | target = 999        | -1         | -1         | Pass   | 
| 12 | highestNetPayIndex | count = 0           | exception  | exception  | Pass   |
| 13 | averageGrossPay    | count = 0           | exception  | exception  | Pass   |
| 14 | readEmployees      | employees-bad.txt   | count = 2  | count = 2  | Pass   |
| 15 | highestNetPayIndex | {500, 700, 600}     | index 1    | index 1    | Pass   |
| 16 | highestNetPayIndex | {700, 700, 600}     | index 0    | index 0    | Pass   |