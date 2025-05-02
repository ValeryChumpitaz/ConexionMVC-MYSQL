CREATE DATABASE IF NOT EXISTS taskdb;

USE taskdb;
    CREATE TABLE IF NOTS EXISTS tareas(
        id INT AUTO_INCREMENT PRIMARY KEY,
        titulo VARCHAR(100),
        descripcion TEXT,
        estado VARCHAR(50)
    );

INSERT INTO tarea(titulo, descripcion, estado) VALUES
                ('Comprar utiles', 'Ir a la libreria', 'pediente'),
                ('Estudiar Java', 'Estudiar los conceptos de POO', 'en progreso'),
                ('Subir al aula virtual', 'Entrega de trabajo', 'Completado');