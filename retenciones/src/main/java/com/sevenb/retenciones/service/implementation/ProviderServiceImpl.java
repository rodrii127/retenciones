package com.sevenb.retenciones.service.implementation;

import com.sevenb.retenciones.repository.ProviderRepository;
import com.sevenb.retenciones.entity.Provider;
import com.sevenb.retenciones.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Provider save(Provider provider){
        return providerRepository.save(provider);
    }

    @Override
    public List<Provider> findAll(){
        return (List<Provider>) providerRepository.findAll();
    }

    @Override
    public Provider findById(Long id) {

        return  providerRepository.findById(id).orElse(null);
    }

     public Provider update(Provider provider,Long id) {
         Provider providerCurrent = findById(id);

         providerCurrent.setCompanyName(provider.getCompanyName());
         providerCurrent.setCuit(provider.getCuit());
         providerCurrent.setAddress(provider.getAddress());
         providerCurrent.setPhone(provider.getPhone());
         providerCurrent.setFiscalCondition(provider.getFiscalCondition());

         return providerRepository.save( providerCurrent);

     }

}
