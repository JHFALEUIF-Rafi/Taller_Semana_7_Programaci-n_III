package Alternativa;

import modelo.Avenger;
import modelo.RepositorioAvengers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

/**
 * Alternativa 2: repositorio con HashMap<ID, Avenger>.
 * Buscar, registrar y modificar por ID son O(1) en promedio.
 * La clave se normaliza (trim + minúsculas) porque Avenger compara IDs sin distinguir mayúsculas.
 */
public class RepositorioHashMap implements RepositorioAvengers {

    // LinkedHashMap es un HashMap que además conserva el orden de registro al listar.
    private final HashMap<String, Avenger> avengers;

    public RepositorioHashMap() {
        this.avengers = new LinkedHashMap<>();
    }

    @Override
    public boolean registrar(Avenger avenger) {
        if (avenger == null) {
            return false;
        }
        String clave = normalizar(avenger.getId());
        if (avengers.containsKey(clave)) {
            return false; // ID duplicado
        }
        avengers.put(clave, avenger);
        return true;
    }

    @Override
    public Optional<Avenger> buscarPorId(String id) {
        String clave = normalizar(id);
        if (clave == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(avengers.get(clave));
    }

    @Override
    public boolean modificar(String id, String nombre, String mision, int peligrosidad, double pagoMensual) {
        Avenger avenger = buscarPorId(id).orElse(null);
        if (avenger == null) {
            return false;
        }

        // Valida todos los datos antes de modificar nada (lanza IllegalArgumentException si hay error).
        new Avenger(id, nombre, mision, peligrosidad, pagoMensual);

        avenger.setNombre(nombre);
        avenger.setMision(mision);
        avenger.setPeligrosidad(peligrosidad);
        avenger.setPagoMensual(pagoMensual);
        return true;
    }

    @Override
    public List<Avenger> listar() {
        return Collections.unmodifiableList(new ArrayList<>(avengers.values()));
    }

    public int cantidad() {
        return avengers.size();
    }

    private String normalizar(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }
        return id.trim().toLowerCase();
    }
}