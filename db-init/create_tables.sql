CREATE TYPE match_status AS ENUM ('CREATED', 'IN_PROGRESS', 'ENDED');

CREATE TABLE match (
    id SERIAL PRIMARY KEY,
    status match_status NOT NULL,
    winner VARCHAR(1),
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE move (
    id SERIAL PRIMARY KEY,
    match_id INTEGER NOT NULL REFERENCES match(id),
    player VARCHAR(1) NOT NULL,
    x INTEGER NOT NULL,
    y INTEGER NOT NULL,
    move_number INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);