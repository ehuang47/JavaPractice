package oop_shape;

abstract public class Shape {
    int x, y;
    abstract public void printArea();
    public static void main(String[] args){
        Rectangle rect = new Rectangle();
        Triangle tri = new Triangle();
        Circle cir = new Circle();
        Shape[] shapes = new Shape[]{rect, tri, cir};

        for (Shape shape : shapes) {
            System.out.println(shape.toString());
        }

    }
    @Override
    public String toString() {
        return String.format("about: %s\nx: %s, y: %s", super.toString(),x, y);
    }
}

class Rectangle extends Shape {
    @Override
    public void printArea() {
        System.out.printf("Area=%s", x * y);
    }
}

class Triangle extends Shape {
    @Override
    public void printArea() {
        System.out.printf("Area=%s", 0.5*x*y);
    }
}

class Circle extends Shape {
    @Override
    public void printArea() {
        System.out.printf("Area=%s", (int) Math.PI * Math.pow(x,2));
    }
}