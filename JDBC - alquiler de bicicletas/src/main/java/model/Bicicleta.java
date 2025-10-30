package model;

public class Bicicleta {
    private Long id;
    private String marca;
    private String modelo;
    private String tipo;
    private Double precioHora;
    private Boolean disponible;

    /* Constructor para creación de bicicleta */
    public Bicicleta(String marca, String modelo, String tipo, Double precioHora) {
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.precioHora = precioHora;
        this.disponible = true;
    }

    /* Constructor para mostrar bicicletas */
    public Bicicleta(Long id, String marca, String modelo, String tipo, Double precioHora, Boolean disponible) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.precioHora = precioHora;
        this.disponible = disponible;
    }

    public Long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getPrecioHora() {
        return precioHora;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public void setPrecioHora(Double precioHora) {
        this.precioHora = precioHora;
    }
}