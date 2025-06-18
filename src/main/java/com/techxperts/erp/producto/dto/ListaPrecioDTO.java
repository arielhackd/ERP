package com.techxperts.erp.producto.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaPrecioDTO {

    private Long id;
    private String nombre;
    private Long empresaId;
    private Boolean activa;

}
