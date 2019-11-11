const express = require("express");

const app = express();
const port = 3005;
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.post("/banktransfer", async (req, res) => {
    res.json({
        message:`Banktransfer succesfull`
    });
});

app.listen(port, () => {
  console.log(`Bank-Service listening on port ${port}`);
});
