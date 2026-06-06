package pe.falabella.dto;

import java.math.BigDecimal;

public class ProductoListadoDTO {

    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private Integer idCategoria;
    private String categoria;
    private Integer idProveedor;
    private String proveedor;
    private BigDecimal precio;
    private Integer stockMinimo;

    public ProductoListadoDTO(Integer idProducto, String nombre, String descripcion,
                              Integer idCategoria, String categoria,
                              Integer idProveedor, String proveedor,
                              BigDecimal precio, Integer stockMinimo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.categoria = categoria;
        this.idProveedor = idProveedor;
        this.proveedor = proveedor;
        this.precio = precio;
        this.stockMinimo = stockMinimo;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }
}