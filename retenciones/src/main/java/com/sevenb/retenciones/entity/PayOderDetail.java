package com.sevenb.retenciones.entity;

import javax.persistence.*;

@Entity
@Table(name = "pay_order_detail")
public class PayOderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private PayOrder payOder;
    @OneToOne
    private RetentionType retentionType;




}
