const Sequelize = require("sequelize");
const db = require("../database/db");

const user = db.define("user", {
  firstname: Sequelize.TEXT,
  lastname: Sequelize.TEXT,
  email: Sequelize.TEXT,
  password : Sequelize.TEXT,
  address: Sequelize.TEXT,
  payment : Sequelize.TEXT
});

module.exports = user;