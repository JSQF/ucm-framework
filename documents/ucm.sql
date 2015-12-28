DROP TABLE IF EXISTS t_ucm_environment;
CREATE TABLE IF NOT EXISTS t_ucm_environment (
  id                INT PRIMARY KEY AUTO_INCREMENT,
  environment_name  VARCHAR(64)  NOT NULL,
  environment_code  VARCHAR(128) NOT NULL,
  environment_order INT          NOT NULL,
  create_time       DATETIME,
  update_time       TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS t_ucm_user;
CREATE TABLE IF NOT EXISTS t_ucm_user (
  id          INT PRIMARY KEY AUTO_INCREMENT,
  username    VARCHAR(32) NOT NULL,
  create_time DATETIME,
  update_time TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE UNIQUE INDEX idx_username ON t_ucm_user (username);

DROP TABLE IF EXISTS t_ucm_project;
CREATE TABLE IF NOT EXISTS t_ucm_project (
  id          INT PRIMARY KEY AUTO_INCREMENT,
  code        VARCHAR(64) NOT NULL,
  name        VARCHAR(64) NOT NULL,
  type        CHAR(1)     NOT NULL,
  description CHAR(128)   NOT NULL,
  create_time DATETIME,
  update_time TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS t_ucm_event;
CREATE TABLE IF NOT EXISTS t_ucm_event (
  id          INT PRIMARY KEY AUTO_INCREMENT,
  username    VARCHAR(32) NOT NULL,
  event_type  CHAR(1)     NOT NULL,
  description VARCHAR(128),
  event_time  DATETIME    NOT NULL,
  create_time DATETIME,
  update_time TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

