CREATE TABLE atividades (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    data TIMESTAMP NOT NULL,
    viagem_id UUID,
    FOREIGN KEY (viagem_id) REFERENCES viagens(id) ON DELETE CASCADE
);