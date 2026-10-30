package verificador;

public class SignoPosicion {
    public String signo;
    public int renglon;
    public int pos;


    public SignoPosicion(String signo, int renglon, int pos) {
        this.signo = signo;
        this.renglon = renglon;
        this.pos = pos;
    }

    public String getSigno() {
        return signo;
    }
    public void setSigno(String signo) {
        this.signo = signo;
    }
    public int getRenglon() {
        return renglon;
    }
    public void setRenglon(int renglon) {
        this.renglon = renglon;
    }
    public int getPos() {
        return pos;
    }
    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return signo + ", " + renglon + ", " + pos;
    }
}
