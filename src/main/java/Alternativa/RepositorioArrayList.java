package Alternativa;

import modelo.Avenger;
import modelo.RepositorioAvengers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RepositorioArrayList implements RepositorioAvengers {

    private final ArrayList<Avenger> avengers;

    public RepositorioArrayList() {
        this.avengers = new ArrayList<>();
    }

    @Override
    public boolean registrar(Avenger avenger) {
        if (avenger == null) {
            return false;
        }
        if (buscarIndice(avenger.getId()) != -1) {
            return false; // ID duplicado
        }
        avengers.add(avenger);
        return true;
    }

    @Override
    public Optional<Avenger> buscarPorId(String id) {
        int indice = buscarIndice(id);
        if (indice == -1) {
            return Optional.empty();
        }
        return Optional.of(avengers.get(indice));
    }

    @Override
    public boolean modificar(String id, String nombre, String mision, int peligrosidad, double pagoMensual) {
        int indice = buscarIndice(id);
        if (indice == -1) {
            return false;
        }

        new Avenger(id, nombre, mision, peligrosidad, pagoMensual);

        Avenger avenger = avengers.get(indice);
        avenger.setNombre(nombre);
        avenger.setMision(mision);
        avenger.setPeligrosidad(peligrosidad);
        avenger.setPagoMensual(pagoMensual);
        return true;
    }

    @Override
    public List<Avenger> listar() {
        return Collections.unmodifiableList(new ArrayList<>(avengers));
    }

    public int cantidad() {
        return avengers.size();
    }

    private int buscarIndice(String id) {
        if (id == null || id.trim().isEmpty()) {
            return -1;
        }
        String idBuscado = id.trim();
        for (int i = 0; i < avengers.size(); i++) {
            if (avengers.get(i).getId().equalsIgnoreCase(idBuscado)) {
                return i;
            }
        }
        return -1;
    }
}
