-- ============================
-- Cleanup (delete existing data)
-- ============================
DELETE FROM user_activity_logs;
DELETE FROM book_authors;
DELETE FROM book;
DELETE FROM author;
DELETE FROM publisher;
DELETE FROM category;
DELETE FROM member;
DELETE FROM users;

-- ============================
-- Users (with roles)
-- ============================
INSERT INTO users (username, password, role)
VALUES ('admin1', '$2b$12$DKM73.L3es6kUE2gGYAj.u2hAMTfr/WYI63kLjg0I.uz76j77iFV6', 'ADMIN');

INSERT INTO users (username, password, role)
VALUES ('librarian1', '$2b$12$E0uhev7fumOLOhYl3nylXeesJaF5ccKFbryOkBdiEHLOwdwcak8VW', 'LIBRARIAN');

INSERT INTO users (username, password, role)
VALUES ('staff1', '$2b$12$98CtadSDipKiiHEQl6R6Bew1KM7iHEtIqhdVeRDqVOAnDjPZ3LQi6', 'STAFF');

-- ============================
-- Members
-- ============================
INSERT INTO member (first_name, last_name, email, phone_number, address)
VALUES ('John', 'Doe', 'john.doe@example.com', '1234567890', '123 Main St');

INSERT INTO member (first_name, last_name, email, phone_number, address)
VALUES ('Alice', 'Smith', 'alice.smith@example.com', '9876543210', '456 Park Ave');

-- ============================
-- Authors
-- ============================
INSERT INTO author (name) VALUES ('George Orwell');
INSERT INTO author (name) VALUES ('J.K. Rowling');
INSERT INTO author (name) VALUES ('Harper Lee');

-- ============================
-- Publishers
-- ============================
INSERT INTO publisher (name) VALUES ('Penguin Books');
INSERT INTO publisher (name) VALUES ('Bloomsbury');
INSERT INTO publisher (name) VALUES ('HarperCollins');

-- ============================
-- Categories
-- ============================
INSERT INTO category (name, parent_id) VALUES ('Fiction', NULL);
INSERT INTO category (name, parent_id) VALUES ('Dystopian', (SELECT id FROM category WHERE name = 'Fiction'));
INSERT INTO category (name, parent_id) VALUES ('Fantasy', (SELECT id FROM category WHERE name = 'Fiction'));
INSERT INTO category (name, parent_id) VALUES ('Classic', (SELECT id FROM category WHERE name = 'Fiction'));

-- ============================
-- Books
-- ============================
INSERT INTO book (title, isbn, publication_year, edition, summary, language, cover_image_url, publisher_id, category_id)
VALUES (
           '1984',
           '9780451524935',
           1949,
           '1st',
           'A dystopian novel about a totalitarian regime.',
           'English',
           'https://via.placeholder.com/150',
           (SELECT id FROM publisher WHERE name = 'Penguin Books'),
           (SELECT id FROM category WHERE name = 'Dystopian')
       );

INSERT INTO book (title, isbn, publication_year, edition, summary, language, cover_image_url, publisher_id, category_id)
VALUES (
           'Harry Potter and the Philosopher''s Stone',
           '9780747532699',
           1997,
           '1st',
           'The first novel in the Harry Potter series.',
           'English',
           'https://via.placeholder.com/150',
           (SELECT id FROM publisher WHERE name = 'Bloomsbury'),
           (SELECT id FROM category WHERE name = 'Fantasy')
       );

INSERT INTO book (title, isbn, publication_year, edition, summary, language, cover_image_url, publisher_id, category_id)
VALUES (
           'To Kill a Mockingbird',
           '9780061120084',
           1960,
           '1st',
           'A novel about racial injustice in the Deep South.',
           'English',
           'https://via.placeholder.com/150',
           (SELECT id FROM publisher WHERE name = 'HarperCollins'),
           (SELECT id FROM category WHERE name = 'Classic')
       );

-- ============================
-- Books ↔ Authors (many-to-many)
-- ============================
INSERT INTO book_authors (book_id, author_id)
VALUES (
           (SELECT id FROM book WHERE title = '1984'),
           (SELECT id FROM author WHERE name = 'George Orwell')
       );

INSERT INTO book_authors (book_id, author_id)
VALUES (
           (SELECT id FROM book WHERE title = 'Harry Potter and the Philosopher''s Stone'),
           (SELECT id FROM author WHERE name = 'J.K. Rowling')
       );

INSERT INTO book_authors (book_id, author_id)
VALUES (
           (SELECT id FROM book WHERE title = 'To Kill a Mockingbird'),
           (SELECT id FROM author WHERE name = 'Harper Lee')
       );

-- ============================
-- User Activity Logs
-- ============================
INSERT INTO user_activity_logs (user_id, action, details, ip_address, timestamp)
VALUES (
           (SELECT id FROM users WHERE username = 'admin1'),
           'CREATED_USER',
           'Seeded admin created a user',
           '127.0.0.1',
           NOW()
       );

INSERT INTO user_activity_logs (user_id, action, details, ip_address, timestamp)
VALUES (
           (SELECT id FROM users WHERE username = 'librarian1'),
           'ISSUED_BOOK',
           'Librarian issued a book to a member',
           '127.0.0.1',
           NOW()
       );

INSERT INTO user_activity_logs (user_id, action, details, ip_address, timestamp)
VALUES (
           (SELECT id FROM users WHERE username = 'staff1'),
           'UPDATED_BOOK_INFO',
           'Staff updated book information',
           '127.0.0.1',
           NOW()
       );
