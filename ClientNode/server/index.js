const express = require('express');
const cors = require("cors");
const fs = require('fs');
const path = require('path');
const sessions = require('express-session');
var cookieParser = require('cookie-parser');


const app = express();

const PORT = 7341;

app.use(cookieParser());
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, authorization");
    res.header("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
    next();
});
app.use(sessions({
    secret: "sadsadsadasd",
    resave: false,
    saveUninitialized: false,
    cookie: { maxAge: 60000 },
    user:{ isAuth: false, roles: [] }
    
}));

//Middleware necessari per parsejar el body i colocar-ho dintre del req.body.
app.use(express.json());


//Configuració del mòdul de cors. 
//Per cada petició es crida aquest middleware (app.use)
//rep dos parametres el mòdul cors. El primer és l'origen, i el segon és una funció
//de callback que ens permet controlar si acceptem la petició o no.
app.use(cors({ 
    credentials: true,
    origin: function(origin, callback){
        console.log(origin);
        return callback(null, true)
    }
}));

//Ruta a /auth amb dos parametres que s'envien per "param"
app.post("/authPost", async (req, res) => {
    console.log("body ==============> ", req.body);
    console.log("session ==============>  ", req.session);
    console.log("headers ==============>  ", req.headers);

    let username = req.body.userid;
    let passwd = req.body.passwdid;
  
    ret = await checkUserFromJson(username, passwd);

    req.session.user = ret;

    console.log(req.session);

    res.send(JSON.stringify(ret));
      
});

//Ruta a /logOutPost amb dos parametres que s'envien per "param"
app.post("/logOutPost", async (req, res) => {
    console.log("body ==============> ", req.body);
    console.log("session ==============>  ", req.session);
    console.log("headers ==============>  ", req.headers);
    let session = req.session;

    var ret = {
        text: "No hi ha cap sessió que eliminar"
    }; 
    if(session.user && session.user.isAuth){
        session.user  = { isAuth: false, roles: [] }
        var ret = {
            text: "sessió eliminada correctament"
        };    
    }
 
 
    console.log(ret)
    res.send(JSON.stringify(ret));
      
});

//Ruta a /statusPost amb dos parametres que s'envien per "param"
app.post("/statusPost", async (req, res) => {
    console.log("body ==============> ", req.body);
    console.log("session ==============>  ", req.session);
    console.log("headers ==============>  ", req.headers);
    let session = req.session;

    var ret = {
        text: "No hi ha cap sessió activa"
    };  
    //console.log(session);

    if(session.user && session.user.isAuth){
        var ret = {
            text: "Sessió Activa => amb usuari "+session.user.username
        };
    }

    console.log(ret)
    res.send(JSON.stringify(ret));
      
});

//Ruta a /getNomUsuari amb dos parametres que s'envien per "param"
app.post("/getNomUsuari", async (req, res) => {
    console.log("body ==============> ", req.body);
    console.log("session ==============>  ", req.session);
    console.log("headers ==============>  ", req.headers);
    let session = req.session;

    var ret = {
        nameUser: "error"
    };  
    //console.log(session);

    if(session.user && session.user.isAuth){
        var ret = {
            nameUser: session.user.username
        };
    }

    console.log(ret)
    res.send(JSON.stringify(ret));
      
});

app.listen (PORT, ()=>{
    console.log("Server Running ["+PORT+"]");
});

function checkUserFromJson(username, passwd){
    let ret = {
        isAuth: false,
        roles:[]
    };
    console.log("username => "+username);
    console.log("password => "+passwd);
    var prom = new Promise((resolve, reject) => {
        //llegim l'array de clients
         fs.readFile(path.join(__dirname, "users.json"), (err, data) =>{
             if(err){
                 console.log(err);
             }
             var dades = JSON.parse(data);
             //fins es una funció d'arrays en javascript.
             user = dades.clients.find(user => {            
                 if(user.username == username && user.passwd == passwd){
                     return true;
                 }
             });
             if(user){
                 console.log("user found");
                 ret.isAuth = user.isAuth;
                 ret.roles = user.roles;
                 ret.username = user.username;
             }
             console.log(ret);
             resolve(ret);
        }); 
    });
    return prom;
}
