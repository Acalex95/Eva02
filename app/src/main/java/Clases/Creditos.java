package Clases;

public class Creditos {

    private int Hipotecario;
    private int Automotriz;

    public Creditos(){

        Hipotecario = 1000000;
        Automotriz = 500000;
    }
    public int getCreditoHipotecario(){
        return Hipotecario;
    }
    public int getCreditoAutomotriz(){
        return Automotriz;
    }
}
