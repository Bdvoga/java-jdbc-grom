CREATE TABLE ROOM (
    ROOM_ID NUMBER NOT NULL ENABLE,
    NUMBER_OF_GUESTS NUMBER NOT NULL,
    PRICE NUMBER NOT NULL,
    BREAKFAST_INCLUDED NUMBER CHECK(BREAKFAST_INCLUDED = 0 OR BREAKFAST_INCLUDED = 1),
    PETS_ALLOWED NUMBER CHECK(PETS_ALLOWED = 0 OR PETS_ALLOWED = 1),
    DATE_AVAILABLE_FROM DATE,
    HOTEL_ID NUMBER,
    CONSTRAINT HOTEL_ID_FK FOREIGN KEY (HOTEL_ID) REFERENCES HOTEL(HOTEL_ID)
);