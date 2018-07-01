package com.madfooat.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.madfooat.jpa.model.Merchant;

@Repository
public interface MerchantRepo extends JpaRepository<Merchant, Integer> {

}
