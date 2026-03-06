import java.util.Objects;

public final class City extends Location
{
    private int populatie;

    public City(String nume,String tara,double altitudine,int populatie)
    {
        super(nume,tara,altitudine);
        this.populatie=populatie;
    }

    public int getPopulatie()
    {
        return populatie;
    }

    public void setPopulatie(int populatie)
    {
        this.populatie=populatie;
    }

    @Override
    public boolean equals(Object o)
    {
        if(!super.equals(o))return false;
        City other=(City)o;
        return populatie==other.populatie;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(),populatie);
    }

    @Override
    public String toString()
    {
        return super.toString().replace("}","")+",populatie="+populatie+"}";
    }
}