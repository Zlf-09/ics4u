/*
 * ICS4U Session 4 Homework: Rectangle Tester
 * Student Name: Oscar Zhu
 * Date: September 30, 2026
 * Tests the Rectangle class and its methods
 */

public class RectangleTester {
    public static void main(String[] args) {
        Rectangle a = new Rectangle(3, 4);
        System.out.println("a: " + a);
        System.out.println(" area " + a.area()
                + ", perimeter " + a.perimeter() + ", square " + a.isSquare());
        Rectangle b = new Rectangle(5);
        System.out.println("b: " + b);
        System.out.println(" area " + b.area()
                + ", perimeter " + b.perimeter() + ", square " + b.isSquare());
        Rectangle c = a.scale(2);
        System.out.println("c = a.scale(2): " + c);
        System.out.println("a is unchanged: " + a);
        Rectangle d = new Rectangle(0.1 + 0.2, 0.3);
        System.out.println("d: " + d);
        System.out.println(" square " + d.isSquare()
                + " (0.1 + 0.2 == 0.3 is " + (0.1 + 0.2 == 0.3) + ")");
        System.out.println("Rectangle.area(2.5, 4) " + "= " + Rectangle.area(2.5, 4));
        try {
            new Rectangle(0, 4);
            System.out.println("FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            new Rectangle(3, -2);
            System.out.println("FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            new Rectangle(-1);
            System.out.println("FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            a.scale(0);
            System.out.println("FAIL: no exception");
        } catch (IllegalArgumentException e){
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println("Rectangles created: " + Rectangle.getCreated());
    }
}
