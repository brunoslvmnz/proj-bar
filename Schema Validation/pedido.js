db.createCollection("pedidos", {
  validator: {
    $jsonSchema: {
      required: ["status", "viagem", "produtoList"],
      properties: {
        valorPedido: {
          bsonType: "double",
          maxLength: 5,
          description: "Valor total do pedido.",
        },
        status: {
          bsonType: "string",
          maxLength: 50,
          description: "Status do pedido.",
        },
        viagem: {
          bsonType: "bool",
          description: "Pedido para viagem ou consumação.",
        },
        garcom: {
          bsonType: "object",
          description: "id de referência do garcom.",
        },
        produtoList: {
          bsonType: "array",
          minItems: 1,
          description: "Produtos do pedido.",
          items:{
            bsonType: ["object"],
            description: "id de referência dos produtos.",
          }
        },
      },
    },
  },
});
