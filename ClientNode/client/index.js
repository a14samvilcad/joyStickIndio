//Les sessions funcionen sense problemes en l'exemple de sessions 1, quan ho utilitzem com a api, 
//dona algun problema, ja que les cookies (que es on viatje d'id de sessió), no és transmeten al ser un 
//cross domain. Amb aquest exemple, queda solucionat. Podeu veure explicació en el següent enllaç
//https://www.codeconcisely.com/posts/how-to-set-up-cors-and-cookie-session-in-express/

var app = new Vue({
    el: "#app",
    vuetify: new Vuetify(),
    data: {
        drawer: false,
        group: null,
        nameUser: "",
        passwd: "",
        snackbar: false,
        text: '',
        nameUser:'',
        loading: false,
        timeout: 1500,
        timeout2: 1,
        postData: null
    },
    methods: {
        toggleDrawerAndGetNomUsuariPost: function () {
            this.drawer = true;
            this.getNomUsuariPost();
          },
        getAuthPost:function () {
        
            var callback = ()=>{
                this.snackbar = true;
                if(this.postData.isAuth){
                    this.text = "Autoritzat. Roles => " + this.postData.roles;
                    window.location.replace("admin.html");
                }else{
                    this.text = "No autoritzat";
                }
                this.loading = false;
            };

            this.doFetchPost("http://proj2dam.alumnes.inspedralbes.cat:7341/authPost", {userid:this.username, passwdid:this.passwd}, callback);    
        },
        getLogOutPost: function () {

            var callback = ()=>{
                window.location.replace("index.html");
                this.snackbar = true;
                this.text =  this.postData.text;
                this.loading = false;
            };
            this.doFetchPost("http://proj2dam.alumnes.inspedralbes.cat:7341/logOutPost", {}, callback);    
        },
        getStatusPost: function () {
            var callback = ()=>{
                this.snackbar = true;
                this.text = "Status:  " + this.postData.text;
                this.loading = false;
            };
            this.doFetchPost("http://proj2dam.alumnes.inspedralbes.cat:7341/statusPost", {}, callback);    

        },
        getNomUsuariPost: function () {
            var callback = ()=>{
                this.snackbar = true;
                this.nameUser = this.postData.nameUser;
                if(this.nameUser == "error"){
                    window.location.replace("index.html");
                }else{
                }
                this.loading = false;
            };
            this.doFetchPostUser("http://proj2dam.alumnes.inspedralbes.cat:7341/getNomUsuari", {}, callback);    

        },

        doFetchPost: function(url, data, callback){
            this.loading = true;
            
            fetch(url,
            {
                method: "POST",
                headers: {
                    'Content-Type':'application/json',
                    'Accept':'application/json',
                },
                credentials: 'include',
                body: JSON.stringify(data),
                mode: "cors",
                cache: "default",
            }
            ).then(
                (response) =>{
                    console.log(response);
                    return (response.json());
                }
            ).then(
                (data) => {
                    this.postData = data;
                    setTimeout(callback, this.timeout);
                }
            ).catch(
                (error) => {
                    console.log(error);
                }
            );
        },
        doFetchPostUser: function(url, data,callback){
            this.loading = true;
            
            fetch(url,
            {
                method: "POST",
                headers: {
                    'Content-Type':'application/json',
                    'Accept':'application/json',
                },
                credentials: 'include',
                body: JSON.stringify(data),
                mode: "cors",
                cache: "default",
            }
            ).then(
                (response) =>{
                    console.log(response);
                    return (response.json());
                }
            ).then(
                (data) => {
                    this.postData = data;
                    setTimeout(callback, this.timeout2);
                }
            ).catch(
                (error) => {
                    console.log(error);
                }
            );
        }
    }
});
