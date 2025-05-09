-- Crear la base de datos si no existe y usarla
CREATE DATABASE IF NOT EXISTS taskdb;
USE taskdb;

-- Crear la tabla 'tareas' si no existe
CREATE TABLE IF NOT EXISTS tareas (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      titulo VARCHAR(100),
                                      descripcion TEXT,
                                      estado VARCHAR(50)
);

-- Insertar datos en la tabla 'tareas'
INSERT INTO tareas (titulo, descripcion, estado) VALUES
                                                     ('Comprar útiles', 'Ir a la librería', 'Pendiente'),
                                                     ('Estudiar Java', 'Repasar conceptos de POO', 'En progreso'),
                                                     ('Estudiar Angular', 'Repasar conceptos de POO', 'En progreso'),
                                                     ('Entrega del trabajo', 'Subir al aula virtual', 'Completado');

-- Seleccionar todos los registros de la tabla 'tareas'
SELECT * FROM tareas;


