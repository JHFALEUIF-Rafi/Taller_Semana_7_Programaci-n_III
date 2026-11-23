package modelo;

import java.util.Objects;

public class Avenger {

    public static final int PELIGROSIDAD_MIN = 1;
    public static final int PELIGROSIDAD_MAX = 5;

    private final String id;
    private String nombre;
    private String mision;
    private int peligro;     // 1 a 5
    private double pagoMensual;   // en dólares

    public Avenger(String id, String nombre, String mision, int peligro, double pagoMensual) {
        this.id = validarTexto(id, "ID");
        setNombre(nombre);
        setMision(mision);
        setPeligrosidad(peligro);
        setPagoMensual(pagoMensual);
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMision() {
        return mision;
    }

    public int getPeligrosidad() {
        return peligro;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public double getPagoAnual() {
        return pagoMensual * 12;
    }

    public void setNombre(String nombre) {
        this.nombre = validarTexto(nombre, "Nombre");
    }

    public void setMision(String mision) {
        this.mision = validarTexto(mision, "Misión");
    }

    public void setPeligrosidad(int peligrosidad) {
        if (peligrosidad < PELIGROSIDAD_MIN || peligrosidad > PELIGROSIDAD_MAX) {
            throw new IllegalArgumentException(
                    "La peligrosidad debe estar entre " + PELIGROSIDAD_MIN + " y " + PELIGROSIDAD_MAX + ".");
        }
        this.peligro = peligrosidad;
    }

    public void setPagoMensual(double pagoMensual) {
        if (pagoMensual < 0 || Double.isNaN(pagoMensual) || Double.isInfinite(pagoMensual)) {
            throw new IllegalArgumentException("El pago mensual debe ser un número mayor o igual a 0.");
        }
        this.pagoMensual = pagoMensual;
    }


    private static String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " no puede estar vacío.");
        }
        return valor.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avenger)) return false;
        Avenger otro = (Avenger) o;
        return id.equalsIgnoreCase(otro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | Peligrosidad: %d | Pago mensual: $%,.2f",
                id, nombre, mision, peligro, pagoMensual);
    }
}