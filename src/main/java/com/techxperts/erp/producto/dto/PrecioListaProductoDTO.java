package com.techxperts.erp.producto.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrecioListaProductoDTO {

    private Long id;
    private Long productoId;
    private Long listaPrecioId;
    private BigDecimal precio;

}
