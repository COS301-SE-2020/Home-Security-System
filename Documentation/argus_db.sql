SET datestyle = 'iso, dmy';

-- Sequences

CREATE SEQUENCE imageID_seq START 1;
CREATE SEQUENCE notificationID_seq START 1;
CREATE SEQUENCE userID_seq START 1;
CREATE SEQUENCE personID_seq START 1;
CREATE SEQUENCE vehicleID_seq START 1;

-- Domains

CREATE DOMAIN checkRole TEXT
CHECK (VALUE IN ('Admin', 'Advanced', 'Basic'));

CREATE DOMAIN checkList TEXT
CHECK (VALUE IN ('White', 'Grey', 'Black'));

-- Types

CREATE TYPE roleType AS ENUM('Admin', 'Advanced', 'Basic');
CREATE TYPE listType AS ENUM('White', 'Grey', 'Black');

-- Tables

CREATE TABLE Image(
    image_id INT NOT NULL DEFAULT nextval('imageID_seq'),
    photo bytea NOT NULL,
    deletionDate DATE,
    PRIMARY KEY (image_id)
);

CREATE TABLE Users(
    user_id INT NOT NULL DEFAULT nextval('userID_seq'),
    profilePhoto INT REFERENCES Image(image_id),
    fname TEXT NOT NULL,
    lname TEXT NOT NULL,
    email TEXT NOT NULL,
    username TEXT NOT NULL,
    userPass TEXT NOT NULL,
    userRole roleType NOT NULL,
    notifyEmail BOOLEAN NOT NULL DEFAULT true,
    notifyLocal BOOLEAN NOT NULL DEFAULT true,
    deletionDate DATE,
    PRIMARY KEY (user_id)
);

CREATE TABLE Notification(
    notification_id INT NOT NULL DEFAULT nextval('notificationID_seq'),
    image_id INT REFERENCES Image(image_id),
    message TEXT NOT NULL,
    onDate DATE NOT NULL DEFAULT CURRENT_DATE,
    atTime TIME NOT NULL DEFAULT CURRENT_TIME,
    deletionDate DATE,
    PRIMARY KEY (notification_id)
);

CREATE TABLE UserNotification(
    notification_id INT NOT NULL REFERENCES Notification(notification_id),
    user_id INT NOT NULL REFERENCES Users(user_id),
    PRIMARY KEY (notification_id, user_id)
);

CREATE TABLE Person(
    person_id INT NOT NULL DEFAULT nextval('personID_seq'),
    image_id INT REFERENCES Image(image_id),
    fname TEXT NOT NULL,
    lname TEXT NOT NULL,
    listed listType NOT NULL DEFAULT 'Grey',
    created DATE NOT NULL DEFAULT CURRENT_DATE,
    deletionDate DATE,
    PRIMARY KEY (person_id)
);

CREATE TABLE Vehicle(
    vehicle_id INT NOT NULL DEFAULT nextval('vehicleID_seq'),
    image_id INT REFERENCES Image(image_id),
    listed listType NOT NULL DEFAULT 'Grey',
    licenseNo TEXT NOT NULL,
    created DATE NOT NULL DEFAULT CURRENT_DATE,
    deletionDate DATE,
    PRIMARY KEY (vehicle_id)
);

CREATE TABLE PersonVehicle(
    person_id INT NOT NULL REFERENCES Person(person_id),
    vehicle_id INT NOT NULL REFERENCES Vehicle(vehicle_id),
    PRIMARY KEY (person_id, vehicle_id)
);

-- Insert

INSERT INTO Users(fname,lname,email,username,userPass,userRole) 
VALUES('Team','Sigma','SigmaCOS301@gmail.com','Sigma','COS301Sigma!','Admin');
