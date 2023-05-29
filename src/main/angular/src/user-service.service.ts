import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private loginUsuario: string = '';

  constructor() { }

  setNomeUsuario(login: string) {
    this.loginUsuario = login;
  }

  getNomeUsuario() {
    return this.loginUsuario;
  }
}
