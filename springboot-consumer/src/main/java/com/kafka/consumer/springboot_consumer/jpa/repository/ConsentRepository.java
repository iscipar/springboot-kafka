package com.kafka.consumer.springboot_consumer.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kafka.consumer.springboot_consumer.jpa.entity.ConsentEntity;
import com.kafka.consumer.springboot_consumer.jpa.entity.ConsentEntityPK;

@Repository
public interface ConsentRepository extends JpaRepository<ConsentEntity, ConsentEntityPK> {

}
