package com.buildoster.serviceImpl;

import com.buildoster.model.Address;
import com.buildoster.repository.AddressRepository;
import com.buildoster.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Address address) {
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Optional<Address> getAddress(Long addressId) {
        return addressRepository.findById(addressId);
    }

    @Override
    public List<Address> getUserAddress(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    @Override
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
