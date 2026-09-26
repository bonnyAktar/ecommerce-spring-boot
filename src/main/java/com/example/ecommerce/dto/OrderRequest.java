package com.example.ecommerce.dto;

public class OrderRequest {
    private int userid;
    private String status;

    public OrderRequest() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
