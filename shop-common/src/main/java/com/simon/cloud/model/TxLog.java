package com.simon.cloud.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author simon
 * @date 2020/10/11 10:38 上午
 */
@Data
@Entity(name = "shop_tx_log")
public class TxLog {

    @Id
    private String txId;

    private Date date;
}
