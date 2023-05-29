import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-poslogin',
  templateUrl: './poslogin.component.html',
  styleUrls: ['./poslogin.component.css']
})
export class PosloginComponent {
  nomeUsuario: string = '';

  login: string = '';
  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {
    //this.obterNomeUsuario();
  }


  ngOnInit() {
    this.login = this.route.snapshot.queryParams['login'];
    console.log('Login:',this.login);
    this.obterNomeUsuario(this.login); // Chame o método para obter o nome do usuário com o valor de login
  }
  

  obterNomeUsuario(login: string) {
    const url = 'http://localhost:8080/lojinha-1/api/message/poslogin';
    const params = { login: login }; // Adicione o parâmetro login na requisição
  
    this.http.get<any>(url, { params }).subscribe({
      next: (response) => {
        this.nomeUsuario = response.message;
        console.log('Nome do usuário:', response.message);
        console.log(this.nomeUsuario) // Atualize o valor de nomeUsuario com a resposta do back-end
      },
      error: (error) => {
        console.error('Ocorreu um erro ao obter o nome do usuário:', error);
      }
    });
  }
  
  excluir(login: string) {
    const url = 'http://localhost:8080/lojinha-1/api/message/excluir';
    const params = { login: this.login };
  
    this.http.get<any>(url, { params }).subscribe({
      next: (response) => {
        console.log(response.message, params);
        if (response.message === 'sucess') {
          console.log('Cadastro excluído com sucesso');
          console.log('esta aqui');
          this.router.navigate(['/appcomponent']);
        } else {
          console.error('Erro ao excluir cadastro:');
          console.log('aeaeea eno', this.nomeUsuario);
        }
      }
    });
  }
  
}
