package com.sevenb.retenciones.service;


import com.sevenb.retenciones.entity.Provider;

import java.util.List;

public interface ProviderService {

     Provider save(Provider provider);
     List<Provider> findAll();
     Provider findById(Long id);
     Provider update(Provider provider,Long id);

}
