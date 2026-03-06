import java.util.Objects;

public final class GasStation extends Location
{
    private double pretCombustibil;

    public GasStation(String nume,String tara,double altitudine,double pretCombustibil)
    {
        super(nume,tara,altitudine);
        this.pretCombustibil=pretCombustibil;
    }

    public double getPretCombustibil()
    {
        return pretCombustibil;
    }

    public void setPretCombustibil(double pretCombustibil)
    {
        this.pretCombustibil=pretCombustibil;
    }

    @Override
    public boolean equals(Object o)
    {
        if(!super.equals(o))return false;
        GasStation other=(GasStation)o;
        return Double.compare(pretCombustibil,other.pretCombustibil)==0;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(),pretCombustibil);
    }

    @Override
    public String toString()
    {
        return super.toString().replace("}","")+",pretCombustibil="+pretCombustibil+"}";
    }
}