const express = require('express');

const app = express();
const port = 3003;

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.post('/paypal',async(req,res)=>{
    res.json({
        message:`Paid Succesfully with Paypal`
    });
});

app.listen(port,()=>{
    console.log(`Payment-service listening on port ${port}`);
});