package com.example.infsystem.forms;

import com.example.infsystem.models.OrderPosition;

public class OrderPositionWithComment{

    private OrderPosition orderPosition;

    private String comment;

    public OrderPositionWithComment() {
    }

    public OrderPositionWithComment(OrderPosition orderPosition, String comment) {
        this.orderPosition = orderPosition;
        this.comment = comment;
    }

    public OrderPositionWithComment(OrderPosition orderPosition) {
        this.orderPosition = orderPosition;
    }

    public OrderPosition getOrderPosition() {
        return orderPosition;
    }

    public void setOrderPosition(OrderPosition orderPosition) {
        this.orderPosition = orderPosition;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
