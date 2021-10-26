package com.buildoster.serviceImpl;

import com.buildoster.model.Configuration;
import com.buildoster.repository.ConfigurationRepository;
import com.buildoster.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {
    @Autowired
    private ConfigurationRepository configurationRepository;
    @Override
    public Configuration addConfigurationDetail(Configuration configuration) {
        return configurationRepository.save(configuration);
    }
}
