const express = require("express");
var User = require("./models/user");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");

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
    let { firstname, lastname, email, password, address, payment } = req.body;
    //Insert into table
    await User.create({
      firstname: firstname,
      lastname: lastname,
      email: email,
      password: bcrypt.hashSync(password, 10),
      address: address,
      payment: payment
    })
      .then(user => console.log("Saved User Successfully"))
      .catch(err => console.error(err));
  } catch (err) {
    console.error(err);
  }
});

app.post("/login", async (req, res) => {
  try {
    let { email, password } = req.body;
    const findUser = await User.findAll({
      where: {
        email: email
      }
    }).then(users => {
      if (users.length === 0) {
        res.json("Not Found");
      } else {
        const encryptedpassword = users[0].password;
        const verify = bcrypt.compareSync(password, encryptedpassword);
        if (!verify) {
          res.json({ message: "Auth Failed" });
        } else {
          const token = jwt.sign(
            {
              email: users[0].email
            },
            "TOPSECRET",
            {
              expiresIn: "1h"
            }
          );
          res.status(200).json({
            message: "Auth Successfull",
            token: token
          });
        }
      }
    });
  } catch (err) {}
});

app.get('/find/:email',async(req,res)=>{
  User.findAll({
    email:req.params.email
  }).then((users)=>{
    res.json(users[0]);
  });
});

app.listen(port, () => {
  console.log(`Registration listening on port ${port}`);
});
