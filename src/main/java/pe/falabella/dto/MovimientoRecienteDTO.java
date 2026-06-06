package pe.falabella.dto;

import java.time.LocalDateTime;

public class MovimientoRecienteDTO {

    private String producto;
    private String tipoMovimiento;
    private Integer cantidad;
    private LocalDateTime fecha;

    public MovimientoRecienteDTO(String producto, String tipoMovimiento, Integer cantidad, LocalDateTime fecha) {
        this.producto = producto;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public String getProducto() {
        return producto;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}