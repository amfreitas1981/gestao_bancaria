# gestao_bancaria

# Avaliação #

# Desenvolvedor Backend Java #

### Autor / Responsável do Projeto: ###
#### Alexandre Mendes de Freitas

> ### Status: ### 
> #### Concluído, incluído todos os testes unitários realizados e atualizados no GitHub.

> ### Objetivo: ###
> Responsável para realizar transações bancárias

> ### Descrição do Projeto: ###
> Este projeto, apesar de ser experimental, consiste na implementação de um microserviço, 
> que compreende o processo de criação de contas bancárias e transações que podem ser 
> realizadas via aplicação, selecionando as formas de pagamento:
> + Cartão de Débito
> + Cartão de Crédito
> + PIX

> ### Funcionalidades e Demonstração da Aplicação: ###
#### O funcionamento desta aplicação é feito da seguinte forma: ####
#### As ações aplicadas durante o desenvolvimento foram as seguintes: ####

> ##### Primeiro Passo: pom.xml #####
O pom.xml foi criado junto com o pacote, gerado pelo Spring Initializr (https://start.spring.io/).
O projeto foi feito no Maven, na versão 3.2.6, na linguagem Java, iniciado nas dependências abaixo,
adicionando, conforme os avanços no desenvolvimento da aplicação, importado, em seguida, o pacote 
na IDE (IntelliJ).
>
> 	<dependencies>
> 		<dependency>
> 			<groupId>org.springframework.boot</groupId>
> 			<artifactId>spring-boot-starter-validation</artifactId>
> 		</dependency>
> 		<dependency>
> 			<groupId>org.springframework.boot</groupId>
> 			<artifactId>spring-boot-starter-web</artifactId>
> 		</dependency>
>
> 		<dependency>
> 			<groupId>org.springframework.boot</groupId>
> 			<artifactId>spring-boot-devtools</artifactId>
> 			<scope>runtime</scope>
> 			<optional>true</optional>
> 		</dependency>
>
> 		<dependency>
> 			<groupId>org.projectlombok</groupId>
> 			<artifactId>lombok</artifactId>
> 			<optional>true</optional>
> 		</dependency>
> 		<dependency>
> 			<groupId>org.springframework.boot</groupId>
> 			<artifactId>spring-boot-starter-test</artifactId>
> 			<scope>test</scope>
> 		</dependency>
>
> 		<dependency>
> 			<groupId>org.springframework.security</groupId>
> 			<artifactId>spring-security-test</artifactId>
> 			<scope>test</scope>
> 		</dependency>
> 	</dependencies>
> 

> ##### Segundo Passo: Organização das packages #####
Verificar, dentro do contexto, quais são as packages que precisam ser criadas, para critérios 
de organização, além de especificar sua devida funcionalidade para a regra de negócio específica 
a ser utilizada para a aplicação. Elas foram definidas como:
>
> + controller
> + domain
>     + accounts
>     + transaction
>     + username
>     + profile
> + infra
>     + exception
>     + security
>     + springdoc
>

> ##### Terceiro Passo: Criar as classes AccountController e AccountService #####
Nessa classe, foram criados todos os métodos necessários para atenderem as principais necessidades 
solicitadas na especificação para a regra de negócio, seguindo as regras do CRUD. A partir dessa 
classe foi iniciada o processo de criação da JPA, seja da classe Account, o mesmo vale para a classe 
Transaction.
Nela, foi definida a criação do endpoint, seguindo as orientações, para utilizarem a nomenclatura 
informada na documentação fornecida.
Sobre os conceitos técnicos aplicados para a implementação, foram utilizadas as anotações @RestController, 
para indicar que é uma classe controller para ser chamada dentro do contexto, @RequestMapping, onde contém 
o endpoint criado em questão e a anotação @SecurityRequirement, que corresponde ao requerimento de 
segurança ao carregar a documentação do Swagger no momento que o usuário carregar o token de segurança, 
que será visto mais adiante.
Os tratamentos aplicados para regra de negócio, foram implementados na classe AccountService, que, por sua
vez, efetua as chamadas para a interface AccountRepository, onde carregam todo acesso ao Banco de Dados, 
conduzido pela classe de entidade.
O endpoint adotado para esse processo é "**/conta**".

> ##### Quarto Passo: Criação das classes JPA e seus relacionamentos #####
Neste passo, foram criadas as classes Account e Transaction, basicamente, utilizando o relacionamento um 
para um (@OneToOne), orientados via documentação, desde o início da solicitação.
Para isso, foram utilizadas as dependências abaixo, primeiro, pensando na criação das classes JPA, segundo, 
na criação das tabelas, utilizando o Flyway DB e depois o H2, definido para a criação do Banco, configurando em 
seguida, no application.yml, as configurações para conexão.

Finalizando este passo, foi criada a package "db.migration", acompanhada do primeiro script, para ser executada 
no Flyway. Lembrando que a aplicação deverá estar parada enquanto estiver configurando. Quando terminar, subir 
a aplicação novamente, iniciando a console do H2, utilizando as configurações locais.
Dados:
+ URL: http://localhost:8080/h2-console
+ Driver Class: org.h2.Driver
+ JDBC URL: jdbc:h2:mem:baccount_api
+ User Name: sa
+ Password:

**OBSERVAÇÃO:** Clicar em Test Connection antes, para verificar se a conexão foi feita com sucesso. Se sim, clicar 
em Connect.

As dependências utilizadas para o desenvolvimento foram as seguintes:
>
> 		<dependency>
> 			<groupId>org.springframework.boot</groupId>
> 			<artifactId>spring-boot-starter-data-jpa</artifactId>
> 		</dependency>
>
> 		<dependency>
> 			<groupId>com.h2database</groupId>
> 			<artifactId>h2</artifactId>
> 			<scope>runtime</scope>
> 		</dependency>
>
> 		<dependency>
> 			<groupId>org.hibernate.validator</groupId>
> 			<artifactId>hibernate-validator</artifactId>
> 			<version>6.1.5.Final</version>
> 		</dependency>
>

> ##### Quinto Passo: Criação das classes TransactionController e TransactionService #####
Nesta classe, foi criado somente o método para criar transações, desde que: 
Exista uma conta para ser efetuada a transação;
Que tenha um saldo maior ou igual ao valor desejado para aplicar a transação.
Os tratamentos aplicados para regra de negócio, foram implementados na classe TransactionService, que, por sua
vez, efetua as chamadas para a interface TransactionRepository, onde carregam todo acesso ao Banco de Dados,
conduzido pela classe de entidade.
O endpoint adotado para esse processo é "**/transacao**".

> ##### Sexto Passo: Criação de Usuários e Serviços de Autenticação JWT #####
Para validar o token, é utilizada a lógica abaixo:
O JWT é dividido em 3 partes, header, payload e signature, separados pelo caractere de ponto. Por isso é necessário 
validar se foi possível dividir ele em 3 partes.
O JWT é codificado em Base64Url, por isso usamos o Base64 URL decoder para decodificar as partes, onde, caso a 
decodificação falhe, é lançada uma exceção que é capturada para retornar false, negando a validade do token.
Para acessar os métodos que a aplicação tem à disposição, com segurança, foi desenvolvido também serviços, 
responsáveis para autenticação de validação, por meio de geração de token. As configurações estão localizadas
nas packages domain.profile, domain.username e infra. Este processo, necessariamente é feito conforme a 
utilização, contanto que o usuário envolvido esteja cadastrado no Banco de Dados. Por padrão, foi desenvolvido 
os usuários 'Administrador' e 'Teste'. Cada usuário, antes de navegar pela aplicação, deverá criar um usuário 
para autenticação, fornecendo os seguintes dados:
+ nome - Nome do usuário;
+ login - Uma conta de e-mail, que servirá como login;
+ password - Deverá criar uma senha numérica. Após o cadastro, este dado será automaticamente criptografado, no 
formato BCrypt.

Para isso, foram incluídas as dependências abaixo:
> 
> 		<dependency>
> 			<groupId>org.springframework.boot</groupId>
> 			<artifactId>spring-boot-starter-security</artifactId>
> 		</dependency>
>
> 		<dependency>
> 			<groupId>com.auth0</groupId>
> 			<artifactId>java-jwt</artifactId>
> 			<version>4.2.1</version>
> 		</dependency>
> 

> ##### Sétimo Passo: Criação de script para criação das tabelas no Banco de Dados, utilizando o Flyway #####
Tratando-se de utilização e testes da aplicação em um ambiente local, foi criado um script, executado via Flyway, para 
criação de tabelas e relacionamentos. Está localizado em: 
>
> gestao_bancaria > src > main > resources > db.migration > V1___create-tables.sql
>
Dependência:
>
> 		<dependency>
> 			<groupId>org.flywaydb</groupId>
> 			<artifactId>flyway-core</artifactId>
> 		</dependency>
>

> ##### Oitavo Passo: Testes da Aplicação (Ambiente) #####
Ao inicializar a aplicação, será necessário criar no Insomnia, todos os cenários necessários para serem aplicados ao 
processo relacionado a criação de Conta (Account) e Transação (Transaction), com os métodos de criação (contas e 
transação) e consulta, conforme requisitos solicitados para desenvolver este projeto.
Para isso, deverá seguir os seguintes passos:
1. Inicializar a aplicação local;
2. Inicializar a console do Banco de Dados H2;
3. Acessar a console, dado opcional, pela URL, somente para verificação, após inicializar a aplicação. Após executar 
os passos 1 e 2, poderá navegar pela console e nela, verificar todas as tabelas que foram criadas. 
4. Abrir uma ferramenta de API Client para testes (Insomnia / Postman), criar uma requisição do tipo POST, com a 
URL: http://localhost:8080/login. Na aba Body, selecionar a opção JSON e clicar abaixo, para montar o corpo da 
chamada, igual ao exemplo. Feito isso, clicar no botão Send. Com o token gerado, copiar o conteúdo que está dentro 
das aspas duplas.
5. Após copiar o token no item anterior, o próximo passo é Criar Account (Conta), pelo Método POST. Lembrando que 
se não colar o token antes da chamada, será retornado o erro, porque faltou a etapa da autenticação. Do contrário, 
a conta será criada com sucesso. Após a execução, será retornado o HTTP STATUS CODE 201, para informar que a 
requisição foi criada com sucesso. Os dados a serem informados para chamar o método, são:
+ numero_conta (tipo String): informar, uma sequência de caracteres de 3 a 6 dígitos numéricos, definidos no código.
Exemplo: "746"
+ saldo (tipo Double): informar o valor, seja inteiro ou com duas casas decimais após o ponto. Exemplo: "234.56"
6. Consultar conta. Este método foi desenvolvido para consultar contas que foram gravadas no Banco de Dados. O 
critério de busca é feito, ao informar o número da conta. Existindo na base, retornará o HTTP STATUS CODE 200, para
sucesso e ou o código 404, para casos de erro, se a conta não existir cadastro no Banco. Para executar, primeiro o 
usuário deverá autenticar, via token, para validar o código e depois informar o número da conta e clicar em Send, 
em seguida.
7. Criar Transação (Transaction). Ao executar, cadastrando a transação com sucesso, será retornado o HTTP STATUS CODE 
201, para caso de sucesso e o HTTP STATUS CODE 404, para erro, se não tiver saldo disponível, respeitando a especificação 
para este cenário. Implementado o método POST, após passar pelo processo de autenticação, ao gerar o token, deverá passar 
os seguintes campos:
+ forma_pagamento (tipo enum): o usuário deverá informar os códigos aplicados para a forma de pagamento. Cada uma das opções
selecionadas, o usuário poderá selecionar a forma de pagamento desejada, e cada uma das opções, no Enum desenvolvido, adotando 
o Princípio de Strategy, em Java, foram adicionadas as regras para calcular as taxas de transação, na medida que o usuário 
solicita, conforme a necessidade. As formas utilizadas, junto com suas fórmulas para cálculo adicional das taxas, são as 
seguintes:
  + Cartão de Crédito ("C") - deverá calcular 5% sobre o valor informado;
  + Cartão de Débito ("D") - deverá calcular 3% sobre o valor informado;
  + PIX - não haverá custos.
+ numero_conta - servirá como base ou referência para localizar a conta no Banco de Dados;
+ valor - dado necessário para haver a transação.

**ATENÇÃO**: Para cada tipo de chamada de método a ser chamada, independente da opção que poderão ser aplicados, o usuário deverá
configurar o token, responsável para autenticar as requisições, antes de executá-las. Após a geração, terá a validade de 2 horas,
antes de expirar, lembrando que é válida para utilizar em qualquer chamada do método designado para esta aplicação. Se houver a 
necessidade de continuar com as chamadas após a validade do token, o processo deverá ser repetido.

**OBSERVAÇÃO**: Na classe de entidade Account, foi implementado o campo "ativo", além dos campos solicitados na documentação.
Funciona como um campo chave, que pode ser utilizado para inativar uma conta, ao invés de remover em definitivo, no Banco de Dados.
Basicamente esta função. Além do campo, também foi adicionado o método "deleteOrInvalidateInformations". Este método, mesmo que não 
seja utilizado para o desafio proposto, foi criado, pensando no sentido de remover uma conta, apenas para inativar (alterando de true, 
para false), ao validar quando verificar ou consultar no Banco de Dados, sem precisar remover de fato, permitindo reativar, quando 
precisar.

> ##### Nono Passo: Adicionar a dependência para documentação Swagger, incluindo configuração token JWT e processos para testes #####
Para utilizar a documentação Swagger, seja para efetuar testes, garantir documentação, para efeitos de qualidade ou efetuar testes da 
aplicação, poderá utilizar o Swagger, seja da forma tradicional, seja da forma segura que o usuário necessita acessar, através de token.
Visitar o site https://springdoc.org/ para tutorial, carregar a dependência, no caso copiar e colar no pom.xml. Exemplo:
> 
> 		<dependency>
> 			<groupId>org.springdoc</groupId>
> 			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
> 			<version>2.5.0</version>
> 		</dependency>
> 
O Swagger nada mais é que a documentação do projeto, quando está no ar, garantindo o funcionamento e as funcionalidades solicitadas, 
permitindo também, realizar testes, incluindo a utilização de autenticações de segurança, via token. A diferença é que o token, após 
ser gerado, pode ser informado para cada serviço HTTP que será utilizado ou informado, de forma geral, clicando no botão _Authorize_, 
válida para todos os serviços aplicados, respeitando o tempo de validade do token gerado. A URL criada é (http://localhost:8080/swagger-ui/index.html).

> ##### Décimo Passo: Realização de testes unitários (cobertura de código) #####
Os testes unitários foram aplicados para as classes, sejam de entidade, classes DTO (entrada e saída de dados), incluindo classes 
controller e service.
Todo o processamento foi feito, utilizando JUnit 5 e Mockito, localizados em:
> 
> gestao_bancaria > src > test > java.com.banking.account.transact
> 


##### Link Api-Docs #####
http://localhost:8080/v3/api-docs

### Tecnologias utilizadas: ###
> + Java 17;
> + Spring Boot 3;
> + IDE - IntelliJ IDEA;
> + Paradigma de Orientação a Objetos;
> + Banco de Dados H2;
> + Flyway DB;
> + Spring Security;
> + JWT;
> + Swagger;
> + JUnit5;
> + Mockito;
> + GitHub;
> + Insomnia;
> + Postman;



### Conclusão ###
A construção da aplicação foi concluída, mostrando como pode ser desenvolvida, através do documento informado, todo o processo 
necessário, através dos requisitos e das regras de negócio, como efetuar o desenvolvimento simples de um microserviço backend, 
exibindo o relacionamento entre tabelas, a criação de dados entre elas, apresentados, dentro da especificação, provavelmente, 
previstos, com requisitos básicos para funcionamento do fluxo.
