/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     3/09/2021 12:30:56 a. m.                     */
/*==============================================================*/


DROP TABLE BRANDS;

DROP TABLE PRICES;

DROP TABLE PRICES_LIST;

DROP TABLE PRODUCTS;

DROP TABLE TYPE_PRODUCTS;

DROP SEQUENCE SEQ_PRICES_ID;


CREATE SEQUENCE SEQ_PRICES_ID
INCREMENT 1
START 1;

/*==============================================================*/
/* Table: BRANDS                                                */
/*==============================================================*/
CREATE TABLE BRANDS (
   BRAND_ID             NUMERIC(1)           NOT NULL,
   NAME                 VARCHAR(30)          NOT NULL,
   STATE                NUMERIC(1)           NULL DEFAULT 1
      CONSTRAINT CKC_STATE_BRANDS CHECK (STATE IS NULL OR (STATE BETWEEN 0 AND 1)),
   CONSTRAINT PK_BRANDS PRIMARY KEY (BRAND_ID)
);

/*==============================================================*/
/* Table: PRICES                                                */
/*==============================================================*/
CREATE TABLE PRICES (
   PRICES_ID            SERIAL NOT NULL,
   BRAND_ID             NUMERIC(1)           NOT NULL,
   START_DATE           DATE                 NOT NULL,
   END_DATE             DATE                 NOT NULL,
   PRICE_LIST_ID        NUMERIC(5)           NOT NULL,
   PRODUCT_ID           NUMERIC(5)           NOT NULL,
   PRIORITY             NUMERIC(1)           NOT NULL,
   PRICE                NUMERIC(15,2)        NOT NULL,
   CURR                 VARCHAR(3)           NOT NULL,
   CONSTRAINT PK_PRICES PRIMARY KEY (PRICES_ID)
);

/*==============================================================*/
/* Table: PRICES_LIST                                           */
/*==============================================================*/
CREATE TABLE PRICES_LIST (
   PRICE_LIST_ID        NUMERIC(5)           NOT NULL,
   PRODUCT_ID           NUMERIC(5)           NOT NULL,
   STATE                NUMERIC(1)           NULL DEFAULT 1
      CONSTRAINT CKC_STATE_PRICES_L CHECK (STATE IS NULL OR (STATE BETWEEN 0 AND 1)),
   CONSTRAINT PK_PRICES_LIST PRIMARY KEY (PRICE_LIST_ID)
);

/*==============================================================*/
/* Table: PRODUCTS                                              */
/*==============================================================*/
CREATE TABLE PRODUCTS (
   PRODUCT_ID           NUMERIC(5)           NOT NULL,
   NAME                 VARCHAR(30)          NOT NULL,
   TYPE_ID              VARCHAR(3)           NOT NULL,
   STATE                NUMERIC(1)           NULL DEFAULT 1
      CONSTRAINT CKC_STATE_PRODUCTS CHECK (STATE IS NULL OR (STATE BETWEEN 0 AND 1)),
   CONSTRAINT PK_PRODUCTS PRIMARY KEY (PRODUCT_ID)
);

/*==============================================================*/
/* Table: TYPE_PRODUCTS                                         */
/*==============================================================*/
CREATE TABLE TYPE_PRODUCTS (
   TYPE_ID              VARCHAR(3)           NOT NULL,
   NAME                 VARCHAR(30)          NOT NULL,
   CONSTRAINT PK_TYPE_PRODUCTS PRIMARY KEY (TYPE_ID)
);

ALTER TABLE PRICES
   ADD CONSTRAINT FK_PRICES_TO_BRANDS FOREIGN KEY (BRAND_ID)
      REFERENCES BRANDS (BRAND_ID)
      ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE PRICES
   ADD CONSTRAINT FK_PRICES_TO_PRICES_LIST FOREIGN KEY (PRICE_LIST_ID)
      REFERENCES PRICES_LIST (PRICE_LIST_ID)
      ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE PRICES
   ADD CONSTRAINT FK_PRICES_TO_PRODUCTS FOREIGN KEY (PRODUCT_ID)
      REFERENCES PRODUCTS (PRODUCT_ID)
      ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE PRICES_LIST
   ADD CONSTRAINT FK_PRICES_L_TO_PRODUCTS FOREIGN KEY (PRODUCT_ID)
      REFERENCES PRODUCTS (PRODUCT_ID)
      ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE PRODUCTS
   ADD CONSTRAINT FK_PRODUCTS_TO_TYPE_PRO FOREIGN KEY (TYPE_ID)
      REFERENCES TYPE_PRODUCTS (TYPE_ID)
      ON DELETE RESTRICT ON UPDATE RESTRICT;

