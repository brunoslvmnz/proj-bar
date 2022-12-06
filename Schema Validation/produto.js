db.createCollection("produtos", {
    validator: {
      $jsonSchema: {
        required: ["nome", "preco"],
        properties: {
          nome: {
            bsonType: "string",
            maxLength: 64,
            description: "Nome do produto.",
          },
          preco: {
            bsonType: "double",
            minimum: 1,
            description: "Preço do produto",
          },
        },
      },
    },
  });
  