CREATE TABLE HOTEL (
    ID NUMBER,
    CONSTRAINT HOTEL_ID_PK PRIMARY KEY (ID),
    HOTEL_NAME NVARCHAR2(100) NOT NULL,
    COUNTRY NVARCHAR2(100) NOT NULL,
    CITY NVARCHAR2(100) NOT NULL,
    STREET NVARCHAR2(100) NOT NULL

    --ManyToOne
);

CREATE SEQUENCE HOTEL_SEQ MINVALUE 1 MAXVALUE 100  START WITH 1 INCREMENT BY 1;
    --private Long id;
    --private String name;
    --private String country;
    --private String city;
    --private String street;
    --private List<Room> rooms; ?? - В таблице ROOM