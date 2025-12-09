package com.feliciano.store.services;

import com.feliciano.store.repositories.AddressRepository;
import com.feliciano.store.resources.domain.Address;
import com.feliciano.store.resources.mappers.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository repository;
    private final AddressMapper addressMapper;

    @Autowired
    public AddressService(AddressRepository repository,
                          AddressMapper addressMapper) {
        this.repository = repository;
        this.addressMapper = addressMapper;
    }

    public void saveAll(List<Address> address) {
        repository.saveAll(address);
    }
}
