import java.util.Objects;

public class Ship {
    private String name;
    private double weight;

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public Ship(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "java.Ship{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return Double.compare(ship.weight, weight) == 0 && Objects.equals(name, ship.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }
}