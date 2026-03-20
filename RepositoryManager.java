import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class RepositoryManager
{
    private Repository repository;

    public RepositoryManager(Repository repository)
    {
        this.repository = repository;
    }

    public void add(Resource resource)
    {
        repository.addResource(resource);
    }

    public void display()
    {
        for (Resource resource : repository.getResources())
        {
            System.out.println(resource);
        }
    }

    public void open(Resource resource) throws IOException
    {
        Desktop desktop = Desktop.getDesktop();
        File file = new File(resource.getPath());
        desktop.open(file);
    }
}