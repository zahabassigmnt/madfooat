package com.madfooat.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.madfooat.jpa.model.Merchant;
import com.madfooat.jpa.model.MerchantFile;

@Repository
public interface MerchantFilesRepo extends JpaRepository<MerchantFile, Integer> {

}
