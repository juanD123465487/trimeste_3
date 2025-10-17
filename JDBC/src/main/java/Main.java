import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Clase interna Usuario
    static class Usuario {
        private static long contador = 1;
        private long id;
        private String nombre;
        private Long edad;

        public Usuario(String nombre, Long edad) {
            this.id = contador++;
            this.nombre = nombre;
            this.edad = edad;
        }

        public long getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Long getEdad() {
            return edad;
        }

        public void setEdad(Long edad) {
            this.edad = edad;
        }
    }

    // Clase interna UsuarioRepository
    static class UsuarioRepository {
        private List<Usuario> usuarios = new ArrayList<>();

        public void insertarUsuario(Usuario usuario) {
            usuarios.add(usuario);
        }

        public List<Usuario> listarUsuarios() {
            return usuarios;
        }

        public Usuario buscarPorNombre(String nombre) {
            for (Usuario u : usuarios) {
                if (u.getNombre().equalsIgnoreCase(nombre)) {
                    return u;
                }
            }
            return null;
        }

        public Usuario buscarPorId(long id) {
            for (Usuario u : usuarios) {
                if (u.getId() == id) {
                    return u;
                }
            }
            return null;
        }

        public void eliminarUsuario(long id) {
            usuarios.removeIf(u -> u.getId() == id);
        }
    }

    // Método principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioRepository usuarioRepository = new UsuarioRepository();

        int opcion;
        do {
            System.out.println("\n=== CRUD de Usuarios ===");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Buscar usuario");
            System.out.println("4. Actualizar usuario");
            System.out.println("5. Eliminar usuario");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    // Crear usuario
                    System.out.println("\n=== Registro de Usuario ===");
                    System.out.print("Ingrese nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Ingrese edad: ");
                    Long edad = scanner.nextLong();
                    scanner.nextLine();

                    Usuario usuario = new Usuario(nombre, edad);
                    usuarioRepository.insertarUsuario(usuario);

                    System.out.println("Usuario registrado con éxito.");
                    break;

                case 2:
                    // Listar usuarios
                    System.out.println("\n=== Lista de Usuarios ===");
                    for (Usuario u : usuarioRepository.listarUsuarios()) {
                        System.out.println("Id: " + u.getId() +
                                           " | Nombre: " + u.getNombre() +
                                           " | Edad: " + u.getEdad());
                    }
                    break;

                case 3:
                    // Buscar usuario
                    System.out.print("\nIngrese el nombre del usuario a buscar: ");
                    String nombreBuscar = scanner.nextLine();

                    Usuario encontrado = usuarioRepository.buscarPorNombre(nombreBuscar);

                    if (encontrado != null) {
                        System.out.println("\nUsuario encontrado:");
                        System.out.println("Id: " + encontrado.getId() +
                                           " | Nombre: " + encontrado.getNombre() +
                                           " | Edad: " + encontrado.getEdad());
                    } else {
                        System.out.println("\nNo se encontró un usuario con ese nombre.");
                    }
                    break;

                case 4:
                    // Actualizar usuario
                    System.out.print("\nIngrese el ID del usuario a actualizar: ");
                    long idActualizar = scanner.nextLong();
                    scanner.nextLine();

                    Usuario usuarioActualizar = usuarioRepository.buscarPorId(idActualizar);

                    if (usuarioActualizar != null) {
                        System.out.print("Nuevo nombre (dejar vacío si no cambia): ");
                        String nuevoNombre = scanner.nextLine();

                        System.out.print("Nueva edad (0 si no cambia): ");
                        long nuevaEdad = scanner.nextLong();
                        scanner.nextLine();

                        if (!nuevoNombre.isBlank()) {
                            usuarioActualizar.setNombre(nuevoNombre);
                        }
                        if (nuevaEdad > 0) {
                            usuarioActualizar.setEdad(nuevaEdad);
                        }

                        System.out.println("Usuario actualizado correctamente.");
                    } else {
                        System.out.println("No se encontró un usuario con ese ID.");
                    }
                    break;

                case 5:
                    // Eliminar usuario
                    System.out.print("\nIngrese el ID del usuario a eliminar: ");
                    long idEliminar = scanner.nextLong();
                    scanner.nextLine();

                    Usuario usuarioEliminar = usuarioRepository.buscarPorId(idEliminar);

                    if (usuarioEliminar != null) {
                        usuarioRepository.eliminarUsuario(idEliminar);
                        System.out.println("Usuario eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró un usuario con ese ID.");
                    }
                    break;

            }

        } while (opcion != 0);

        scanner.close();
    }
}
