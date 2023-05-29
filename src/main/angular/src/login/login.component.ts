import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { MessageResponse } from './messageresponse';
import { Router } from '@angular/router';
import { UserService } from '../user-service.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  exibirPosLogin = false;
  login: string = '';
  loginForm: FormGroup;
  

  

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router, private userService: UserService) {
    this.loginForm = this.fb.group({
      login: ['', Validators.required],
      senha: ['', [Validators.required, Validators.minLength(8)]]
     
    });
  }

  mostrarposCadastro(){
    this.exibirPosLogin = true;
   
  }
 

  submit() {
    const url = 'http://localhost:8080/lojinha-1/api/message/login';
    const loginControl = this.loginForm.get('login');
    const senhaControl = this.loginForm.get('senha');
  
    if (loginControl && loginControl.value && senhaControl && senhaControl.value) {
      const formData = {
        login: loginControl.value,
        senha: senhaControl.value
      };
      console.log(loginControl.value)
      console.log(senhaControl.value)
      const headers = new HttpHeaders().set('Content-Type', 'application/json');
  
      this.http.post<MessageResponse>(url, formData, { headers }).subscribe({
        next: (response) => {
          console.log(response.message);
          if (response.message === 'errooo') {
            console.log('Dados de login incorretos. Por favor, verifique novamente.');
            this.login='Seus dados estão incorretos';
            // Exibir mensagem de erro na interface do usuário ou tomar outra ação apropriada
          } else {
            console.log(response.message);
            this.userService.setNomeUsuario(loginControl.value);
            this.router.navigate(['/poslogin'], { queryParams: { login: loginControl.value } });
            // Additional logic after successful registration
          }
      
        },
        error: error => {
          console.error('Ocorreu um erro durante o cadastro:', error);
          // Additional logic to handle the error
        }
      });
    }
  }
  
  

}


