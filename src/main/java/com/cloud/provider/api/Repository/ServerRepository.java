package com.cloud.provider.api.Repository;

import org.springframework.data.aerospike.repository.AerospikeRepository;

import com.cloud.provider.api.model.Server;

import javax.persistence.LockModeType;

public interface ServerRepository extends AerospikeRepository<Server, Integer> {

	
    	Server findByFreeMemoryGreaterThanEqual(int size);

		
	
	
}
