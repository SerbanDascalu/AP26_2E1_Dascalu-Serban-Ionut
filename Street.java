public class Street
{
    private String name;
    private int length;
    private Intersection first;
    private Intersection second;

    public Street(String name, int length, Intersection first, Intersection second)
    {
        this.name = name;
        this.length = length;
        this.first = first;
        this.second = second;
    }

    public String getName()
    {
        return name;
    }

    public int getLength()
    {
        return length;
    }

    public Intersection getFirst()
    {
        return first;
    }

    public Intersection getSecond()
    {
        return second;
    }

    @Override
    public String toString()
    {
        return name + " (" + first + " - " + second + ", length=" + length + ")";
    }
}