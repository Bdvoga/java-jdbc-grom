CREATE TABLE STORAGES (
    ID NUMBER,
    CONSTRAINT STORAGE_ID_PK PRIMARY KEY (ID),
    FORMAT_SUPPORTED NVARCHAR2(50) NOT NULL,
    COUNTRY NVARCHAR2(50) NOT NULL,
    MAX_SIZE NUMBER NOT NULL
);