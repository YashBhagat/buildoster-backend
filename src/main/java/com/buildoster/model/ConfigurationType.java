package com.buildoster.model;

import javax.persistence.*;

@Entity
@Table(name = "configuration_type")
public class ConfigurationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "configuration_type", nullable = false)
    private String configuration_type;
    @Column(name = "configuration_id", nullable = false)
    private Long configuration_id;

    @ManyToOne(targetEntity = Configuration.class)
    @JoinColumn(name = "configuration_id",nullable = false,updatable = false,insertable = false)
    private Configuration configuration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfiguration_type() {
        return configuration_type;
    }

    public void setConfiguration_type(String configuration_type) {
        this.configuration_type = configuration_type;
    }

    public Long getConfiguration_id() {
        return configuration_id;
    }

    public void setConfiguration_id(Long configuration_id) {
        this.configuration_id = configuration_id;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String toString() {
        return "ConfigurationType{" +
                "id=" + id +
                ", configuration_type='" + configuration_type + '\'' +
                ", configuration_id=" + configuration_id +
                '}';
    }
}
