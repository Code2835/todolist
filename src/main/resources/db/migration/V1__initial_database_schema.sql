CREATE TABLE "role" (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE "person" (
    "id" SERIAL PRIMARY KEY,
    "first_name" VARCHAR(255) NOT NULL,
    "last_name" VARCHAR(255) NOT NULL,
    "role_id" INT NOT NULL
);

CREATE TABLE "todoitem" (
    "id" SERIAL PRIMARY KEY,
    "item" TEXT NOT NULL,
    "completed" BOOLEAN NOT NULL,
    "person_id" INT NOT NULL
);

ALTER TABLE "person"
ADD CONSTRAINT "fk__person__role_id"
FOREIGN KEY ("role_id")
REFERENCES "role" ("id")
ON DELETE RESTRICT;

ALTER TABLE "todoitem"
ADD CONSTRAINT "fk__todoitem__person_id"
FOREIGN KEY ("person_id")
REFERENCES "person" ("id")
ON DELETE CASCADE;