db.createCollection("caixa", {
    validator: {
      $jsonSchema: {
        required: ["nome"],
        properties: {
          nome: {
            bsonType: "string",
            maxLength: 64,
            description: "Nome do caixa.",
          },
          pedidos: {
            bsonType: "array",
            description: "Pedidos fechados pelo caixa.",
          },
        },
      },
    },
  });