package com.luno.ferreteria.service.ServiceImp;

import com.luno.ferreteria.dto.MercadoPagoDTO;
import com.luno.ferreteria.service.IMpService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nico Morales
 */

@Service
public class MpService implements IMpService {

    @Override
    public String OrderMp(MercadoPagoDTO data) {

        // Agrega credenciales
        MercadoPagoConfig.setAccessToken("APP_USR-1981036742439068-120723-9e310f8c5c4aafd7a2c666639fd9e6b3-758580456");

        PreferenceItemRequest itemRequest
                = PreferenceItemRequest.builder()
                        .id(data.getId())
                        .title("Corralon Online")
                        .description("Pago de compra")
                        .pictureUrl("http://picture.com/PS5")
                        .categoryId("Herramientas e insumos")
                        .quantity(1)
                        .currencyId("ARS")
                        .unitPrice(data.getPrice())
                        .build();
        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items).build();
        PreferenceClient client = new PreferenceClient();
        try {
            Preference preference = client.create(preferenceRequest);
            return preference.getInitPoint();
        } catch (MPException ex) {
            Logger.getLogger(MpService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MPApiException ex) {
            Logger.getLogger(MpService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error";
    }

}
