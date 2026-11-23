package modelo;

public class CalculadoraPagos {

    public static final double PORCENTAJE_FONDO = 0.08;


    public double calcularAporteFondo(double pagoMensual) {
        validarPago(pagoMensual);
        return pagoMensual * PORCENTAJE_FONDO;
    }

    public double calcularImpuestoAnual(double pagoAnual) {
        validarPago(pagoAnual);

        if (pagoAnual <= 50_000) {
            return 0.0;
        } else if (pagoAnual <= 100_000) {
            return (pagoAnual - 50_000) * 0.10;
        } else if (pagoAnual <= 200_000) {
            return (pagoAnual - 100_000) * 0.20;
        } else {
            return (pagoAnual - 200_000) * 0.30;
        }
    }

    public double calcularImpuestoMensual(double pagoMensual) {
        validarPago(pagoMensual);
        return calcularImpuestoAnual(pagoMensual * 12) / 12.0;
    }


    public double calcularPagoNetoMensual(double pagoMensual) {
        validarPago(pagoMensual);
        double aporte = calcularAporteFondo(pagoMensual);
        double impuestoMensual = calcularImpuestoMensual(pagoMensual);
        return pagoMensual - aporte - impuestoMensual;
    }

    public double calcularPagoNetoMensual(Avenger avenger) {
        if (avenger == null) {
            throw new IllegalArgumentException("El Avenger no puede ser nulo.");
        }
        return calcularPagoNetoMensual(avenger.getPagoMensual());
    }

    private void validarPago(double monto) {
        if (monto < 0 || Double.isNaN(monto) || Double.isInfinite(monto)) {
            throw new IllegalArgumentException("El monto debe ser un número mayor o igual a 0.");
        }
    }
}