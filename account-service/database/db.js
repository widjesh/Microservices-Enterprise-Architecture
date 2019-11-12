const Sequelize = require('sequelize');

const connection = new Sequelize('node',process.env.DB_USERNAME,process.env.DB_PASSWORD,{ 
  port : 3306,
    host : 'polling-app-mysql', 
    dialect : 'mysql',
    operatorAliases:false,
    pool:{
        max : 5,
        min : 0,
        aquire : 3000,
        idle : 10000
    }
});

connection.authenticate()
  .then(() => {
    console.log("Database Connected Successfully");
  })
  .catch(err => console.log(`Error: ${err}`));

  //Run this code to drop table 
connection.sync({
    force : true
}).then(()=>{
    console.log('Database & Table created');
})


module.exports = connection;

