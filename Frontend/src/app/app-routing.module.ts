import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainScreenComponent } from './UIVersion-1/main-screen/main-screen.component';


const routes: Routes = [
  {
    path: 'ui-v2',
    loadChildren: () => import('./ui-v2/ui-v2.module').then(m => m.UiV2Module) 
  },
  { path: 'home', component: MainScreenComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
