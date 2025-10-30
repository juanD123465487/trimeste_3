
import java.util.List;
import java.util.Scanner;

import model.Bicicleta;
import repository.BicicletaRepository;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BicicletaRepository bicicletaRepository = new BicicletaRepository();

        int opcion;
        do {
            System.out.println("\n=== SISTEMA DE ALQUILER DE BICICLETAS ===");
            System.out.println("1. Agregar bicicleta");
            System.out.println("2. mostrar todas las bicicletas");
            System.out.println("3. Buscar bicicleta por ID");
            System.out.println("4. Actualizar bicicleta");
            System.out.println("5. Eliminar bicicleta");
            System.out.println("6. mostrar bicicletas disponibles");
            System.out.println("7. Alquilar bicicleta");
            System.out.println("8. Devolver bicicleta");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    // Agregar bicicleta
                    System.out.println("\n=== AGREGAR BICICLETA ===");
                    System.out.print("Ingrese marca: ");
                    String marca = scanner.nextLine();

                    System.out.print("Ingrese modelo: ");
                    String modelo = scanner.nextLine();

                    System.out.print("Ingrese tipo (MTB/Carrera/Urbana): ");
                    String tipo = scanner.nextLine();

                    System.out.print("Ingrese precio por hora: ");
                    Double precioHora = scanner.nextDouble();
                    scanner.nextLine();

                    Bicicleta nuevaBicicleta = new Bicicleta(marca, modelo, tipo, precioHora);
                    bicicletaRepository.insertarBicicleta(nuevaBicicleta);
                    System.out.println("Bicicleta agregada con éxito.");
                    break;

                case 2:
                    // buscar bicicletas
                    System.out.println("\n=== BUSQUEDA DE BICICLETAS ===");
                    List<Bicicleta> todasBicicletas = bicicletaRepository.listarBicicletas();
                    if (todasBicicletas.isEmpty()) {
                        System.out.println("No hay bicicletas registradas.");
                    } else {
                        for (Bicicleta b : todasBicicletas) {
                            String estado = b.getDisponible() ? "Disponible" : "Alquilada";
                            System.out.println("modelo: " + b.getId() +
                                               " | Marca: " + b.getMarca() +
                                               " | id: " + b.getModelo() +
                                               " | Tipo: " + b.getTipo() +
                                               " | Precio/h: $" + b.getPrecioHora() +
                                               " | Estado: " + estado);
                        }
                    }
                    break;

                case 3:
                    // Buscar bicicleta por ID
                    System.out.print("\nIngrese el ID de la bicicleta a buscar: ");
                    long idBuscar = scanner.nextLong();
                    scanner.nextLine();

                    Bicicleta bicicletaEncontrada = bicicletaRepository.buscarPorId(idBuscar);
                    if (bicicletaEncontrada != null) {
                        String estado = bicicletaEncontrada.getDisponible() ? "Disponible" : "Alquilada";
                        System.out.println("\nBicicleta encontrada:");
                        System.out.println("ID: " + bicicletaEncontrada.getId() +
                                           " | Marca: " + bicicletaEncontrada.getMarca() +
                                           " | Modelo: " + bicicletaEncontrada.getModelo() +
                                           " | Tipo: " + bicicletaEncontrada.getTipo() +
                                           " | Precio/h: $" + bicicletaEncontrada.getPrecioHora() +
                                           " | Estado: " + estado);
                    } else {
                        System.out.println("\nNo se encontró una bicicleta con ese ID.");
                    }
                    break;

                case 4:
                    // Actualizar bicicleta
                    System.out.print("\nIngrese el ID de la bicicleta a actualizar: ");
                    long idActualizar = scanner.nextLong();
                    scanner.nextLine();

                    Bicicleta bicicletaActualizar = bicicletaRepository.buscarPorId(idActualizar);
                    if (bicicletaActualizar != null) {
                        System.out.print("Nueva marca (dejar vacío si no cambia): ");
                        String nuevaMarca = scanner.nextLine();

                        System.out.print("Nuevo modelo (dejar vacío si no cambia): ");
                        String nuevoModelo = scanner.nextLine();

                        System.out.print("Nuevo tipo (dejar vacío si no cambia): ");
                        String nuevoTipo = scanner.nextLine();

                        System.out.print("Nuevo precio por hora (0 si no cambia): ");
                        double nuevoPrecio = scanner.nextDouble();
                        scanner.nextLine();

            
                        Bicicleta bicicletaActualizada = new Bicicleta(
                            idActualizar,
                            nuevaMarca.isBlank() ? bicicletaActualizar.getMarca() : nuevaMarca,
                            nuevoModelo.isBlank() ? bicicletaActualizar.getModelo() : nuevoModelo,
                            nuevoTipo.isBlank() ? bicicletaActualizar.getTipo() : nuevoTipo,
                            nuevoPrecio == 0 ? bicicletaActualizar.getPrecioHora() : nuevoPrecio,
                            bicicletaActualizar.getDisponible()
                        );
                        
                        bicicletaRepository.actualizarBicicleta(bicicletaActualizada);
                        System.out.println("Bicicleta actualizada correctamente.");
                    } else {
                        System.out.println("No se encontró una bicicleta con ese ID.");
                    }
                    break;

                case 5:
                    // Eliminar bicicleta
                    System.out.print("\nIngrese el ID de la bicicleta a eliminar: ");
                    long idEliminar = scanner.nextLong();
                    scanner.nextLine();

                    Bicicleta bicicletaEliminar = bicicletaRepository.buscarPorId(idEliminar);
                    if (bicicletaEliminar != null) {
                        bicicletaRepository.eliminarBicicleta(idEliminar);
                        System.out.println("Bicicleta eliminada correctamente.");
                    } else {
                        System.out.println("No se encontró una bicicleta con ese ID.");
                    }
                    break;

                case 6:
                    // mostrar bicicletas libres
                    System.out.println("\n=== BICICLETAS DISPONIBLES ===");
                    List<Bicicleta> bicicletasDisponibles = bicicletaRepository.buscarBicicletasDisponibles();
                    if (bicicletasDisponibles.isEmpty()) {
                        System.out.println("No hay bicicletas disponibles en este momento.");
                    } else {
                        for (Bicicleta b : bicicletasDisponibles) {
                            System.out.println("ID: " + b.getId() +
                                               " | Marca: " + b.getMarca() +
                                               " | Modelo: " + b.getModelo() +
                                               " | Tipo: " + b.getTipo() +
                                               " | Precio/h: $" + b.getPrecioHora());
                        }
                    }
                    break;

                case 7:
                    // Alquilar bicicleta
                    System.out.print("\nIngrese el ID de la bicicleta a alquilar: ");
                    long idAlquilar = scanner.nextLong();
                    scanner.nextLine();

                    Bicicleta bicicletaAlquilar = bicicletaRepository.buscarPorId(idAlquilar);
                    if (bicicletaAlquilar != null && bicicletaAlquilar.getDisponible()) {
                        bicicletaAlquilar.setDisponible(false);
                        bicicletaRepository.actualizarBicicleta(bicicletaAlquilar);
                        System.out.println("Bicicleta alquilada correctamente. ¡Disfrute del paseo!");
                    } else if (bicicletaAlquilar != null && !bicicletaAlquilar.getDisponible()) {
                        System.out.println("La bicicleta ya está alquilada.");
                    } else {
                        System.out.println("No se encontró una bicicleta con ese ID.");
                    }
                    break;

                case 8:
                    // Devolver bicicleta
                    System.out.print("\nIngrese el ID de la bicicleta a devolver: ");
                    long idDevolver = scanner.nextLong();
                    scanner.nextLine();

                    Bicicleta bicicletaDevolver = bicicletaRepository.buscarPorId(idDevolver);
                    if (bicicletaDevolver != null && !bicicletaDevolver.getDisponible()) {
                        bicicletaDevolver.setDisponible(true);
                        bicicletaRepository.actualizarBicicleta(bicicletaDevolver);
                        System.out.println("Bicicleta devuelta correctamente. ¡Gracias!");
                    } else if (bicicletaDevolver != null && bicicletaDevolver.getDisponible()) {
                        System.out.println("La bicicleta ya está disponible.");
                    } else {
                        System.out.println("No se encontró una bicicleta con ese ID.");
                    }
                    break;
            }

        } while (opcion != 0);

        System.out.println("¡Hasta pronto!");
        scanner.close();
    }
}
