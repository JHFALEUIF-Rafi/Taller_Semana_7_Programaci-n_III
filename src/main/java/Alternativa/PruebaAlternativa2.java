package Alternativa;

import modelo.Avenger;

public class PruebaAlternativa2 {

    public static void main(String[] args) {
        RepositorioHashMap repo = new RepositorioHashMap();

        System.out.println("=== REGISTRAR ===");
        System.out.println("A001: " + repo.registrar(new Avenger("A001", "Iron Man", "Proteger Nueva York", 4, 15000)));
        System.out.println("A002: " + repo.registrar(new Avenger("A002", "Capitán América", "Liderar al equipo", 3, 9000)));
        System.out.println("A003: " + repo.registrar(new Avenger("A003", "Thor", "Defender Asgard", 5, 20000)));
        System.out.println("a001 (repetido): " + repo.registrar(new Avenger("a001", "Copia", "Ninguna", 1, 0)));

        System.out.println("\n=== LISTAR ===");
        repo.listar().forEach(System.out::println);

        System.out.println("\n=== BUSCAR POR ID ===");
        System.out.println("A002 -> " + repo.buscarPorId("A002").map(Avenger::toString).orElse("No encontrado"));
        System.out.println("A999 -> " + repo.buscarPorId("A999").map(Avenger::toString).orElse("No encontrado"));

        System.out.println("\n=== MODIFICAR ===");
        System.out.println("A003: " + repo.modificar("A003", "Thor Odinson", "Vigilar el Bifrost", 5, 22000));
        System.out.println("A999: " + repo.modificar("A999", "Nadie", "Nada", 1, 0));
        try {
            repo.modificar("A001", "Iron Man", "Proteger Nueva York", 9, 15000);
        } catch (IllegalArgumentException e) {
            System.out.println("A001 con peligrosidad 9: " + e.getMessage());
        }

        System.out.println("\n=== LISTAR (después de modificar) ===");
        repo.listar().forEach(System.out::println);
        System.out.println("Total: " + repo.cantidad());
    }
}