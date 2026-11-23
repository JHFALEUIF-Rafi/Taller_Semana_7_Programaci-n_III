package modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraPagosTest {

    private CalculadoraPagos calculadora;

    @BeforeEach
    void setUp() {
        calculadora = new CalculadoraPagos();
    }


    @Test
    @DisplayName("Tramo 1: Pago anual <= 50,000 (0% impuesto)")
    void testTramo1SinImpuesto() {
        Avenger spiderMan = new Avenger("AV-01", "Spider-Man", "Patrulla Queens", 2, 4000.0);

        assertEquals(48000.0, spiderMan.getPagoAnual(), 0.01);
        assertEquals(320.0, calculadora.calcularAporteFondo(spiderMan.getPagoMensual()), 0.01);
        assertEquals(0.0, calculadora.calcularImpuestoAnual(spiderMan.getPagoAnual()), 0.01);
        assertEquals(3680.0, calculadora.calcularPagoNetoMensual(spiderMan), 0.01);
    }


    @Test
    @DisplayName("Tramo 2: Pago anual > 50,000 y <= 100,000 (10% sobre exceso)")
    void testTramo2DiezPorCiento() {
        Avenger capitan = new Avenger("AV-02", "Capitán América", "Defensa Torre", 4, 6000.0);

        assertEquals(72000.0, capitan.getPagoAnual(), 0.01);
        assertEquals(480.0, calculadora.calcularAporteFondo(capitan.getPagoMensual()), 0.01);
        assertEquals(2200.0, calculadora.calcularImpuestoAnual(capitan.getPagoAnual()), 0.01);
        assertEquals(5336.67, calculadora.calcularPagoNetoMensual(capitan), 0.01);
    }


    @Test
    @DisplayName("Tramo 3: Pago anual > 100,000 y <= 200,000 (20% sobre exceso)")
    void testTramo3VeintePorCiento() {
        Avenger ironMan = new Avenger("AV-03", "Iron Man", "Seguridad Global", 5, 10000.0);

        assertEquals(120000.0, ironMan.getPagoAnual(), 0.01);
        assertEquals(800.0, calculadora.calcularAporteFondo(ironMan.getPagoMensual()), 0.01);
        assertEquals(4000.0, calculadora.calcularImpuestoAnual(ironMan.getPagoAnual()), 0.01);
        assertEquals(8866.67, calculadora.calcularPagoNetoMensual(ironMan), 0.01);
    }


    @Test
    @DisplayName("Tramo 4: Pago anual > 200,000 (30% sobre exceso)")
    void testTramo4TreintaPorCiento() {
        Avenger thor = new Avenger("AV-04", "Thor", "Protección Asgard", 5, 20000.0);

        assertEquals(240000.0, thor.getPagoAnual(), 0.01);
        assertEquals(1600.0, calculadora.calcularAporteFondo(thor.getPagoMensual()), 0.01);
        assertEquals(12000.0, calculadora.calcularImpuestoAnual(thor.getPagoAnual()), 0.01);
        assertEquals(17400.0, calculadora.calcularPagoNetoMensual(thor), 0.01);
    }


    @Test
    @DisplayName("Validación: Peligrosidad fuera de rango (< 1 o > 5)")
    void testPeligrosidadFueraDeRango() {
        assertThrows(IllegalArgumentException.class, () ->
                new Avenger("AV-05", "Hulk", "Contención", 0, 8000.0));

        assertThrows(IllegalArgumentException.class, () ->
                new Avenger("AV-06", "Doctor Strange", "Multiverso", 6, 9000.0));
    }


    @Test
    @DisplayName("Validación: Pago negativo lanza IllegalArgumentException")
    void testPagoNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Avenger("AV-07", "Black Widow", "Infiltración", 3, -500.0));

        assertThrows(IllegalArgumentException.class, () ->
                calculadora.calcularPagoNetoMensual(-100.0));
    }

    @Test
    @DisplayName("Validación: No permite registrar un Avenger con ID repetido")
    void testIdRepetido() {
        RepositorioAvengers repoPrueba = new RepositorioAvengers() {
            private final List<Avenger> lista = new ArrayList<>();

            @Override
            public boolean registrar(Avenger avenger) {
                if (existe(avenger.getId())) return false;
                return lista.add(avenger);
            }

            @Override
            public Optional<Avenger> buscarPorId(String id) {
                return lista.stream().filter(a -> a.getId().equalsIgnoreCase(id)).findFirst();
            }

            @Override
            public boolean modificar(String id, String nombre, String mision, int peligrosidad, double pagoMensual) {
                return false;
            }

            @Override
            public List<Avenger> listar() {
                return lista;
            }
        };

        Avenger primero = new Avenger("AV-10", "Hawkeye", "Vigilancia", 2, 4500.0);
        Avenger duplicado = new Avenger("av-10", "Ronin", "Otra misión", 4, 5000.0);

        assertTrue(repoPrueba.registrar(primero));
        assertFalse(repoPrueba.registrar(duplicado));
    }
}