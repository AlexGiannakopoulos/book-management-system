
-- populate user table
INSERT INTO `book_management_db`.`user` (`firstname`, `lastname`, `username`, `userpassword`, `role`)
VALUES ('Marios', 'Tsiris', 'marios', '$2a$12$TWdtQ4zIqOYp4fkWXDe6Xe.mSJHjEArlZ22rHt1mjBGtFgmfKv/Fi', 'LIBRARIAN');
INSERT INTO `book_management_db`.`user` (`firstname`, `lastname`, `username`, `userpassword`, `role`)
VALUES ('Giannhs', 'Lekakos', 'giannhs', '$2a$12$TWdtQ4zIqOYp4fkWXDe6Xe.mSJHjEArlZ22rHt1mjBGtFgmfKv/Fi', 'LIBRARIAN');
INSERT INTO `book_management_db`.`user` (`firstname`, `lastname`, `username`, `userpassword`, `role`)
VALUES ('Alex', 'Giann', 'admin', '$2a$12$TWdtQ4zIqOYp4fkWXDe6Xe.mSJHjEArlZ22rHt1mjBGtFgmfKv/Fi', 'ADMIN');

-- populate books
INSERT INTO `book_management_db`.`book` (`bookprice`, `bookname`)
VALUES (12, 'Book 1');
INSERT INTO `book_management_db`.`book` (`bookprice`, `bookname`)
VALUES (17, 'Book 2');
INSERT INTO `book_management_db`.`book` (`bookprice`, `bookname`)
VALUES (69, 'Book 3');

-- populate library table
INSERT INTO `book_management_db`.`library` (`libraryaddress`, `librarycity`, `libraryname`, `lat`, `lng`, `username`)
VALUES ('xrysostomou', 'nea smyrnh', 'Aiswpos', 38.1725, 23.725, 'Giannhs');
INSERT INTO `book_management_db`.`library` (`libraryaddress`, `librarycity`, `libraryname`, `lat`, `lng`, `username`)
VALUES ('artakhs', 'nea smyrnh', 'Bibliotopia', 38.1725, 23.725, 'Marios');

-- populate inventory table
INSERT INTO `book_management_db`.`inventory` (`dateofcount`, `bookid`, `libraryid`, `quantity`)
VALUES ('2025-11-01', 1, 1, 33);
INSERT INTO `book_management_db`.`inventory` (`dateofcount`, `bookid`, `libraryid`, `quantity`)
VALUES ('2025-11-02', 2, 1, 33);
INSERT INTO `book_management_db`.`inventory` (`dateofcount`, `bookid`, `libraryid`, `quantity`)
VALUES ('2025-11-04', 3, 1, 33);
INSERT INTO `book_management_db`.`inventory` (`dateofcount`, `bookid`, `libraryid`, `quantity`)
VALUES ('2025-11-05', 2, 1, 33);
INSERT INTO `book_management_db`.`inventory` (`dateofcount`, `bookid`, `libraryid`, `quantity`)
VALUES ('2025-11-06', 1, 1, 33);

-- populate orders
INSERT INTO `book_management_db`.`libraryorder` (`orderdate`, `libraryid`, `orderid`)
VALUES ('2024-11-12', 1, 1);
INSERT INTO `book_management_db`.`libraryorder` (`orderdate`, `libraryid`, `orderid`)
VALUES ('2024-11-18', 2, 2);

INSERT INTO `book_management_db`.`libraryorderitem` (`bookid`, `orderitemid`, `quantity`, `orderid`, `iscompleted`)
VALUES (1, 1, 22, 1, false);
INSERT INTO `book_management_db`.`libraryorderitem` (`bookid`, `orderitemid`, `quantity`, `orderid`, `iscompleted`)
VALUES (2, 2, 22, 1, false);
INSERT INTO `book_management_db`.`libraryorderitem` (`bookid`, `orderitemid`, `quantity`, `orderid`, `iscompleted`)
VALUES (2, 3, 22, 2, false);
