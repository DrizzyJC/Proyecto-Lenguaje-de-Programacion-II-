package pe.falabella.dto;

import java.time.LocalDateTime;

public class InventarioDTO {

    private Integer idInventario;
    private String nombreProducto;
    private String nombreAlmacen;
    private String ubicacion;
    private Integer stockActual;
    private Integer stockMinimo;
    private String estado;
    private LocalDateTime fechaActualizacion;

    public InventarioDTO(Integer idInventario, String nombreProducto, String nombreAlmacen,
                         String ubicacion, Integer stockActual, Integer stockMinimo,
                         String estado, LocalDateTime fechaActualizacion) {
        this.idInventario = idInventario;
        this.nombreProducto = nombreProducto;
        this.nombreAlmacen = nombreAlmacen;
        this.ubicacion = ubicacion;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.estado = estado;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getIdInventario() {
        return idInventario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }
}