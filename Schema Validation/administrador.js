db.createCollection("administradores", {
  validator: {
    $jsonSchema: {
      required: ["nome"],
      properties: {
        nome: {
          bsonType: "string",
          maxLength: 64,
          description: "Nome do administrador.",
        },
        produtos: {
          bsonType: "array",
          description: "Produtos cadastrados do administrador",
        },
      },
    },
  },
});
