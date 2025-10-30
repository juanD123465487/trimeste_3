
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Conexion;
import model.Bicicleta;

public class BicicletaRepository {
    
    public void insertarBicicleta(Bicicleta bicicleta) {
        String sql = "INSERT INTO BICICLETA (MARCA, MODELO, TIPO, PRECIO_HORA, DISPONIBLE) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Conexion.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, bicicleta.getMarca());
            preparedStatement.setString(2, bicicleta.getModelo());
            preparedStatement.setString(3, bicicleta.getTipo());
            preparedStatement.setDouble(4, bicicleta.getPrecioHora());
            preparedStatement.setBoolean(5, bicicleta.getDisponible());

            preparedStatement.executeUpdate();

            System.out.println("Bicicleta insertada correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Bicicleta> listarBicicletas() {
        List<Bicicleta> bicicletas = new ArrayList<>();
        String sql = "SELECT * FROM BICICLETA";

        try (Connection connection = Conexion.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                bicicletas.add(new Bicicleta(
                        resultSet.getLong("id"),
                        resultSet.getString("marca"),
                        resultSet.getString("modelo"),
                        resultSet.getString("tipo"),
                        resultSet.getDouble("precio_hora"),
                        resultSet.getBoolean("disponible")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bicicletas;
    }

    public Bicicleta buscarPorId(long id) {
        String sql = "SELECT * FROM BICICLETA WHERE ID = ?";
        
        try (Connection connection = Conexion.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Bicicleta(
                    resultSet.getLong("id"),
                    resultSet.getString("marca"),
                    resultSet.getString("modelo"),
                    resultSet.getString("tipo"),
                    resultSet.getDouble("precio_hora"),
                    resultSet.getBoolean("disponible"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizarBicicleta(Bicicleta bicicleta) {
        String sql = "UPDATE BICICLETA SET MARCA = ?, MODELO = ?, TIPO = ?, PRECIO_HORA = ?, DISPONIBLE = ? WHERE ID = ?";

        try (Connection connection = Conexion.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, bicicleta.getMarca());
            preparedStatement.setString(2, bicicleta.getModelo());
            preparedStatement.setString(3, bicicleta.getTipo());
            preparedStatement.setDouble(4, bicicleta.getPrecioHora());
            preparedStatement.setBoolean(5, bicicleta.getDisponible());
            preparedStatement.setLong(6, bicicleta.getId());

            preparedStatement.executeUpdate();

            System.out.println("Bicicleta actualizada correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarBicicleta(long id) {
        String sql = "DELETE FROM BICICLETA WHERE ID = ?";

        try (Connection connection = Conexion.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Bicicleta eliminada correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Bicicleta> buscarBicicletasDisponibles() {
        List<Bicicleta> bicicletas = new ArrayList<>();
        String sql = "SELECT * FROM BICICLETA WHERE DISPONIBLE = true";

        try (Connection connection = Conexion.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                bicicletas.add(new Bicicleta(
                        resultSet.getLong("id"),
                        resultSet.getString("marca"),
                        resultSet.getString("modelo"),
                        resultSet.getString("tipo"),
                        resultSet.getDouble("precio_hora"),
                        resultSet.getBoolean("disponible")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bicicletas;
    }
}