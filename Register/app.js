const express = require("express");
var User = require("./models/user");

const app = express();
const port = 3000;

//Database
const db = require("./database/db");

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

// Retrieve all the users
app.get("/", (req, res) => {
  User.findAll().then(users => {
    res.json(users);
  });
});

//Register a new user
app.post("/register", async (req, res) => {
  try {
    console.log(req.body);
    let { firstname, lastname, email, address } = req.body;
    //Insert into table
    await User.create({
      firstname: firstname,
      lastname: lastname,
      email: email,
      address: address
    })
      .then(user => console.log("Saved User Successfully"))
      .catch(err => console.error(err));
  } catch (err) {
    console.error(err);
  }
});

app.listen(port, () => {
  console.log(`Registration listening on port ${port}`);
});