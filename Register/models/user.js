const Sequelize = require("sequelize");
const db = require("../database/db");

const user = db.define("user", {
  firstname: Sequelize.TEXT,
  lastname: Sequelize.TEXT,
  email: Sequelize.TEXT,
  address: Sequelize.TEXT
});

module.exports = user;