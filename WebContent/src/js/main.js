//Apontando documento HTML: Campo texto "cep"
const cep = document.querySelector("#cep")

//Tratando o resultado
const showData = (result)=>{
    for(const campo in result){ //Pra cada um dos resultados armazena ele no campo
        if(document.querySelector("#"+campo)){ // Pegue nesse docuemnto pelo id se não descate
            document.querySelector("#"+campo).value = result[campo] //
            // Se existir o id escreva 
        }
    }
}
//Evento                        Arrow
cep.addEventListener("blur", (e)=>{
    let search = cep.value.replace("-","")
    const options = { //Parametros
        method: 'GET',
        mode: 'cors', 
        cache: 'default'
    }                                   //ADD variavel search     
    fetch(`https://viacep.com.br/ws/${search}/json/`, options)
        //Se der certo
        .then(response=>{ response.json()
        //Trate o resultado    
        .then(data => showData(data))
    })
    //Se der errado
    .catch(e => console.log('Deu Erro: '+ e,message))
})
