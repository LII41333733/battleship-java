abstract class Shape {

    abstract double getPerimeter();
    abstract double getArea();
}

class Triangle extends Shape {
    double a;
    double b;
    double c;

    public Triangle (double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    protected double getPerimeter() {
        return a + b + c;
    }
    protected double getArea() {
        double s = this.getPerimeter() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}

class Circle extends Shape {
    double r;

    public Circle (double r) {
        this.r = r;
    }

    protected double getPerimeter() {
        return 2 * Math.PI * r;
    }

    protected double getArea() {
        return Math.PI * Math.pow(r, 2);
    }
}
class Rectangle extends Shape {
    double a;
    double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    protected double getPerimeter() {
        return (a * 2) + (b * 2);
    }
    protected double getArea() {
        return a * b;
    }
}