# Api Sistema Biblioteca


📚 Descrição do Projeto
A Biblioteca API REST é uma aplicação desenvolvida em Spring Boot que fornece um sistema robusto para o gerenciamento de livros em uma biblioteca. Esta API permite realizar operações CRUD (Criar, Ler, Atualizar, Excluir) em livros e gerenciar reservas e filas de espera para livros que já estão reservados. O armazenamento dos dados é feito em memória, ideal para desenvolvimento e testes rápidos.





🎯 Funcionalidades
  1. Gerenciamento de Livros
        - Adicionar Livros: Registre novos livros na biblioteca.
        - Listar Livros: Obtenha uma lista de livros com opções para ordenação por título ou autor e filtragem por categoria.
        - Excluir Livros: Remova livros da biblioteca, garantindo que livros com reservas ativas não possam ser excluídos.

  2. Sistema de Reservas e Filas de Espera
        - Reservar Livros: Reserve um livro; se o livro estiver reservado, adicione o usuário à fila de espera.
        - Listar Fila de Espera: Veja a fila de espera para um livro específico.
        - Cancelar Reserva: Cancele uma reserva ou remova um usuário da fila de espera.
          
  3. Exceções e Tratamento de Erros
       - LivroNaoEncontradoException: Retornado quando um livro não é encontrado.
       - LivroJaReservadoException: Retornado quando uma tentativa de reserva é feita em um livro já reservado.
       - Tratamento Global de Erros: Manipulação centralizada das exceções para respostas HTTP apropriadas.
    



🚀 Tecnologias Utilizadas
       - Spring Boot: Framework principal para desenvolvimento da API REST.
       - Java 17: Linguagem de programação utilizada.
       - JUnit 5: Para criação e execução de testes.
       - Postman: Ferramenta para teste dos endpoints da API.
       - Lombok: Biblioteca para simplificar o código Java (opcional).


       

📋 Endpoints
  
  
  1. Criar um Livro
       Método: POST
       URL: /livros
       Request Body:
     {
      "isbn": "978-3-16-148410-0",
      "titulo": "O Senhor dos Anéis",
      "autor": "J.R.R. Tolkien",
      "categoria": "Ficção"
     }
     Respostas:
      201 Created: Livro criado com sucesso.
      400 Bad Request: ISBN já existente.

  
  2. Listar Livros
      Método: GET
      URL: /livros
      Parâmetros de Query:
      ordenarPor (opcional): "autor" ou "titulo".
      categoria (opcional): Categoria para filtrar.
     Respostas:
      200 OK: Lista de livros com sucesso.
      404 Not Found: Nenhum livro encontrado.
 
  
  3. Excluir um Livro
      Método: DELETE
      URL: /livros/{isbn}
     Respostas:
      204 No Content: Livro excluído com sucesso.
      404 Not Found: Livro não encontrado.
      409 Conflict: Livro com reserva ativa.
  
  
  4. Reservar um Livro
      Método: POST
      URL: /reservas/{isbn}
      Request Body: json
        {
          "userId": "12345"
       }
     Respostas:
      200 OK: Reserva realizada com sucesso.
      409 Conflict: Livro já reservado, usuário adicionado à fila de espera.
  
  
  5. Listar Fila de Espera
      Método: GET
      URL: /reservas/{isbn}
     Respostas:
      200 OK: Lista de usuários na fila de espera.
     404 Not Found: Livro não encontrado.
  
  
  6. Cancelar uma Reserva
      Método: DELETE
      URL: /reservas/{isbn}/{userId}
     Respostas:
      200 OK: Reserva cancelada ou usuário removido da fila de espera.
      404 Not Found: Livro ou usuário não encontrado.


🔧 Execução da Aplicação
      
  1. Clone o Repositório: git clone (https://github.com/ArtFiorindo/ApiSistemaBiblioteca.git)

  2. Navegue até o Diretório do Projeto: cd biblioteca-api

  3. Compile e Execute a Aplicação: ./mvnw spring-boot:run

  4. Teste a API com Postman: Utilize o Postman para enviar requisições aos endpoints e verificar o funcionamento da API.


💡 Contribuição
  Contribuições são bem-vindas! Para contribuir:

   1. Fork o repositório.
   2. Crie uma nova branch (git checkout -b feature/nova-funcionalidade).
   3. Faça suas alterações e commit (git commit -am 'Adiciona nova funcionalidade').
   4. Push para a branch (git push origin feature/nova-funcionalidade).
   5. Abra um Pull Request.



