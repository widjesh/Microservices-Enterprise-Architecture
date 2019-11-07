const express = require('express');

const app = express();
const port = 3004;
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.post('/creditcard',async(req,resp)=>{
    let{number,expirationdate} = req.body;
    if(number && expirationdate){
        resp.json({
            message:`Creditcard payment succesfull`
        });
    }else{
        resp.json({
            message:`Creditcard payment unsuccessfull`
        });
    }
 
});

app.listen(port,()=>{
    console.log(`Creditcard-Service listening on port ${port}`)
});