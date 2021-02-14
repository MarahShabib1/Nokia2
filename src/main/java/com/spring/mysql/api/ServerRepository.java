package com.spring.mysql.api;

import org.springframework.data.aerospike.repository.AerospikeRepository;

public interface ServerRepository extends AerospikeRepository<Server, Integer> {

}
