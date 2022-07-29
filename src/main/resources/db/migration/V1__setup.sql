CREATE TABLE users
(
    user_id  INT UNSIGNED NOT NULL AUTO_INCREMENT,
    first_name   varchar(30) NOT NULL,
    last_name    varchar(30) NOT NULL,
    age  		INT UNSIGNED NOT NULL,
    mail        varchar(40) NOT NULL,
	PRIMARY KEY (user_id)
);

