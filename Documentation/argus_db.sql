SET datestyle = 'iso, dmy';

-- Sequences

CREATE SEQUENCE photoID_seq START 1;
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

CREATE TABLE photo_table(
    photo_id INT NOT NULL DEFAULT nextval('photoID_seq'),
    image bytea NOT NULL,
    deletionDate DATE,
    PRIMARY KEY (photo_id)
);

CREATE TABLE user_table(
    user_id INT NOT NULL DEFAULT nextval('userID_seq'),
    fname TEXT NOT NULL,
    lname TEXT NOT NULL,
    email TEXT NOT NULL,
    username TEXT NOT NULL,
    userPass TEXT NOT NULL,
    userRole roleType NOT NULL,
    profilePhoto INT NOT NULL DEFAULT 1 REFERENCES photo_table(photo_id),
    deletionDate DATE,
    PRIMARY KEY (user_id)
);

CREATE TABLE notification_table(
    notification_id INT NOT NULL DEFAULT nextval('notificationID_seq'),
    message TEXT NOT NULL,
    onDate DATE NOT NULL DEFAULT CURRENT_DATE,
    atTime TIME NOT NULL DEFAULT CURRENT_TIME,
    photo_id INT REFERENCES photo_Table(photo_id),
    deletionDate DATE,
    PRIMARY KEY (notification_id)
);

CREATE TABLE usernotification_table(
    notification_id INT NOT NULL REFERENCES notification_table(notification_id),
    user_id INT NOT NULL REFERENCES user_table(user_id),
    PRIMARY KEY (notification_id, user_id)
);

CREATE TABLE person_table(
    person_id INT NOT NULL DEFAULT nextval('personID_seq'),
    fname TEXT NOT NULL,
    lname TEXT NOT NULL,
    listed listType NOT NULL DEFAULT 'Grey',
    created DATE NOT NULL DEFAULT CURRENT_DATE,
    photo_ID INT NOT NULL DEFAULT 1 REFERENCES photo_table(photo_id),
    deletionDate DATE,
    PRIMARY KEY (person_id)
);

CREATE TABLE vehicle_table(
    vehicle_id INT NOT NULL DEFAULT nextval('vehicleID_seq'),
    listed listType NOT NULL DEFAULT 'Grey',
    licenseNo TEXT NOT NULL,
    created DATE NOT NULL DEFAULT CURRENT_DATE,
    photo_id INT NOT NULL DEFAULT 1 REFERENCES photo_table(photo_id),
    deletionDate DATE,
    PRIMARY KEY (vehicle_id)
);

CREATE TABLE personvehicle_table(
    person_id INT NOT NULL REFERENCES person_table(person_id),
    vehicle_id INT NOT NULL REFERENCES vehicle_table(vehicle_id),
    PRIMARY KEY (person_id, vehicle_id)
);

-- Insert

INSERT INTO photo_table(image) 
VALUES('11111111111111111111111111111111001111111111001111111111000111110011111000111111000001111000011110000011100000001110000111000000011000000000000000000000000100000000000000000000000000000000000000000000000000001000000000000000000000000110000110001000010001100001110011111111001111111100111110011111110011111110011111111111111111111111111111');

INSERT INTO user_table(fname,lname,email,username,userPass,userRole) 
VALUES('Team','Sigma','SigmaCOS301@gmail.com','Sigma','cos301sigma!','Admin');
