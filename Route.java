import java.util.List;

public class Route
{
    private List<Intersection> path;
    private int cost;

    public Route(List<Intersection> path, int cost)
    {
        this.path = path;
        this.cost = cost;
    }

    public List<Intersection> getPath()
    {
        return path;
    }

    public int getCost()
    {
        return cost;
    }

    @Override
    public String toString()
    {
        return "cost=" + cost + ", path=" + path;
    }
}