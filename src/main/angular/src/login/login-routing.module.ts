import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PosloginComponent } from '../poslogin/poslogin.component';

const routes: Routes = [
    { path: 'poslogin', component: PosloginComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { }



export class MessageResponse {
    constructor(public message: string) {}
  }