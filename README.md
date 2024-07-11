# ViViagens

O projeto ViViagens é uma API que tem como objetivo ajudar os usuários a organizar viagens a trabalho ou lazer. Os usuários podem criar viagens com nome, data de início e fim. Dentro da viagem, eles podem planejar suas atividades para cada dia.

## Funcionalidades

1. **Cadastro de Viagem:**
   - O usuário informa o local de destino, data de início, data de término, e-mails dos convidados, nome completo e endereço de e-mail.
   - O criador da viagem recebe um e-mail para confirmar a nova viagem através de um link. Ao clicar no link, a viagem é confirmada, os convidados recebem e-mails de confirmação de presença e o criador é redirecionado para a página da viagem.

2. **Participantes:**
   - Os convidados, ao clicarem no link de confirmação de presença, são redirecionados para a aplicação onde devem inserir seu nome (além do e-mail que já estará preenchido) e então estarão confirmados na viagem.
   - Novos participantes podem ser convidados dentro da página do evento através do e-mail e devem passar pelo fluxo de confirmação como qualquer outro convidado.

3. **Detalhes da Viagem:**
   - Na página do evento, os participantes da viagem podem adicionar links importantes da viagem, como reserva do AirBnB, locais para serem visitados, etc.
   - Ainda na página do evento, o criador e os convidados podem adicionar atividades que irão ocorrer durante a viagem com título, data e horário.

## Tecnologias Utilizadas

- Spring Web
- Flyway
- Dev Tools
- Lombok
- JPA
- H2 Database

## Como Testar

1. Clone o repositório.
2. Execute o projeto localmente.
3. Utilize o Insomnia ou outra ferramenta similar para fazer requisições HTTP (GET, POST, PUT) para as rotas da API.

## Endpoints

### Criar Viagem
- Método: POST
- Rota: `localhost:8080/viagens`
- Corpo da Requisição:
  ```json
  {
    "destino": "Foz do Iguacu, PR",
    "inicio": "2024-07-11T21:50:50.111Z",
    "fim": "2024-07-30T21:50:50.111Z",
    "emails_convite": ["teste@gmail.com", "teste2@gmail.com"],
    "nome_dono_da_viagem": "nome",
    "email_dono_da_viagem": "teste3@hotmail.com"
  }

### Registrar Link
- Método: POST
- Rota: `localhost:8080/viagens/{id_da_viagem}/links`
- Corpo da Requisição:
  ```json
  {
  "titulo": "Link do Github",
  "url": "https://github.com/"
  }

### Registrar Atividade
- Método: POST
- Rota: `localhost:8080/viagens/{id_da_viagem}/atividades`
- Corpo da Requisição:
  ```json
  {
  "titulo": "Faculdade",
  "data": "2024-08-01T21:50:50.111Z"
  }

### Registrar Participante
- Método: POST
- Rota: `localhost:8080/participante/{id_do_participante}/confirmacao`
- Corpo da Requisição:
  ```json
  {
  "nome": "nome do participante"
  }

### Atualização de Viagem
- Método: PUT
- Rota: `localhost:8080/viagens/{id_da_viagem}`
- Corpo da Requisição:
  ```json
  {
  "destino": "Santa Terezinha, PR",
  "inicio": "2024-07-11T21:50:50.111Z",
  "fim": "2024-07-30T21:50:50.111Z"
  }

### Confirmar Viagem
- Método: GET
- Rota: `localhost:8080/viagens/{id_da_viagem}/confirmacao`

### Recupera Links da Viagem
- Método: GET
- Rota: `localhost:8080/viagens/{id_da_viagem}/links`

### Recupera Atividades da Viagem
- Método: GET
- Rota: `localhost:8080/viagens/{id_da_viagem}/atividades`

### Recupera Participantes da Viagem
- Método: GET
- Rota: `localhost:8080/viagens/{id_da_viagem}/participante`

### Recupera da Viagem
- Método: GET
- Rota: `localhost:8080/viagens/{id_da_viagem}`
