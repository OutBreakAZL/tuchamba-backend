-- src/main/resources/data.sql
-- Insertar usuarios iniciales
INSERT INTO users (name, email, password, phone) VALUES
                                                     ('Admin', 'admin@chamba.com', '$2a$10$E7QGvjLHnm8EhOQ6RYo2aOKPFKY8gHmJBqPZf8YJmJkY8QGvjL8Y.', '987654321'),
                                                     ('Usuario Uno', 'user1@chamba.com', '$2a$10$E7QGvjLHnm8EhOQ6RYo2aOKPFKY8gHmJBqPZf8YJmJkY8QGvjL8Y.', '987654322'),
                                                     ('Freemium User', 'freemium@chamba.com', '$2a$10$E7QGvjLHnm8EhOQ6RYo2aOKPFKY8gHmJBqPZf8YJmJkY8QGvjL8Y.', '987654323'),
                                                     ('Premium User', 'premium@chamba.com', '$2a$10$E7QGvjLHnm8EhOQ6RYo2aOKPFKY8gHmJBqPZf8YJmJkY8QGvjL8Y.', '987654324'),
                                                     ('Soporte Técnico', 'soporte@chamba.com', '$2a$10$E7QGvjLHnm8EhOQ6RYo2aOKPFKY8gHmJBqPZf8YJmJkY8QGvjL8Y.', '987654325'),
                                                     ('Desarrollador', 'dev@chamba.com', '$2a$10$E7QGvjLHnm8EhOQ6RYo2aOKPFKY8gHmJBqPZf8YJmJkY8QGvjL8Y.', '987654326'),
                                                     ('Analista', 'analista@chamba.com', '$2a$10$E7QGvjLHnm8EhOQ6RYo2aOKPFKY8gHmJBqPZf8YJmJkY8QGvjL8Y.', '987654327'),
                                                     ('Líder de Proyecto', 'lider@chamba.com', '$2a$10$E7QGvjLHnm8EhOQ6RYo2aOKPFKY8gHmJBqPZf8YJmJkY8QGvjL8Y.', '987654328'),
                                                     ('Diseñador UX', 'ux@chamba.com', '$2a$10$E7QGvjLHnm8EhOQ6RYo2aOKPFKY8gHmJBqPZf8YJmJkY8QGvjL8Y.', '987654329'),
                                                     ('Tester QA', 'qa@chamba.com', '$2a$10$E7QGvjLHnm8EhOQ6RYo2aOKPFKY8gHmJBqPZf8YJmJkY8QGvjL8Y.', '987654330'),
                                                     ('Jose Manuel', 'micchon@gmail.com', '$2a$10$E7QGvjLHnm8EhOQ6RYo2aOKPFKY8gHmJBqPZf8YJmJkY8QGvjL8Y.', '931403087');

-- Insertar mensajes iniciales
INSERT INTO messages (sender, receiver, content, timestamp) VALUES
                                                                ('user1@chamba.com', 'admin@chamba.com', 'Hola, tengo una duda', '2024-09-01T10:00:00'),
                                                                ('freemium@chamba.com', 'admin@chamba.com', '¿Puedo actualizar mi plan?', '2024-09-02T10:00:00'),
                                                                ('premium@chamba.com', 'user1@chamba.com', 'Hola, ¿quieres colaborar?', '2024-09-03T10:00:00'),
                                                                ('admin@chamba.com', 'user1@chamba.com', 'Sí, claro.', '2024-09-04T10:00:00'),
                                                                ('user1@chamba.com', 'freemium@chamba.com', 'Mira esta recomendación', '2024-09-05T10:00:00'),
                                                                ('freemium@chamba.com', 'premium@chamba.com', 'Buen trabajo en la evaluación', '2024-09-06T10:00:00'),
                                                                ('admin@chamba.com', 'freemium@chamba.com', 'Tu cuenta fue actualizada', '2024-09-07T10:00:00'),
                                                                ('user1@chamba.com', 'admin@chamba.com', '¿Cómo edito mi perfil?', '2024-09-08T10:00:00'),
                                                                ('premium@chamba.com', 'admin@chamba.com', 'Gracias por el soporte', '2024-09-09T10:00:00'),
                                                                ('admin@chamba.com', 'premium@chamba.com', 'Estamos para ayudarte', '2024-09-10T10:00:00'),
                                                                ('parionalucasjosemanuel268@gmail.com', 'alma@gmail.com', 'HOLA BIENVENIDO', '2025-05-16T08:28:18'),
                                                                ('parionalucasj@gmail.com', 'lucia@gmail.com', 'NECESITO MAS INFORMACION SOBRE LA EMPRESA TECNO ORIGIN', '2025-05-17T00:40:30');
