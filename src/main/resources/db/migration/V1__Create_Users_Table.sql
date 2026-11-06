CREATE TABLE users (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(7) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE user_health_service (
    user_id VARCHAR(255) NOT NULL,
    health_service_id VARCHAR(255),
    CONSTRAINT fk_user_health_service_user 
        FOREIGN KEY (user_id) 
        REFERENCES users (id) 
        ON DELETE CASCADE
);