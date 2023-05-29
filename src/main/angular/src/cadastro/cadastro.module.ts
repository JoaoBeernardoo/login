import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // Importe o ReactiveFormsModule
import { HttpClientModule } from '@angular/common/http'; // Importe esta linha
import { CadastroComponent } from './cadastro.component';


@NgModule({
  declarations: [
    CadastroComponent,
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule, // Adicione esta linha
  ],
  providers: [],
  bootstrap: [CadastroComponent]
})
export class CadastroModule { }