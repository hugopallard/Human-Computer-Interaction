package org.example.model;

public class Route {
    private String grade;
    private String color;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ClimbingRoute{" +
                ", grade='" + grade + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
