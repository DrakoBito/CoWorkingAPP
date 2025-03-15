INSERT INTO spaces (id, name, description) VALUES (1, 'Sala de reuniones', 'Espacio con proyector y pizarra') ON DUPLICATE KEY UPDATE name=name;
INSERT INTO spaces (id, name, description) VALUES (2, 'Sala de conferencias', 'Espacio amplio para eventos') ON DUPLICATE KEY UPDATE name=name;
INSERT INTO spaces (id, name, description) VALUES (3, 'Coworking privado', 'Espacio individual con escritorio') ON DUPLICATE KEY UPDATE name=name;
