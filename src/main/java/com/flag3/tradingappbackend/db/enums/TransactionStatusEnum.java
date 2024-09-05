package com.flag3.tradingappbackend.db.enums;

public enum TransactionStatusEnum {
    IN_PROGRESS,
    SHIPPED,          // The item has been shipped to the buyer
    DELIVERED,        // The item has been delivered to the buyer
    CONFIRMED,        // The buyer has confirmed receipt of the item
    COMPLETED,        // The transaction is completed, and all steps are finalized
    CANCELED,         // The transaction has been canceled by the buyer or seller
    REFUNDED          // The transaction was refunded
}
