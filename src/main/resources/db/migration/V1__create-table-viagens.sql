CREATE TABLE viagens (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    destino VARCHAR (255) NOT NULL,
    inicio TIMESTAMP NOT NULL,
    fim TIMESTAMP NOT NULL,
    confirmacao BOOLEAN NOT NULL,
    nome_dono_da_viagem VARCHAR (255) NOT NULL,
    email_dono_da_viagem VARCHAR (255) NOT NULL
);