package utils.matematicas;

public class Monomio {
    protected int coeficiente;
    protected int potX;
    protected int potY;

    public Monomio(int coeficiente, int potX, int potY) {
        this.coeficiente = coeficiente;
        this.potX = potX;
        this.potY = potY;
    }

    public int calcularResultado(int x, int y){
        if (coeficiente == 0) {
            return 0;
        } else {
            return (int) (coeficiente * (Math.pow(x, potX) * (Math.pow(y, potY))));
        }
    }

    @Override
    public String toString() {
        return "" + coeficiente + ", " + potX + ", " + potY;
    }
}
