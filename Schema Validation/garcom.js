db.createCollection("garcons", {
    validator: {
      $jsonSchema: {
        required: ["nome"],
        properties: {
          nome: {
            bsonType: "string",
            maxLength: 64,
            description: "Nome do garcom.",
          },
          pedidos: {
            bsonType: "array",
            description: "Pedidos gerenciados pelo garcom.",
          },
        },
      },
    },
  });