package com.buildoster.serviceImpl;

import com.buildoster.model.ConfigurationType;
import com.buildoster.repository.ConfigurationTypeRepository;
import com.buildoster.service.ConfigurationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationTypeServiceImpl implements ConfigurationTypeService {
    @Autowired
    private ConfigurationTypeRepository configurationTypeRepository;
    @Override
    public ConfigurationType addConfigurationType(ConfigurationType configurationType) {
        return configurationTypeRepository.save(configurationType);
    }

    @Override
    public List<ConfigurationType> findByConfigurationId(Long id) {
        return configurationTypeRepository.findByConfigurationId(id);
    }

    @Override
    public Optional<ConfigurationType> findById(Long id) {
        return configurationTypeRepository.findById(id);
    }
}
