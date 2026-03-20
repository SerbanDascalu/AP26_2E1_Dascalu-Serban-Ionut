public class Resource
{
    private String name;
    private String path;

    public Resource(String name, String path)
    {
        this.name = name;
        this.path = path;
    }

    public String getName()
    {
        return name;
    }

    public String getPath()
    {
        return path;
    }

    @Override
    public String toString()
    {
        return name + " -> " + path;
    }
}