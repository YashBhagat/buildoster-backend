package com.buildoster.service;

import com.buildoster.model.ConfigurationType;

import java.util.List;
import java.util.Optional;

public interface ConfigurationTypeService {
    public ConfigurationType addConfigurationType(ConfigurationType configurationType);
    public List<ConfigurationType> findByConfigurationId(Long id);
    public Optional<ConfigurationType> findById(Long id);
}
