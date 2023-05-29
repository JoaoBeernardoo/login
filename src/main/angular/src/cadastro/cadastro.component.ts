import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient,HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})


export class CadastroComponent {
  title = 'angular';
  exibirCadastro=false;
  cadastroStatus: string = '';



  loginForm: FormGroup;



  
  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.loginForm = this.fb.group({
    
      nome: ['', Validators.required],
      login: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      celular: ['', Validators.required],
      senha: ['', [Validators.required]]
     
    });
  }



  submit() {
    if (this.loginForm.invalid) {
      this.cadastroStatus = 'Por favor, preencha todos os campos obrigatórios.';
      return;
    }else{
    console.log("tamo aqui")
    const url = 'http://localhost:8080/lojinha-1/api/message/cadastro';

    const formData = this.loginForm.value;

      
let headers = new HttpHeaders();
 headers = headers.set('Content-Type', 'application/json');
 console.log("vai pra url agora")
    this.http.post(url, formData).subscribe({
      next: () => {
        console.log('Cadastro realizado com sucesso!');
        this.cadastroStatus = 'Cadastro realizado com sucesso!';

        // Lógica adicional após o cadastro ser realizado
      },
      error: error => {
        console.error('Ocorreu um erro durante o cadastro:', error);
        this.cadastroStatus = 'Ocorreu um erro durante o cadastro.';
        // Lógica adicional para tratar o erro
      }
    });

  }
       // const { username, password } = this.loginForm.value;
   // let headers = new HttpHeaders();
  // headers = headers.set('Content-Type', 'application/json');
   // console.log("tamo aqui")
   // this.http.get<MessageResponse>('http://localhost:8080/lojinha-1/api/message')
   // .subscribe((response: MessageResponse) => {
    //  console.log('foi');
     // console.log(response.message);
     // Armazenar token no armazenamento local e redirecionar para página protegida
   // });
  }
}
