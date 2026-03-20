public class Intersection
{
    private String name;
    private int x;
    private int y;

    public Intersection(String name)
    {
        this.name = name;
        this.x = 0;
        this.y = 0;
    }

    public Intersection(String name, int x, int y)
    {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName()
    {
        return name;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public String toString()
    {
        return name;
    }
}