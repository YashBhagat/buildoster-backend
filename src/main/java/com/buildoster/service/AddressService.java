package com.buildoster.service;

import com.buildoster.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    public Address addAddress(Address address);
    public Address updateAddress(Address address);
    Optional<Address> getAddress(Long addressId);
    List<Address> getUserAddress(Long userId);
    void deleteAddress(Long addressId);
}
