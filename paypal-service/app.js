const express = require('express');

const app = express();
const port = 3002;

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.post('/paypal',async(req,res)=>{
    let {username,password} = req.body;
    if(username && password){
        res.json({
            message:`Paid Succesfully with ${username}`
        });
    }else{
        res.json({
            message:`Payment unsuccessfull`
        });
    } 
});

app.listen(port,()=>{
    console.log(`Payment-service listening on port ${port}`);
});