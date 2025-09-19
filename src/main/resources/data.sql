-- ============================
-- Authors
-- ============================
INSERT INTO author (name) VALUES ('George Orwell');
INSERT INTO author (name) VALUES ('J.K. Rowling');

-- ============================
-- Publishers
-- ============================
INSERT INTO publisher (name) VALUES ('Penguin Books');
INSERT INTO publisher (name) VALUES ('Bloomsbury');

-- ============================
-- Categories
-- ============================
INSERT INTO category (name, parent_id) VALUES ('Fiction', NULL);
INSERT INTO category (name, parent_id) VALUES ('Dystopian', 1);
INSERT INTO category (name, parent_id) VALUES ('Fantasy', 1);

-- ============================
-- Members
-- ============================
INSERT INTO member (first_name, last_name, email, phone_number, address)
VALUES ('John', 'Doe', 'john.doe@example.com', '1234567890', '123 Main St');

INSERT INTO member (first_name, last_name, email, phone_number, address)
VALUES ('Alice', 'Smith', 'alice.smith@example.com', '9876543210', '456 Park Ave');

-- ============================
-- Books
-- ============================
INSERT INTO book (title, isbn, publication_year, edition, summary, language, cover_image_url, publisher_id, category_id)
VALUES ('1984', '9780451524935', 1949, '1st',
        'A dystopian novel about a totalitarian regime.',
        'English', 'https://via.placeholder.com/150', 1, 2);

INSERT INTO book (title, isbn, publication_year, edition, summary, language, cover_image_url, publisher_id, category_id)
VALUES ('Harry Potter and the Philosopher''s Stone', '9780747532699', 1997, '1st',
        'The first novel in the Harry Potter series.',
        'English', 'https://via.placeholder.com/150', 2, 3);

-- ============================
-- Books ↔ Authors (many-to-many)
-- ============================
INSERT INTO book_authors (book_id, author_id) VALUES (1, 1); -- 1984 by George Orwell
INSERT INTO book_authors (book_id, author_id) VALUES (2, 2); -- Harry Potter by J.K. Rowling
