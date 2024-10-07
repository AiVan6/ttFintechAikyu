CREATE Table settings(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255) NOT NULL UNIQUE ,
    value DECIMAL NOT NULL
);

CREATE TABLE verified_name (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    other_name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL
);

CREATE TABLE reg_person (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    last_name VARCHAR(255) NOT NULL
);

CREATE TABLE credit_bureau (
    id SERIAL PRIMARY KEY,
    verified_name_id BIGINT NOT NULL REFERENCES verified_name(id) ON DELETE CASCADE
);

CREATE TABLE loan_request (
    id SERIAL PRIMARY KEY,
    loan_uui_request VARCHAR(255) NOT NULL UNIQUE,
    reg_presin_id BIGINT NOT NULL REFERENCES reg_person(id) ON DELETE CASCADE,
    credit_bureau_id BIGINT NOT NULL REFERENCES credit_bureau(id) ON DELETE CASCADE
);

CREATE TABLE request_content (
    id SERIAL PRIMARY KEY,
    content JSONB NOT NULL unique
);

CREATE TABLE account_info (
    id SERIAL PRIMARY KEY,
    credit_bureau_id BIGINT NOT NULL REFERENCES credit_bureau(id) ON DELETE CASCADE,
    account_number VARCHAR(255) NOT NULL,
    account_status VARCHAR(255),
    current_balance DECIMAL NOT NULL,
    date_opened DATE,
    days_in_arrears INTEGER,
    delinquency_code VARCHAR(10),
    highest_days_in_arrears INTEGER,
    is_your_account BOOLEAN,
    last_payment_amount DECIMAL,
    last_payment_date DATE,
    loaded_at DATE,
    original_amount DECIMAL,
    overdue_balance DECIMAL,
    overdue_date DATE,
    product_type_id INTEGER
);


