package com.simon.cloud.dao;


import com.simon.cloud.model.TxLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author simon
 * @date 2020/10/11 10:39 上午
 */
public interface TxLogDao extends JpaRepository<TxLog, String> {
}
