package com.DSTA.PJ_BE.utils;

public class OrderStatus {
    public static final String PENDING = "PENDING";         // Đang chờ xử lý
    public static final String PROCESSING = "PROCESSING";   // Đang xử lý
    public static final String SHIPPED = "SHIPPED";         // Đã giao hàng
    public static final String DELIVERED = "DELIVERED";     // Đã giao đến khách hàng
    public static final String CANCELED = "CANCELED";       // Đã hủy
    public static final String RETURNED = "RETURNED";       // Đã trả lại
    public static final String COMPLETED = "COMPLETED";     // Hoàn thành

    public static final String checkValidStatus(String status) {

        status = status.trim().toUpperCase();
        switch (status) {
            case PENDING:
            case PROCESSING:
            case SHIPPED:
            case DELIVERED:
            case CANCELED:
            case RETURNED:
            case COMPLETED:
                break;
            default:
                return null;

        }

        return status;
    }
}
