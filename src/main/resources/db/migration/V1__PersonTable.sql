CREATE TABLE person
(
    id           UUID         NOT NULL PRIMARY KEY,
    name         varchar(100) NOT NULL,
    emailAddress varchar(100) NOT NULL
);

-- INSERT INTO person (id, name, emailAddress) VALUES (gen_random_uuid(), 'Alawode Olanrewaju', 'oa211048@gmail');
-- INSERT INTO person (id, name, emailAddress) VALUES (gen_random_uuid(), 'Alawode Olabanjo', 'olabanjo@gmail');