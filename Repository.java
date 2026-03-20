import java.util.ArrayList;
import java.util.List;

public class Repository
{
    private String name;
    private List<Resource> resources;

    public Repository(String name)
    {
        this.name = name;
        resources = new ArrayList<>();
    }

    public void addResource(Resource resource)
    {
        resources.add(resource);
    }

    public List<Resource> getResources()
    {
        return resources;
    }

    public String getName()
    {
        return name;
    }
}