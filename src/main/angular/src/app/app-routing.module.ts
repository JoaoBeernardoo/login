import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroComponent } from '../cadastro/cadastro.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from '../login/login.component';
import { LoginRoutingModule } from '../login/login-routing.module';
import { PosloginComponent } from 'src/poslogin/poslogin.component';
import { PosloginRoutingModule } from '../poslogin/poslogin-routing.module';


const routes: Routes = [
  { path: 'cadastro', component: CadastroComponent },
  { path: 'login', component: LoginComponent },
  { path: 'poslogin', component: PosloginComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes),ReactiveFormsModule, LoginRoutingModule, PosloginRoutingModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
