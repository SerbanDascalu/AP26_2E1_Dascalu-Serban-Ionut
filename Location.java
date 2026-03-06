import java.util.Objects;

public abstract sealed class Location permits City,Airport,GasStation
{
    private String nume;
    private String tara;
    private double altitudine;

    protected Location(String nume,String tara,double altitudine)
    {
        this.nume=nume;
        this.tara=tara;
        this.altitudine=altitudine;
    }

    public String getNume()
    {
        return nume;
    }

    public void setNume(String nume)
    {
        this.nume=nume;
    }

    public String getTara()
    {
        return tara;
    }

    public void setTara(String tara)
    {
        this.tara=tara;
    }

    public double getAltitudine()
    {
        return altitudine;
    }

    public void setAltitudine(double altitudine)
    {
        this.altitudine=altitudine;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this==o)return true;
        if(o==null)return false;
        if(getClass()!=o.getClass())return false;

        Location other=(Location)o;

        return Double.compare(altitudine,other.altitudine)==0
                &&Objects.equals(nume,other.nume)
                &&Objects.equals(tara,other.tara);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getClass(),nume,tara,altitudine);
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName()+"{nume='"+nume+"',tara='"+tara+"',altitudine="+altitudine+"m}";
    }
}