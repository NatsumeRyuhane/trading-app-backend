DROP TABLE IF EXISTS "transactions";
DROP TABLE IF EXISTS "items";
DROP TABLE IF EXISTS "users";

CREATE TABLE "users"
(
    "id"         uuid PRIMARY KEY   NOT NULL,
    "username"   varchar(50) UNIQUE NOT NULL,
    "first_name" varchar(255)       NOT NULL,
    "last_name"  varchar(255)       NOT NULL,
    "password"   varchar(200)       NOT NULL,
    "address"    varchar(255)       NOT NULL,
    "enabled"    smallint           NOT NULL CHECK (enabled BETWEEN 0 AND 1) DEFAULT 1,
    "created_at" timestamp          NOT NULL                                 DEFAULT (now())
);

CREATE TABLE "items"
(
    "id"          uuid PRIMARY KEY NOT NULL,
    "user_id"     uuid             NOT NULL,
    "name"        varchar(255)     NOT NULL,
    "price"       double precision NOT NULL,
    "description" varchar(255),
    "media_urls"  varchar(255)[],
    "on_sale"     smallint         NOT NULL CHECK (available BETWEEN 0 AND 1) DEFAULT 1,
    "available"   smallint         NOT NULL CHECK (available BETWEEN 0 AND 1) DEFAULT 1,
    "created_at"  timestamp        NOT NULL                                   DEFAULT (now())
);

CREATE TABLE "transactions"
(
    "id"                     uuid PRIMARY KEY NOT NULL,
    "item_id"                uuid             NOT NULL,
    "buyer_id"               uuid             NOT NULL,
    "seller_id"              uuid             NOT NULL,
    "status"                 smallint         NOT NULL,
    "buyer_to_seller_rating" integer          NOT NULL CHECK (buyer_to_seller_rating BETWEEN 0 AND 5) DEFAULT 0,
    "seller_to_buyer_rating" integer          NOT NULL CHECK (seller_to_buyer_rating BETWEEN 0 AND 5) DEFAULT 0,
    "created_at"             timestamp        NOT NULL                                                DEFAULT (now()),
    "shipped_at"             timestamp,
    "delivered_at"           timestamp,
    "confirmed_at"           timestamp,
    "canceled_at"            timestamp
);

ALTER TABLE "items"
    ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "transactions"
    ADD FOREIGN KEY ("item_id") REFERENCES "items" ("id");

ALTER TABLE "transactions"
    ADD FOREIGN KEY ("buyer_id") REFERENCES "users" ("id");

ALTER TABLE "transactions"
    ADD FOREIGN KEY ("seller_id") REFERENCES "users" ("id");