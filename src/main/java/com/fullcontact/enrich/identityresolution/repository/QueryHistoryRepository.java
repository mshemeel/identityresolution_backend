
package com.fullcontact.enrich.identityresolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullcontact.enrich.identityresolution.model.QueryHistory;

@Repository
public interface QueryHistoryRepository extends JpaRepository<QueryHistory, Long> {

}
