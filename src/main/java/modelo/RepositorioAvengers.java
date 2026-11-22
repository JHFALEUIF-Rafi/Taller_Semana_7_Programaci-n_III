package modelo;

import java.util.List;
import java.util.Optional;

public interface RepositorioAvengers {

    boolean registrar(Avenger avenger);

    Optional<Avenger> buscarPorId(String id);


    boolean modificar(String id, String nombre, String mision, int peligrosidad, double pagoMensual);

    List<Avenger> listar();

    default boolean existe(String id) {
        return buscarPorId(id).isPresent();
    }
}
