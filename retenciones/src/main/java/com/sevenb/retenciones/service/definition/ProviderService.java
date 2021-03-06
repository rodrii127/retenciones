package com.sevenb.retenciones.service.definition;

import org.springframework.http.ResponseEntity;
import com.sevenb.retenciones.entity.Provider;

/**
 * Provider service interface
 */
public interface ProviderService {

    public ResponseEntity<?> saveProvider(Provider provider, String bearerToken);

    public ResponseEntity<?> findAll(String bearerToken);

    public ResponseEntity<?> findOneProvider(Long id);

    public ResponseEntity<?> update(Long id, Provider providerNew);

    public ResponseEntity<?> delete(Long id);
}
