import java.util.Objects;

public final class Airport extends Location
{
    private int numarTerminale;

    public Airport(String nume,String tara,double altitudine,int numarTerminale)
    {
        super(nume,tara,altitudine);
        this.numarTerminale=numarTerminale;
    }

    public int getNumarTerminale()
    {
        return numarTerminale;
    }

    public void setNumarTerminale(int numarTerminale)
    {
        this.numarTerminale=numarTerminale;
    }

    @Override
    public boolean equals(Object o)
    {
        if(!super.equals(o))return false;
        Airport other=(Airport)o;
        return numarTerminale==other.numarTerminale;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(),numarTerminale);
    }

    @Override
    public String toString()
    {
        return super.toString().replace("}","")+",numarTerminale="+numarTerminale+"}";
    }
}