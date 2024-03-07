
package com.luno.ferreteria.service;


import com.luno.ferreteria.dto.MercadoPagoDTO;

/**
 *
 * @author Nico Morales
 */
public interface IMpService {
    
    
     String OrderMp(MercadoPagoDTO request);
    
}
