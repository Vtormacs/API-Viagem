CREATE TABLE participante (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    nome VARCHAR (255) NOT NULL,
    email VARCHAR (255) NOT NULL,
    confirmacao BOOLEAN NOT NULL,
    viagem_id UUID,
    FOREIGN KEY (viagem_id) REFERENCES viagens(id) ON DELETE CASCADE
);