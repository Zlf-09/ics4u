/*
 * ICS4U Session 4 Homework: Rectangle Class
 * Student Name: Oscar Zhu
 * Date: September 29, 2026
 * Represents a rectangle with dimensions, calculations, scaling, and creation tracking
 */
public class Rectangle {
    private double width;
    private double height;
    private static int created = 0;
    private static final double TOLERANCE = 1e-9;

    /**
     * Creates a rectangle with the given width and height
     * Precondition: width and height must be greater than 0
     *
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     * @throws IllegalArgumentException if width or height is less than or equal to 0
     */
    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("width and height must be > 0, got " + width + " x " + height);
        }
        this.width = width;
        this.height = height;
        created++;
    }

    /**
     * Creates a square with the given side length.
     * Precondition: side must be greater than 0
     *
     * @param side the side length of the square
     * @throws IllegalArgumentException if side is less than or equal to 0
     */
    public Rectangle(double side) {
        this(side, side);
    }

    /**
     * Returns the width of this rectangle
     * Precondition: none
     *
     * @return the width of this rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of this rectangle
     * Precondition: none
     *
     * @return the height of this rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Calculates the area using the given width and height.
     * Precondition: none
     *
     * @param width  the width
     * @param height the height
     * @return the calculated area
     */
    public static double area(double width, double height) {
        return width * height;
    }

    /**
     * Returns the area of this rectangle
     * Precondition: none
     *
     * @return the area of this rectangle
     */
    public double area() {
        return area(width, height);
    }

    /**
     * Returns the perimeter of this rectangle
     * Precondition: none
     *
     * @return the perimeter of this rectangle
     */
    public double perimeter() {
        return (width + height) * 2;
    }

    /**
     * Checks whether this rectangle is a square
     * Precondition: none
     *
     * @return true if the width and height differ by less than TOLERANCE
     */
    public boolean isSquare() {
        return Math.abs(width - height) < TOLERANCE;
    }

    /**
     * Returns a new rectangle scaled by the given factor
     * Precondition: factor must be greater than 0
     *
     * @param factor the scale factor
     * @return a new scaled rectangle
     * @throws IllegalArgumentException if factor is less than or equal to 0
     */
    public Rectangle scale(double factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("factor must be > 0, got " + factor);
        }
        return new Rectangle(width * factor, height * factor);
    }

    /**
     * Returns the number of rectangles created successfully
     * Precondition: none
     *
     * @return the number of rectangles created
     */
    public static int getCreated() {
        return created;
    }

    /**
     * Returns a formatted string representation of this rectangle.
     * Precondition: none
     *
     * @return the rectangle dimensions as a string
     */
    @Override
    public String toString() {
        return String.format("Rectangle[%.2f x %.2f]", width, height);
    }
}