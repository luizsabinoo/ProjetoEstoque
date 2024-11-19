CREATE TABLE users(
                      id TEXT PRIMARY KEY NOT NULL,
                      login TEXT NOT NULL UNIQUE,
                      password TEXT not null,
                      role TEXT not null
);