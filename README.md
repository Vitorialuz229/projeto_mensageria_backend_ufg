# Projeto Prático – Mensageria Backend

## Descrição

Backend implementado em **Java** usando **Spring Boot** e **Apache Kafka** para comunicação assíncrona entre microserviços.  
Este projeto faz parte da disciplina **Software Concorrente e Distribuído** do curso de Engenharia de Software da **UFG**.

O backend é composto por três microserviços que se comunicam assincronamente via Apache Kafka:

- **Order-Service:** expõe uma API REST para criação de pedidos. Ao receber um pedido, gera um UUID, timestamp e publica o pedido no tópico `orders`.
- **Inventory-Service:** consome o tópico `orders`, verifica disponibilidade e reserva o estoque. Depois, publica no tópico `inventory-events` o resultado da tentativa (sucesso ou falha).
- **Notification-Service:** consome o tópico `inventory-events` e registra no console uma simulação do envio de notificação por e-mail para o cliente.

## Arquitetura de Mensageria

- Tópicos Kafka utilizados:
    - `orders`: onde são publicados pedidos confirmados.
    - `inventory-events`: onde são publicados os resultados da reserva de estoque.

- Os tópicos podem ser particionados para permitir escalabilidade.
- Os consumidores fazem commit manual dos offsets para garantir processamento ordenado e idempotente.

---

## Fluxo de Processamento

1. Cliente realiza requisição POST em `/orders` no Order-Service.
2. Pedido é validado e enviado ao tópico `orders`.
3. Inventory-Service consome a mensagem, tenta reservar os itens no estoque (simulado).
4. Publica resultado no tópico `inventory-events`.
5. Notification-Service consome o evento e imprime no console a notificação simulada.

---

## Tratamento de Falhas

- O Kafka armazena as mensagens até que os consumidores façam o commit de processamento.
- Em caso de falha do consumidor, outra instância pode continuar do último offset.
- Idempotência é garantida por identificadores únicos (UUID) e verificação de duplicatas.

---
## Requisitos Não Funcionais

- **Escalabilidade**: Partições Kafka permitem múltiplas instâncias consumidoras, processando em paralelo.
- **Tolerância a falhas**: Kafka garante persistência das mensagens e recuperação via offset.
- **Idempotência**: Lógica nos consumidores evita efeitos colaterais em caso de reprocessamento de mensagens.

---

## Estrutura do projeto
    mensageria/
    ├── order-service/ # Serviço produtor e API REST
    ├── inventory-service/ # Serviço consumidor + produtor
    └── notification-service/ # Serviço consumidor
---

## Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Kafka
- Apache Kafka 4.0.0
- Maven 
- PostgreSQL 

---

## Ambiente com Docker Compose

O projeto inclui um `docker-compose.yml` que sobe todos os serviços necessários:

- **zookeeper**: gerencia o serviço de coordenação do Kafka.
- **kafka**: broker Kafka configurado para comunicação interna.
- **order-service**: microserviço produtor de pedidos.
- **inventory-service**: microserviço consumidor-produtor para controle de estoque.
- **notification-service**: microserviço consumidor para envio de notificações simuladas.

### Como usar

1. Configure as variáveis de ambiente no arquivo `.env` na raiz do projeto, por exemplo:

```bash 
SPRING_DATASOURCE_URL=jdbc:postgresql://host:port/dbname
SPRING_DATASOURCE_USERNAME=usuario
SPRING_DATASOURCE_PASSWORD=senha
SPRING_JPA_HIBERNATE_DDL_AUTO=update

MAIL_HOST=smtp.exemplo.com
MAIL_PORT=587
MAIL_USERNAME=seu-email@exemplo.com
MAIL_PASSWORD=sua-senha 
```
| ⚠️ Para e-mail, utilize senha de app — nunca sua senha pessoal.

2. Execute o comando para subir todos os containers:
```bash
docker-compose up --build
```

3. Os serviços estarão disponíveis nas portas:

- Order-Service: http://localhost:8081
- Inventory-Service: http://localhost:8082
- Notification-Service: http://localhost:8083
- Kafka broker: acessível internamente pelo hostname kafka:9092

### Rede Docker
Todos os containers estão conectados à rede kafka-network, permitindo comunicação direta entre eles.

### Observações
- O Kafka está configurado com KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 para ambientes locais (não recomendado para produção).
- Cada serviço Spring Boot lê as variáveis de ambiente para conexão com banco, Kafka e e-mail.
- Ajuste as configurações conforme seu ambiente local.

## Como executar manualmente (sem Docker)

### Pré-requisitos

- Apache Kafka rodando localmente (ou em ambiente configurado)
- Java 17+ instalado
- Maven ou Gradle instalado
- Banco de dados PostgreSQL configurado (se usado)

### Passos

1. Clone o repositório do backend:

```bash
git clone https://github.com/Vitorialuz229/projeto_mensageria_backend_ufg.git
cd projeto_mensageria_backend_ufg
```

2. configure o arquivo application.properties (ou use .env com suporte a profiles).
3. Para compilar cada serviço:

```bash
cd order-service
mvn clean install
java -jar target/order-service.jar
```

Repita o processo para `inventory-service` e `notification-service`.

## Endpoints
`POST /orders`

Cria um novo pedido.

Exemplo de body:
```json
{
  "orderItems": [
    {
      "produtoId": "9470a5f5-3e96-49e4-a6be-9c9e7e43939c",
      "quantity": 2
    }
  ]
}
```

`POST /products/importar`
Importa produtos da API externa:
``https://dummyjson.com/docs/products#products-all``

`GET /products/`
Lista todos os produtos cadastrados.

Exemplo de resposta:
```json 
[
	{
		"id": "b4a19a3b-e5f1-4c2f-8eb4-3f9afa46669b",
		"title": "Red Lipstick",
		"description": "The Red Lipstick is a classic and bold choice for adding a pop of color to your lips. With a creamy and pigmented formula, it provides a vibrant and long-lasting finish.",
		"category": "beauty",
		"price": 12.99,
		"stock": 91,
		"tags": [
			"beauty",
			"lipstick"
		],
		"reviews": [
			{
				"id": "193a84ae-5243-492a-bbcb-4ac82e789033",
				"rating": 4,
				"comment": "Great product!",
				"date": "2025-04-30T09:41:02.053Z",
				"reviewerName": "Liam Garcia",
				"reviewerEmail": "liam.garcia@x.dummyjson.com"
			},
			{
				"id": "612e4017-e406-4dc4-aead-2db9deefc600",
				"rating": 5,
				"comment": "Great product!",
				"date": "2025-04-30T09:41:02.053Z",
				"reviewerName": "Ruby Andrews",
				"reviewerEmail": "ruby.andrews@x.dummyjson.com"
			},
			{
				"id": "e9fd5364-cf89-48d4-bea7-d95d0751e136",
				"rating": 5,
				"comment": "Would buy again!",
				"date": "2025-04-30T09:41:02.053Z",
				"reviewerName": "Clara Berry",
				"reviewerEmail": "clara.berry@x.dummyjson.com"
			}
		],
		"images": [
			"https://cdn.dummyjson.com/product-images/beauty/red-lipstick/1.webp"
		]
	},
	{
		"id": "25a7d127-3bad-44ac-bee2-1b2b365618f6",
		"title": "Red Nail Polish",
		"description": "The Red Nail Polish offers a rich and glossy red hue for vibrant and polished nails. With a quick-drying formula, it provides a salon-quality finish at home.",
		"category": "beauty",
		"price": 8.99,
		"stock": 79,
		"tags": [
			"beauty",
			"nail polish"
		],
		"reviews": [
			{
				"id": "a64d86e2-92f3-4d0a-b601-a2d546a1f162",
				"rating": 2,
				"comment": "Poor quality!",
				"date": "2025-04-30T09:41:02.053Z",
				"reviewerName": "Benjamin Wilson",
				"reviewerEmail": "benjamin.wilson@x.dummyjson.com"
			},
			{
				"id": "030aed6a-62a2-42d0-b305-fb9bbca4e671",
				"rating": 5,
				"comment": "Great product!",
				"date": "2025-04-30T09:41:02.053Z",
				"reviewerName": "Liam Smith",
				"reviewerEmail": "liam.smith@x.dummyjson.com"
			},
			{
				"id": "040c14b2-458e-4458-8615-795a8ff88cda",
				"rating": 1,
				"comment": "Very unhappy with my purchase!",
				"date": "2025-04-30T09:41:02.053Z",
				"reviewerName": "Clara Berry",
				"reviewerEmail": "clara.berry@x.dummyjson.com"
			}
		],
		"images": [
			"https://cdn.dummyjson.com/product-images/beauty/red-nail-polish/1.webp"
		]
	}
  ]
```


## Equipe
- Vitória Luz Alves D'Abadia

## Referências
- Kafka: The Definitive Guide (O’Reilly)
- Documentação oficial do Apache Kafka 4.0.0 
- Spring for Apache Kafka Reference Guide