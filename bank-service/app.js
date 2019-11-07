const express = require("express");

const app = express();
const port = 3005;
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.post("/banktransfer", async (req, res) => {
let{banknumber} = req.body;
if(banknumber){
    res.json({
        message:`Banktransfer succesful with banknumber ${banknumber}`
    });
}else{
    res.json({
        message:`Banktransfer unsuccesful`
    });
}
});

app.listen(port, () => {
  console.log(`Bank-Service listening on port ${port}`);
});
