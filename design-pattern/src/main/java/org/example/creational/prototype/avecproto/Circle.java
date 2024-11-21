package org.example.creational.prototype.avecproto;

public class Circle implements ShapePrototype{

    private String color;
    private int radius;

    public Circle(String color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    @Override
    public ShapePrototype clone() {
        return new Circle(color, radius);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "color='" + color + '\'' +
                ", radius=" + radius +
                '}';
    }
}
