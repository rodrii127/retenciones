package com.sevenb.retenciones.service;

import com.sevenb.retenciones.dao.ProviderDao;
import com.sevenb.retenciones.entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderDao providerDao;

    @Override
    public Provider save(Provider provider){
        return providerDao.save(provider);
    }

    @Override
    public List<Provider> findAll(){
        return (List<Provider>) providerDao.findAll();
    }

    @Override
    public Provider findById(Long id) {

        return  providerDao.findById(id).orElse(null);
    }

     public Provider update(Provider provider,Long id) {
         Provider providerCurrent = findById(id);

         providerCurrent.setCompanyName(provider.getCompanyName());
         providerCurrent.setCuit(provider.getCuit());
         providerCurrent.setAddress(provider.getAddress());
         providerCurrent.setPhone(provider.getPhone());
         providerCurrent.setFiscalCondition(provider.getFiscalCondition());

         return providerDao.save( providerCurrent);

     }

}
