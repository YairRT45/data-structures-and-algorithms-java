package estructurasnolineales.auxiliares;

public class EtiquetaVertice {
    protected double metricaAcumulada;
    protected int indiceProcedencia;
    protected int iteracion;



    public double getMetricaAcumulada() {
        return metricaAcumulada;
    }
    public void setMetricaAcumulada(double metricaAcumulada) {
        this.metricaAcumulada = metricaAcumulada;
    }
    public int getIndiceProcedencia() {
        return indiceProcedencia;
    }
    public void setIndiceProcedencia(int indiceProcedencia) {
        this.indiceProcedencia = indiceProcedencia;
    }
    public int getIteracion() {
        return iteracion;
    }
    public void setIteracion(int iteracion) {
        this.iteracion = iteracion;
    }

    @Override
    public String toString() {
        return "["+ metricaAcumulada + "," + indiceProcedencia + "] " + iteracion;
    }
}
