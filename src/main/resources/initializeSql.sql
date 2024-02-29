USE collect_cards;

DROP TABLE IF EXISTS assistEvents;
DROP TABLE IF EXISTS SharingEvents;
DROP TABLE IF EXISTS ScratchingEvents;
DROP TABLE IF EXISTS Involvements;
DROP TABLE IF EXISTS Activities;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS UserTypes;

CREATE TABLE UserTypes(
                          userTypeId TINYINT primary key ,
                          userTypeName VARCHAR(20) UNIQUE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO UserTypes
(userTypeId, userTypeName)
VALUES
    (0, 'normal'),
    (1, 'administrator');



CREATE TABLE Users(
    userId VARCHAR(33) PRIMARY KEY ,
    telephone VARCHAR(20) NOT NULL,
    nickName VARCHAR (20) NOT NUll,
    sex TINYINT NOT NULL ,
    userTypeId TINYINT NOT NULL ,
    createTime DATETIME NOT NULL ,
    userName VARCHAR(20) UNIQUE,
    password VARCHAR(100) NOT NULL,
    FOREIGN KEY (userTypeId) REFERENCES UserTypes(userTypeId)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO Users
    (userId, telephone, nickName, sex, userTypeId, createTime, userName, password)
VALUES
#     ('3ce9c94840034f41a346cfef5295da35', '18851812072', 'java', 2, 1, '2022-07-13 16:14:34', 'java', '123456'),
    ('4b3f4ce79f2c407b88ee40a5f64acb98', '18851812072', 'admin', 1, 1, '2022-07-13 16:14:35', 'admin', 'Admin123');




CREATE TABLE Activities(
    activityId VARCHAR(33) PRIMARY KEY,
    activityTitle VARCHAR(100) UNIQUE ,
    postPic VARCHAR(100) NOT NULL ,
    startTime DATETIME NOT NULL ,
    createTime DATETIME NOT NULL ,
    endTime DATETIME NOT NULL ,
    rules LONGTEXT NOT NULL ,
#     （活动状态：1-已上架，0-未上架）
    state TINYINT NOT NULL ,
    cardName1 VARCHAR(20) NOT NULL ,
    cardName2 VARCHAR(20) NOT NULL ,
    cardName3 VARCHAR(20) NOT NULL ,
    cardName4 VARCHAR(20) NOT NULL ,
    cardName5 VARCHAR(20) NOT NULL ,
    cardPic1 VARCHAR(100) NOT NULL ,
    cardPic2 VARCHAR(100) NOT NULL ,
    cardPic3 VARCHAR(100) NOT NULL ,
    cardPic4 VARCHAR(100) NOT NULL ,
    cardPic5 VARCHAR(100) NOT NULL ,
    chance1 INT NOT NULL ,
    chance2 INT NOT NULL ,
    chance3 INT NOT NULL ,
    chance4 INT NOT NULL ,
    chance5 INT NOT NULL

) ENGINE=INNODB DEFAULT CHARSET=utf8;

# INSERT INTO Activities
#     (activityId, activityTitle, postPic, startTime,
#      createTime, endTime, rules, state,
#      cardName1, cardName2, cardName3, cardName4, cardName5,
#      cardPic1, cardPic2, cardPic3, cardPic4, cardPic5,
#      chance1, chance2, chance3, chance4, chance5)
# VALUES
#     ('63c20c6331bc478db738a36a289c2c45', '1activityTitle', 'picture-4.jpg', '2022-07-13 17:14:34',
#      '2022-07-12 16:14:34', '2022-07-19 16:14:34', 'rules', 1,
#      'cardName1', 'cardName2', 'cardName3', 'cardName4', 'cardName5',
#      'picture-4.jpg','picture-4.jpg','picture-4.jpg','picture-4.jpg','picture-4.jpg',
#      50, 30, 10, 7, 3),
#     ('6fccb4f2dbe148488e74505016666ff7', '2activityTitle', 'picture-4.jpg', '2022-07-13 16:14:34',
#      '2022-07-11 16:14:34', '2022-08-13 16:14:34', 'rules', 0,
#      'cardName1', 'cardName2', 'cardName3', 'cardName4', 'cardName5',
#      'picture-4.jpg','picture-4.jpg','picture-4.jpg','picture-4.jpg','picture-4.jpg',
#      50, 30, 10, 7, 3);



CREATE TABLE Involvements(
    involvementId  VARCHAR(33) PRIMARY KEY,
    userId VARCHAR(33) NOT NULL ,
    activityId VARCHAR(33) NOT NULL ,
    involvementTime DATETIME NOT NULL ,
    FOREIGN KEY (userId) REFERENCES Users(userId),
    FOREIGN KEY (activityId) REFERENCES Activities(activityId)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

# INSERT INTO Involvements
#     (involvementId, userId, activityId, involvementTime)
# VALUES
#     ('d241df3e2c56420683078e0dca21ffaf', '3ce9c94840034f41a346cfef5295da35',
#      '63c20c6331bc478db738a36a289c2c45', '2022-07-17 16:14:34');

CREATE TABLE ScratchingEvents(
    scratchingEventId VARCHAR(33) PRIMARY KEY,
    involvementId VARCHAR(33) NOT NULL ,
    cardId TINYINT NOT NULL ,
    ScratchingTime DATETIME NOT NULL ,
    FOREIGN KEY (involvementId) REFERENCES Involvements(involvementId)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE SharingEvents(
    sharingEventId VARCHAR(33) PRIMARY KEY,
    involvementId VARCHAR(33) NOT NULL ,
    SharingTime DATETIME NOT NULL ,
    sharingUrl LONGTEXT NOT NULL ,
    FOREIGN KEY (involvementId) REFERENCES Involvements(involvementId)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE assistEvents(
    assistEventId VARCHAR(33) PRIMARY KEY,
    involvementId VARCHAR(33) NOT NULL ,
    userId VARCHAR(33) NOT NULL ,
    activityId VARCHAR(33) NOT NULL ,
    assistTime DATETIME NOT NULL ,
    FOREIGN KEY (involvementId) REFERENCES Involvements(involvementId),
    FOREIGN KEY (userId) REFERENCES Users(userId),
    FOREIGN KEY (activityId) REFERENCES Activities(activityId)
) ENGINE=INNODB DEFAULT CHARSET=utf8;