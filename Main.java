//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        Repository repository = new Repository("MyRepository");

        RepositoryManager manager = new RepositoryManager(repository);


        Resource r1 = new Resource("Document1", "test1.txt");
        Resource r2 = new Resource("Document2", "test2.pdf");

        manager.add(r1);
        manager.add(r2);

        System.out.println("Repository: " + repository.getName());
        manager.display();

        try
        {
            manager.open(r1);
        }
        catch (Exception e)
        {
            System.out.println("Could not open resource: " + e.getMessage());
        }
    }
}