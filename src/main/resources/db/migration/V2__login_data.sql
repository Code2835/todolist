ALTER TABLE "person"
ADD COLUMN "username" VARCHAR(255) NOT NULL DEFAULT '',
ADD COLUMN "password" VARCHAR(255) NOT NULL DEFAULT ''
;

UPDATE "person"
SET "username" = LOWER("first_name"),
    "password" = LOWER("first_name")
;

ALTER TABLE "person"
ALTER COLUMN "username" DROP DEFAULT,
ALTER COLUMN "password" DROP DEFAULT
;