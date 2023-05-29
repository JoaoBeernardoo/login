import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { MessageResponse } from './message-response.model';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
 
  exibirCadastro = false;
  exibirLogin = false; 
  cadastroRealizado: boolean = false;
  loginRealizado: boolean = false;
  paglogin: boolean =  false;
  

  voltar(){
  
        this.exibirLogin = false; 
    this.exibirCadastro = false;
    this.cadastroRealizado = false;
    this.loginRealizado=false;

  }
  mostrarCadastro() {
    this.exibirCadastro = true;
    this.exibirLogin= false;
    this.cadastroRealizado = true;
    this.loginRealizado=true;
  }

  mostrarLogin(){
    this.exibirLogin=true;
    this.exibirCadastro=false;
    this.loginRealizado=true;
    this.cadastroRealizado = true;
    this.paglogin = true;
   
  }


  title = 'angular';

 
    
  }








