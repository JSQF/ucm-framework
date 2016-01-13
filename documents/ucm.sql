DROP TABLE IF EXISTS t_ucm_environment;
CREATE TABLE IF NOT EXISTS t_ucm_environment (
  id                INT PRIMARY KEY       AUTO_INCREMENT,
  environment_name  VARCHAR(64)  NOT NULL,
  environment_code  VARCHAR(128) NOT NULL,
  environment_order INT          NOT NULL,
  active            CHAR(1)      NOT NULL DEFAULT '1',
  create_time       DATETIME,
  update_time       TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS t_ucm_environment_ip;
CREATE TABLE IF NOT EXISTS t_ucm_environment_ip (
  id             INT PRIMARY KEY AUTO_INCREMENT,
  environment_id INT         NOT NULL,
  ip             VARCHAR(16) NOT NULL,
  create_time    DATETIME,
  update_time    TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS t_ucm_user;
CREATE TABLE IF NOT EXISTS t_ucm_user (
  id          INT PRIMARY KEY AUTO_INCREMENT,
  username    VARCHAR(32) NOT NULL,
  admin       BOOLEAN         DEFAULT FALSE,
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

DROP TABLE IF EXISTS t_ucm_config_version_control;
CREATE TABLE IF NOT EXISTS t_ucm_config_version_control (
  id          INT PRIMARY KEY AUTO_INCREMENT,
  project_id  INT         NOT NULL,
  version     VARCHAR(24) NOT NULL,
  status      BOOLEAN         DEFAULT TRUE,
  create_time DATETIME,
  update_time TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS t_ucm_property_key;
CREATE TABLE IF NOT EXISTS t_ucm_property_key (
  id                 INT PRIMARY KEY AUTO_INCREMENT,
  version_control_id INT          NOT NULL,
  key_name           VARCHAR(128) NOT NULL,
  create_time DATETIME,
  update_time TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS t_ucm_property_value;
CREATE TABLE IF NOT EXISTS t_ucm_property_value(
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  key_id INT NOT NULL ,
  environment_id int not null,
  property_value varchar(2048),
  create_time DATETIME,
  update_time TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE UNIQUE INDEX idx_keyId_environmentId ON t_ucm_property_value(key_id, environment_id);

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

